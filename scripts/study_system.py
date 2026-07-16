#!/usr/bin/env python3
"""Adaptive, gamified coding-test study state and safe completion CLI."""
from __future__ import annotations

import argparse
import contextlib
import fcntl
import json
import os
from pathlib import Path
import shutil
import subprocess
import sys
import tempfile
from datetime import datetime
from zoneinfo import ZoneInfo

KST = ZoneInfo("Asia/Seoul")

PROFILE_DEFAULT = {
    "weeks": 8,
    "sessions_per_week": 6,
    "language": "Java",
    "schedule": {"morning": "08:00", "reminder": "20:00", "timezone": "Asia/Seoul"},
    "source_ratio": {"platform": 80, "generated": 20},
    "placement_sessions": 6,
    "target": "프로그래머스 Lv.2 상위 안정화 / Lv.3 입문",
}
PROGRESS_DEFAULT = {
    "xp": 0,
    "sr": 450,
    "placement_completed": 0,
    "completed": [],
    "failed": [],
    "streak": 0,
    "weekly": {"attempted": 0, "completed": 0, "target": 6},
    "topics": {},
    "last_activity": None,
}
ACHIEVEMENTS_DEFAULT = {"unlocked": [], "topic_badges": {}, "weekly_quests": []}
TIER_RANGES = [
    (0, 299, "BRONZE", "프로그래머스 Lv.1 학습"),
    (300, 449, "SILVER", "Lv.1 안정 / Lv.2 입문"),
    (450, 599, "GOLD", "Lv.2 기본"),
    (600, 749, "PLATINUM", "Lv.2 상위"),
    (750, 879, "DIAMOND", "Lv.3 입문"),
    (880, 1000, "MASTER", "Lv.3 안정"),
]
RESERVED_WORKSPACE_ROOTS = {
    ".", ".git", ".study", "roadmap", "scripts", "tests", "templates",
    "docs", "out", ".idea",
}


def atomic_write_json(path: Path, data: dict) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    fd, temp_name = tempfile.mkstemp(prefix=path.name + ".", dir=path.parent)
    try:
        with os.fdopen(fd, "w", encoding="utf-8") as handle:
            json.dump(data, handle, ensure_ascii=False, indent=2)
            handle.write("\n")
            handle.flush()
            os.fsync(handle.fileno())
        os.replace(temp_name, path)
    finally:
        with contextlib.suppress(FileNotFoundError):
            os.unlink(temp_name)


def read_json(path: Path, default: dict | None = None) -> dict:
    if not path.exists():
        return json.loads(json.dumps(default or {}))
    return json.loads(path.read_text(encoding="utf-8"))


@contextlib.contextmanager
def state_lock(repo: Path):
    lock_path = repo / ".study" / "state.lock"
    lock_path.parent.mkdir(parents=True, exist_ok=True)
    with lock_path.open("a+") as handle:
        fcntl.flock(handle.fileno(), fcntl.LOCK_EX)
        try:
            yield
        finally:
            fcntl.flock(handle.fileno(), fcntl.LOCK_UN)


def repo_path(repo: Path, value: str, *, must_exist: bool = False) -> Path:
    candidate = Path(value)
    if candidate.is_absolute():
        raise ValueError("저장소 내부의 상대 경로만 허용됩니다.")
    resolved_repo = repo.resolve()
    resolved = (repo / candidate).resolve(strict=False)
    try:
        resolved.relative_to(resolved_repo)
    except ValueError as exc:
        raise ValueError("저장소 외부 경로 또는 심볼릭 링크는 허용되지 않습니다.") from exc
    if must_exist and not resolved.exists():
        raise ValueError(f"풀이 경로가 없습니다: {value}")
    return resolved


def validate_workspace(repo: Path, value: str) -> Path:
    candidate = Path(value)
    if len(candidate.parts) < 2 or candidate.parts[0] in RESERVED_WORKSPACE_ROOTS:
        raise ValueError("문제별 하위 작업 경로만 허용됩니다.")
    return repo_path(repo, value)


def validate_system_directories(repo: Path) -> None:
    resolved_repo = repo.resolve()
    for name in ("roadmap", ".study"):
        path = repo / name
        if path.is_symlink():
            raise ValueError(f"상태 디렉터리는 심볼릭 링크일 수 없습니다: {name}")
        if path.exists():
            try:
                path.resolve().relative_to(resolved_repo)
            except ValueError as exc:
                raise ValueError(f"상태 디렉터리가 저장소 밖을 가리킵니다: {name}") from exc


def tier_info(sr: int, placement_completed: int) -> tuple[str, str]:
    if placement_completed < 6:
        return "UNRANKED", "배치 진행 중"
    bounded = max(0, min(1000, int(sr)))
    for low, high, tier, real in TIER_RANGES:
        if low <= bounded <= high:
            part = min(3, ((bounded - low) * 4) // (high - low + 1))
            return f"{tier} {['IV', 'III', 'II', 'I'][part]}", real
    raise AssertionError("unreachable")


def tier_label(sr: int, placement_completed: int) -> str:
    return tier_info(sr, placement_completed)[0]


def ensure_init(repo: Path) -> None:
    validate_system_directories(repo)
    (repo / "roadmap").mkdir(parents=True, exist_ok=True)
    (repo / ".study").mkdir(parents=True, exist_ok=True)
    defaults = [
        (repo / "roadmap/study-profile.json", PROFILE_DEFAULT),
        (repo / "roadmap/progress.json", PROGRESS_DEFAULT),
        (repo / "roadmap/achievements.json", ACHIEVEMENTS_DEFAULT),
    ]
    for path, default in defaults:
        if not path.exists():
            atomic_write_json(path, default)


def load_progress(repo: Path) -> dict:
    ensure_init(repo)
    progress = read_json(repo / "roadmap/progress.json", PROGRESS_DEFAULT)
    for key, value in PROGRESS_DEFAULT.items():
        if key not in progress:
            progress[key] = json.loads(json.dumps(value))
    return progress


def current_path(repo: Path) -> Path:
    return repo / ".study/current.json"


def current_state(repo: Path) -> dict | None:
    path = current_path(repo)
    return read_json(path) if path.exists() else None


def status_payload(repo: Path) -> dict:
    progress = load_progress(repo)
    tier, real = tier_info(progress["sr"], progress["placement_completed"])
    return {"tier": tier, "real_level": real, "progress": progress, "current": current_state(repo)}


def render_header(progress: dict) -> str:
    tier, real = tier_info(progress["sr"], progress["placement_completed"])
    return (
        f"[{tier}] 실제 수준: {real} · SR {progress['sr']} · XP {progress['xp']}\n"
        f"주간 {progress['weekly'].get('completed', 0)}/{progress['weekly'].get('target', 6)}"
        f" · 연속 {progress.get('streak', 0)}일"
    )


def parse_metadata(args: argparse.Namespace, repo: Path) -> dict:
    if args.json:
        metadata_path = repo_path(repo, args.json, must_exist=True) if not Path(args.json).is_absolute() else Path(args.json)
        data = read_json(metadata_path)
    else:
        fields = ("id", "title", "platform", "url", "difficulty", "topic", "target_minutes", "kind", "workspace_path")
        data = {field: getattr(args, field) for field in fields}
    required = ("id", "title", "platform", "difficulty", "topic", "target_minutes", "kind", "workspace_path")
    missing = [name for name in required if data.get(name) in (None, "")]
    if missing:
        raise ValueError("필수 문제 정보 누락: " + ", ".join(missing))
    data["target_minutes"] = int(data["target_minutes"])
    if data["kind"] not in {"platform", "generated"}:
        raise ValueError("kind는 platform 또는 generated여야 합니다.")
    validate_workspace(repo, str(data["workspace_path"]))
    hints = data.get("hints") or [
        "입력 제한과 필요한 시간복잡도를 먼저 확인해 보세요.",
        "작은 예제를 직접 따라가며 필요한 상태를 정의해 보세요.",
        "핵심 자료구조와 반복문의 불변 조건을 다시 확인해 보세요.",
    ]
    data["hints"] = [str(h) for h in hints if "class " not in str(h)]
    return data


def command_init(repo: Path, _args: argparse.Namespace) -> int:
    ensure_init(repo)
    print("코딩테스트 학습 시스템 초기화 완료")
    return 0


def command_start(repo: Path, args: argparse.Namespace) -> int:
    ensure_init(repo)
    with state_lock(repo):
        existing = current_state(repo)
        if existing and existing.get("status") == "pending" and not args.replace:
            raise ValueError("진행 중인 문제가 있습니다. 교체하려면 --replace를 사용하세요.")
        data = parse_metadata(args, repo)
        data.update({
            "status": "pending",
            "date": datetime.now(KST).date().isoformat(),
            "started_at": datetime.now(KST).isoformat(),
            "hint_count": 0,
        })
        atomic_write_json(current_path(repo), data)
        progress = load_progress(repo)
    base = 160 if "lv3" in str(data["difficulty"]).lower() else 100 if "lv2" in str(data["difficulty"]).lower() else 60
    print(render_header(progress))
    print("\n오늘의 코테 Next Step")
    print(f"- 문제: {data['title']} ({data['platform']})")
    if data.get("url") and data["url"] != "-":
        print(f"- 링크: {data['url']}")
    print(f"- 유형: {data['topic']} · 난이도: {data['difficulty']}")
    print(f"- 권장 제한시간: {data['target_minutes']}분")
    print(f"- 풀이 경로: {data['workspace_path']}")
    print(f"- 오늘의 최대 보상: 기본 {base} XP + 도전/무힌트/시간/설명 보너스")
    print("- 막히면: hint 또는 Telegram에서 `힌트`")
    print("- 통과 후: complete 또는 Telegram에서 `완료`")
    return 0


def command_status(repo: Path, args: argparse.Namespace) -> int:
    payload = status_payload(repo)
    if args.json:
        print(json.dumps(payload, ensure_ascii=False))
        return 0
    progress = payload["progress"]
    print(render_header(progress))
    current = payload["current"]
    if current:
        print(f"현재 문제: {current.get('title')} · 상태: {current.get('status')}")
    else:
        print("현재 문제: 없음")
    print(f"배치: {progress['placement_completed']}/6 · 완료 누계: {len(progress['completed'])}")
    return 0


def command_reminder(repo: Path, _args: argparse.Namespace) -> int:
    current = current_state(repo)
    today = datetime.now(KST).date().isoformat()
    if not current or current.get("status") != "pending" or current.get("date") != today:
        return 0
    print("오늘 코테 문제가 아직 미완료예요.")
    print(f"- 문제: {current.get('title', '오늘 문제')}")
    print(f"- 목표: {current.get('target_minutes', 45)}분")
    print("- 막혔다면 `힌트`, 통과했다면 `완료`, 쉬려면 `휴식`, 이어가려면 `이월`")
    return 0


def java_files(solution: Path) -> list[Path]:
    if solution.is_file():
        return [solution] if solution.suffix == ".java" else []
    return sorted(path for path in solution.rglob("*.java") if path.is_file())


def compile_java(solution: Path) -> None:
    files = java_files(solution)
    if not files:
        raise ValueError("검증할 Java 풀이 파일이 없습니다.")
    with tempfile.TemporaryDirectory(prefix="coding-test-javac-") as output:
        cp = subprocess.run(["javac", "-encoding", "UTF-8", "-d", output, *map(str, files)], text=True, capture_output=True)
    if cp.returncode:
        raise ValueError("Java 컴파일 실패:\n" + (cp.stderr or cp.stdout).strip())


def difficulty_xp(value: str) -> int:
    value = value.lower()
    if "lv3" in value or "gold" in value:
        return 160
    if "lv2" in value or "silver" in value:
        return 100
    return 60


def calculate_reward(current: dict, args: argparse.Namespace) -> tuple[int, int, list[str]]:
    xp = 20 + difficulty_xp(str(current.get("difficulty", "lv1")))
    reasons = ["도전 +20", f"해결 +{difficulty_xp(str(current.get('difficulty', 'lv1')))}"]
    if int(current.get("hint_count", 0)) == 0:
        xp += 30; reasons.append("무힌트 +30")
    within = args.elapsed_minutes is not None and args.elapsed_minutes <= int(current.get("target_minutes", 45))
    if within:
        xp += 20; reasons.append("제한시간 +20")
    if args.explained:
        xp += 20; reasons.append("설명 +20")
    if args.retry:
        xp += 50; reasons.append("재도전 +50")
    if args.review:
        xp += 40; reasons.append("복습 +40")
    sr = 6 if int(current.get("hint_count", 0)) else 12
    if within:
        sr += 3
    return xp, sr, reasons


def append_review(repo: Path, current: dict, elapsed: int | None) -> None:
    path = repo / "roadmap/review-log.md"
    if not path.exists():
        return
    line = (
        f"\n- {datetime.now(KST).date().isoformat()} | {current['platform']} | {current['title']}"
        f" | {current['topic']} | 완료 | {elapsed if elapsed is not None else '-'}분\n"
    )
    with path.open("a", encoding="utf-8") as handle:
        handle.write(line)


def run_git(repo: Path, current: dict, paths: list[Path], *, push: bool) -> tuple[str, str]:
    git_env = os.environ.copy()
    git_env["GIT_TERMINAL_PROMPT"] = "0"
    if subprocess.run(
        ["git", "rev-parse", "--is-inside-work-tree"], cwd=repo,
        env=git_env, capture_output=True, timeout=10,
    ).returncode:
        raise ValueError("Git 저장소가 아닙니다.")

    relative: list[str] = []
    for path in paths:
        candidates = [path] if path.is_file() else sorted(p for p in path.rglob("*") if p.is_file())
        for candidate in candidates:
            resolved = candidate.resolve()
            try:
                rel = str(resolved.relative_to(repo.resolve()))
            except ValueError as exc:
                raise ValueError("커밋 허용 경로가 저장소 밖을 가리킵니다.") from exc
            if rel.startswith(".git/"):
                raise ValueError("Git 내부 경로는 커밋할 수 없습니다.")
            relative.append(rel)
    relative = sorted(set(relative))
    if not relative:
        raise ValueError("커밋할 허용 파일이 없습니다.")

    original_index = subprocess.run(
        ["git", "rev-parse", "--git-path", "index"], cwd=repo, env=git_env,
        text=True, capture_output=True, check=True, timeout=10,
    ).stdout.strip()
    if not Path(original_index).is_absolute():
        original_index = str((repo / original_index).resolve())
    staged_raw = subprocess.run(
        ["git", "diff", "--cached", "--name-only", "-z"], cwd=repo,
        env=git_env, capture_output=True, check=True, timeout=10,
    ).stdout
    originally_staged = {name.decode("utf-8", "surrogateescape") for name in staged_raw.split(b"\0") if name}
    old_head = subprocess.run(
        ["git", "rev-parse", "HEAD"], cwd=repo, env=git_env,
        text=True, capture_output=True, check=True, timeout=10,
    ).stdout.strip()
    branch = subprocess.run(
        ["git", "branch", "--show-current"], cwd=repo, env=git_env,
        text=True, capture_output=True, check=True, timeout=10,
    ).stdout.strip()
    if not branch:
        raise ValueError("현재 Git 브랜치를 확인할 수 없습니다.")

    created_commit = False
    with tempfile.TemporaryDirectory(prefix="coding-test-index-") as temp:
        temp_index = str(Path(temp) / "index")
        env = git_env.copy()
        env["GIT_INDEX_FILE"] = temp_index
        subprocess.run(["git", "read-tree", "HEAD"], cwd=repo, env=env, check=True, capture_output=True, timeout=10)
        subprocess.run(["git", "add", "--", *relative], cwd=repo, env=env, check=True, capture_output=True, timeout=30)
        if subprocess.run(["git", "diff", "--cached", "--quiet"], cwd=repo, env=env, timeout=10).returncode == 0:
            sha = old_head
        else:
            prefix = "solve" if current.get("kind") in {"platform", "generated"} else "study"
            message = f"{prefix}: {current.get('platform')} {current.get('id')} {current.get('title')}"
            cp = subprocess.run(
                ["git", "commit", "-m", message], cwd=repo, env=env,
                text=True, capture_output=True, timeout=30,
            )
            if cp.returncode:
                raise ValueError("Git 커밋 실패: " + (cp.stderr or cp.stdout).strip())
            sha = subprocess.run(
                ["git", "rev-parse", "HEAD"], cwd=repo, env=git_env,
                text=True, capture_output=True, check=True, timeout=10,
            ).stdout.strip()
            created_commit = sha != old_head

    if push and created_commit:
        cp = subprocess.run(
            ["git", "push", "origin", branch], cwd=repo, env=git_env,
            text=True, capture_output=True, timeout=60,
        )
        if cp.returncode:
            rollback = subprocess.run(
                ["git", "update-ref", f"refs/heads/{branch}", old_head, sha],
                cwd=repo, env=git_env, text=True, capture_output=True, timeout=10,
            )
            if rollback.returncode:
                raise ValueError(
                    "GitHub push 실패 후 HEAD 복구도 실패했습니다: "
                    + (rollback.stderr or rollback.stdout).strip()
                )
            raise ValueError("GitHub push 실패(로컬 커밋 복구 완료): " + (cp.stderr or cp.stdout).strip())

    # Only paths that were not already staged by the user are aligned to the new HEAD.
    # Existing staged snapshots, including allowlisted paths, remain untouched.
    align = [name for name in relative if name not in originally_staged]
    if created_commit and align:
        real_env = git_env.copy()
        real_env["GIT_INDEX_FILE"] = original_index
        subprocess.run(
            ["git", "reset", "-q", "HEAD", "--", *align], cwd=repo,
            env=real_env, check=True, timeout=30,
        )

    remote = subprocess.run(
        ["git", "remote", "get-url", "origin"], cwd=repo, env=git_env,
        text=True, capture_output=True, timeout=10,
    )
    url = ""
    if remote.returncode == 0:
        value = remote.stdout.strip()
        if value.startswith("git@github.com:"):
            value = "https://github.com/" + value.split(":", 1)[1]
        if value.startswith("https://github.com/"):
            url = value.removesuffix(".git") + "/commit/" + sha
    return sha, url


def unlock_achievements(progress: dict, achievements: dict, topic: str) -> list[str]:
    new = []
    milestones = {3: "시동 완료", 7: "루틴 빌더", 14: "알고리즘 탐험가", 30: "꾸준함의 증명"}
    if progress["streak"] in milestones and milestones[progress["streak"]] not in achievements["unlocked"]:
        badge = milestones[progress["streak"]]; achievements["unlocked"].append(badge); new.append(badge)
    if topic and topic not in achievements["topic_badges"]:
        achievements["topic_badges"][topic] = "discovered"
        badge = f"{topic} 발견"
        achievements["unlocked"].append(badge); new.append(badge)
    return new


def command_complete(repo: Path, args: argparse.Namespace) -> int:
    ensure_init(repo)
    with state_lock(repo):
        current = current_state(repo)
        if not current:
            raise ValueError("진행 중인 문제가 없습니다.")
        if current.get("status") == "completed":
            print("이미 완료 처리된 문제입니다.")
            return 0
        if current.get("status") != "pending":
            raise ValueError(f"현재 문제 상태가 완료 가능하지 않습니다: {current.get('status')}")
        if current.get("kind") == "platform" and not args.accepted:
            raise ValueError("플랫폼 문제는 정답 통과 확인을 위해 --accepted가 필요합니다.")
        solution = repo_path(repo, args.solution_path, must_exist=True)
        workspace = validate_workspace(repo, str(current["workspace_path"]))
        if not workspace.exists():
            raise ValueError(f"풀이 경로가 없습니다: {current['workspace_path']}")
        try:
            solution.relative_to(workspace)
        except ValueError as exc:
            raise ValueError("풀이 경로는 오늘 문제 작업 경로 내부여야 합니다.") from exc
        compile_java(solution)
        progress = load_progress(repo)
        if any(item.get("id") == current.get("id") for item in progress["completed"]):
            current["status"] = "completed"; atomic_write_json(current_path(repo), current)
            print("이미 완료 처리된 문제입니다.")
            return 0
        achievements = read_json(repo / "roadmap/achievements.json", ACHIEVEMENTS_DEFAULT)
        xp, sr_delta, reasons = calculate_reward(current, args)
        next_progress = json.loads(json.dumps(progress))
        next_progress["xp"] += xp
        next_progress["sr"] = max(0, min(1000, next_progress["sr"] + sr_delta))
        next_progress["placement_completed"] = min(6, next_progress["placement_completed"] + 1)
        next_progress["streak"] += 1
        next_progress["weekly"]["attempted"] = next_progress["weekly"].get("attempted", 0) + 1
        next_progress["weekly"]["completed"] = next_progress["weekly"].get("completed", 0) + 1
        next_progress["last_activity"] = datetime.now(KST).isoformat()
        topic = str(current.get("topic", "unknown"))
        next_progress["topics"].setdefault(topic, {"completed": 0, "failed": 0})
        next_progress["topics"][topic]["completed"] += 1
        record = {
            "id": current["id"], "title": current["title"], "platform": current["platform"],
            "topic": topic, "difficulty": current.get("difficulty"), "xp": xp, "sr_delta": sr_delta,
            "elapsed_minutes": args.elapsed_minutes, "hints": current.get("hint_count", 0),
            "completed_at": datetime.now(KST).isoformat(),
        }
        next_progress["completed"].append(record)
        next_achievements = json.loads(json.dumps(achievements))
        new_badges = unlock_achievements(next_progress, next_achievements, topic)
        old_progress = (repo / "roadmap/progress.json").read_bytes()
        old_achievements = (repo / "roadmap/achievements.json").read_bytes()
        review_path = repo / "roadmap/review-log.md"
        old_review = review_path.read_bytes() if review_path.exists() else None
        try:
            atomic_write_json(repo / "roadmap/progress.json", next_progress)
            atomic_write_json(repo / "roadmap/achievements.json", next_achievements)
            append_review(repo, current, args.elapsed_minutes)
            sha = url = ""
            if not args.no_git:
                allow = [solution, repo / "roadmap/progress.json", repo / "roadmap/achievements.json"]
                if review_path.exists(): allow.append(review_path)
                sha, url = run_git(repo, current, allow, push=not args.no_push)
        except Exception:
            (repo / "roadmap/progress.json").write_bytes(old_progress)
            (repo / "roadmap/achievements.json").write_bytes(old_achievements)
            if old_review is not None: review_path.write_bytes(old_review)
            raise
        current["status"] = "completed"
        current["completed_at"] = datetime.now(KST).isoformat()
        atomic_write_json(current_path(repo), current)
    tier, real = tier_info(next_progress["sr"], next_progress["placement_completed"])
    print("╔════════ QUEST CLEAR ════════╗")
    print(f"  {current['title']} 완료")
    print(f"  +{xp} XP · SR +{sr_delta}")
    if new_badges: print("  새 배지: " + ", ".join(new_badges))
    print("╚═════════════════════════════╝")
    print(f"[{tier}] 실제 수준: {real} · XP {next_progress['xp']} · SR {next_progress['sr']}")
    print("보상: " + ", ".join(reasons))
    if not args.no_git:
        print(f"커밋: {sha}")
        if url: print(f"GitHub: {url}")
    return 0


def command_hint(repo: Path, _args: argparse.Namespace) -> int:
    with state_lock(repo):
        current = current_state(repo)
        if not current or current.get("status") != "pending": raise ValueError("힌트를 받을 진행 중 문제가 없습니다.")
        hints = current.get("hints") or ["제한 조건을 다시 확인해 보세요."]
        count = int(current.get("hint_count", 0))
        hint = str(hints[min(count, len(hints) - 1)]).replace("class ", "")
        current["hint_count"] = count + 1
        atomic_write_json(current_path(repo), current)
    print(f"힌트 {count + 1}단계: {hint}")
    return 0


def command_fail(repo: Path, args: argparse.Namespace) -> int:
    with state_lock(repo):
        current = current_state(repo)
        if not current or current.get("status") != "pending": raise ValueError("실패 기록할 진행 중 문제가 없습니다.")
        progress = load_progress(repo)
        progress["xp"] += 20
        progress["sr"] = max(0, progress["sr"] - 8)
        progress["weekly"]["attempted"] = progress["weekly"].get("attempted", 0) + 1
        progress["last_activity"] = datetime.now(KST).isoformat()
        if args.elapsed_minutes >= 30: progress["streak"] += 1
        topic = str(current.get("topic", "unknown"))
        progress["topics"].setdefault(topic, {"completed": 0, "failed": 0})
        progress["topics"][topic]["failed"] += 1
        progress["failed"].append({
            "id": current["id"], "title": current["title"], "elapsed_minutes": args.elapsed_minutes,
            "sr_delta": -8, "recovery_quest": f"{topic} 유형 쉬운 문제 재도전", "failed_at": datetime.now(KST).isoformat(),
        })
        atomic_write_json(repo / "roadmap/progress.json", progress)
        current["status"] = "failed"; atomic_write_json(current_path(repo), current)
    print("도전 기록 완료 · +20 XP · 회복 퀘스트가 생성됐습니다.")
    return 0


def set_suppressed(repo: Path, status: str) -> int:
    with state_lock(repo):
        current = current_state(repo)
        if not current: raise ValueError("현재 문제가 없습니다.")
        current["status"] = status; atomic_write_json(current_path(repo), current)
    print("휴식 처리 완료" if status == "rest" else "다음 학습일로 이월했습니다.")
    return 0


def build_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--repo", default=str(Path(__file__).resolve().parents[1]))
    sub = parser.add_subparsers(dest="command", required=True)
    sub.add_parser("init")
    start = sub.add_parser("start")
    start.add_argument("--json"); start.add_argument("--replace", action="store_true")
    for name in ("id", "title", "platform", "url", "difficulty", "topic", "target-minutes", "kind", "workspace-path"):
        start.add_argument("--" + name, dest=name.replace("-", "_"))
    status = sub.add_parser("status"); status.add_argument("--json", action="store_true")
    sub.add_parser("reminder")
    complete = sub.add_parser("complete")
    complete.add_argument("--accepted", action="store_true")
    complete.add_argument("--solution-path", required=True)
    complete.add_argument("--elapsed-minutes", type=int)
    complete.add_argument("--hints", type=int)
    complete.add_argument("--explained", action="store_true")
    complete.add_argument("--retry", action="store_true")
    complete.add_argument("--review", action="store_true")
    complete.add_argument("--no-git", action="store_true")
    complete.add_argument("--no-push", action="store_true")
    sub.add_parser("hint")
    fail = sub.add_parser("fail"); fail.add_argument("--elapsed-minutes", type=int, required=True)
    sub.add_parser("rest"); sub.add_parser("defer")
    return parser


def main(argv: list[str] | None = None) -> int:
    args = build_parser().parse_args(argv)
    repo = Path(args.repo).expanduser().resolve()
    repo.mkdir(parents=True, exist_ok=True)
    commands = {
        "init": command_init, "start": command_start, "status": command_status,
        "reminder": command_reminder, "complete": command_complete, "hint": command_hint,
        "fail": command_fail,
        "rest": lambda r, a: set_suppressed(r, "rest"),
        "defer": lambda r, a: set_suppressed(r, "deferred"),
    }
    try:
        return commands[args.command](repo, args)
    except (ValueError, OSError, subprocess.SubprocessError, json.JSONDecodeError) as exc:
        print(f"오류: {exc}", file=sys.stderr)
        return 2


if __name__ == "__main__":
    raise SystemExit(main())

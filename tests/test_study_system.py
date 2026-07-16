import json
import os
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest

SOURCE = Path(__file__).parents[1] / "scripts" / "study_system.py"


class StudySystemCLITest(unittest.TestCase):
    def setUp(self):
        self.tmp = tempfile.TemporaryDirectory()
        self.repo = Path(self.tmp.name)
        (self.repo / "scripts").mkdir()
        (self.repo / "roadmap").mkdir()
        (self.repo / ".study").mkdir()

    def tearDown(self):
        self.tmp.cleanup()

    def run_cli(self, *args, check=True):
        cp = subprocess.run(
            [sys.executable, str(SOURCE), "--repo", str(self.repo), *args],
            text=True, capture_output=True,
        )
        if check and cp.returncode:
            self.fail(f"command failed: {cp.args}\nstdout={cp.stdout}\nstderr={cp.stderr}")
        return cp

    def init(self):
        self.run_cli("init")

    def start(self, **overrides):
        check = overrides.pop("_check", True)
        values = {
            "id": "p-1", "title": "테스트 문제", "platform": "programmers",
            "url": "https://example.test/p-1", "difficulty": "lv2",
            "topic": "graph", "target_minutes": "45", "kind": "platform",
            "workspace_path": "solutions/p-1",
        }
        values.update(overrides)
        args = ["start"]
        for key, value in values.items():
            args += ["--" + key.replace("_", "-"), str(value)]
        return self.run_cli(*args, check=check)

    def read_json(self, rel):
        return json.loads((self.repo / rel).read_text(encoding="utf-8"))

    def test_init_creates_required_defaults(self):
        self.init()
        profile = self.read_json("roadmap/study-profile.json")
        progress = self.read_json("roadmap/progress.json")
        achievements = self.read_json("roadmap/achievements.json")
        self.assertEqual((profile["weeks"], profile["sessions_per_week"], profile["language"]), (8, 6, "Java"))
        self.assertEqual(profile["schedule"], {"morning": "08:00", "reminder": "20:00", "timezone": "Asia/Seoul"})
        self.assertEqual(profile["source_ratio"], {"platform": 80, "generated": 20})
        self.assertEqual(profile["placement_sessions"], 6)
        self.assertEqual(progress["sr"], 450)
        for key in ("completed", "failed", "xp", "placement_completed", "streak", "weekly", "topics", "last_activity"):
            self.assertIn(key, progress)
        self.assertEqual(achievements["unlocked"], [])

    def test_start_message_and_pending_collision(self):
        self.init()
        first = self.start()
        for text in ("오늘의 코테", "UNRANKED", "XP", "SR", "주간", "연속", "테스트 문제", "보상", "complete"):
            self.assertIn(text, first.stdout)
        current = self.read_json(".study/current.json")
        self.assertEqual(current["status"], "pending")
        second = self.start(id="p-2", _check=False)
        self.assertNotEqual(second.returncode, 0)
        self.assertEqual(self.read_json(".study/current.json")["id"], "p-1")

    def test_start_json_and_replace(self):
        self.init(); self.start()
        metadata = self.repo / "problem.json"
        metadata.write_text(json.dumps({
            "id": "g-2", "title": "창작", "platform": "local", "url": "-",
            "difficulty": "lv1", "topic": "array", "target_minutes": 30,
            "kind": "generated", "workspace_path": "generated/g-2",
            "hints": ["입출력 범위를 다시 보세요", "상태를 작게 나누세요"]
        }), encoding="utf-8")
        cp = self.run_cli("start", "--json", str(metadata), "--replace")
        self.assertIn("창작", cp.stdout)
        self.assertEqual(self.read_json(".study/current.json")["id"], "g-2")

    def test_rejects_outside_workspace_and_symlink_escape(self):
        self.init()
        cp = self.start(workspace_path="../outside", _check=False)
        self.assertNotEqual(cp.returncode, 0)
        outside = Path(self.tmp.name).parent / "outside-target"
        outside.mkdir(exist_ok=True)
        (self.repo / "escape").symlink_to(outside, target_is_directory=True)
        cp = self.start(workspace_path="escape/problem", _check=False)
        self.assertNotEqual(cp.returncode, 0)

    def test_rejects_root_and_reserved_workspace(self):
        self.init()
        for value in (".", "templates/problem", "roadmap/problem", ".study/problem", "scripts/problem"):
            cp = self.start(workspace_path=value, _check=False)
            self.assertNotEqual(cp.returncode, 0, value)
        self.assertFalse((self.repo / ".study/current.json").exists())

    def test_init_rejects_symlinked_state_directories(self):
        outside = self.repo.parent / f"{self.repo.name}-outside-state"
        outside.mkdir(exist_ok=True)
        self.addCleanup(lambda: __import__("shutil").rmtree(outside, ignore_errors=True))
        (self.repo / "roadmap").rmdir()
        (self.repo / "roadmap").symlink_to(outside, target_is_directory=True)
        cp = self.run_cli("init", check=False)
        self.assertNotEqual(cp.returncode, 0)
        self.assertFalse((outside / "progress.json").exists())

    def test_complete_xp_sr_idempotency_and_generated_acceptance(self):
        self.init()
        solution_dir = self.repo / "generated/g-1"
        solution_dir.mkdir(parents=True)
        (solution_dir / "Main.java").write_text("class Main {}", encoding="utf-8")
        self.start(id="g-1", kind="generated", platform="local", difficulty="lv2", workspace_path="generated/g-1")
        cp = self.run_cli("complete", "--solution-path", "generated/g-1", "--elapsed-minutes", "40", "--explained", "--retry", "--review", "--no-git")
        progress = self.read_json("roadmap/progress.json")
        # attempt 20 + lv2 100 + no hint 30 + within 20 + explained 20 + retry 50 + review 40
        self.assertEqual(progress["xp"], 280)
        self.assertEqual(progress["sr"], 465)  # appropriate +12, within +3
        self.assertEqual(progress["placement_completed"], 1)
        self.assertIn("완료", cp.stdout)
        again = self.run_cli("complete", "--solution-path", "generated/g-1", "--no-git")
        self.assertIn("이미 완료", again.stdout)
        self.assertEqual(self.read_json("roadmap/progress.json")["xp"], 280)

    def test_platform_requires_accepted(self):
        self.init(); (self.repo / "solutions/p-1").mkdir(parents=True)
        (self.repo / "solutions/p-1/Main.java").write_text("class Main {}", encoding="utf-8")
        self.start()
        cp = self.run_cli("complete", "--solution-path", "solutions/p-1", "--no-git", check=False)
        self.assertNotEqual(cp.returncode, 0)
        self.assertEqual(self.read_json(".study/current.json")["status"], "pending")

    def test_compile_failure_has_zero_state_and_git_effect(self):
        self.init()
        subprocess.run(["git", "init", "-q"], cwd=self.repo, check=True)
        subprocess.run(["git", "config", "user.email", "t@example.com"], cwd=self.repo, check=True)
        subprocess.run(["git", "config", "user.name", "T"], cwd=self.repo, check=True)
        bad = self.repo / "solutions/bad"
        bad.mkdir(parents=True)
        (bad / "Main.java").write_text("class Main { syntax error }", encoding="utf-8")
        self.start(id="bad", workspace_path="solutions/bad")
        before = (self.repo / "roadmap/progress.json").read_bytes()
        cp = self.run_cli("complete", "--accepted", "--solution-path", "solutions/bad", check=False)
        self.assertNotEqual(cp.returncode, 0)
        self.assertEqual(before, (self.repo / "roadmap/progress.json").read_bytes())
        staged = subprocess.run(["git", "diff", "--cached", "--name-only"], cwd=self.repo, text=True, capture_output=True, check=True).stdout
        self.assertEqual(staged, "")
        self.assertEqual(self.read_json(".study/current.json")["status"], "pending")

    def test_fail_hint_rest_defer(self):
        self.init(); self.start()
        hint = self.run_cli("hint")
        self.assertIn("힌트 1단계", hint.stdout)
        self.assertNotIn("class ", hint.stdout)
        self.assertEqual(self.read_json(".study/current.json")["hint_count"], 1)
        self.run_cli("fail", "--elapsed-minutes", "35")
        p = self.read_json("roadmap/progress.json")
        self.assertEqual(p["xp"], 20)
        self.assertEqual(p["sr"], 442)
        self.assertTrue(p["failed"][-1]["recovery_quest"])
        self.start(id="rest", workspace_path="solutions/rest", replace="") if False else None
        self.run_cli("rest")
        self.assertEqual(self.read_json(".study/current.json")["status"], "rest")
        # replace a suppressed current, then defer it
        self.start(id="d", workspace_path="solutions/d")
        self.run_cli("start", "--id", "d", "--title", "D", "--platform", "local", "--url", "-", "--difficulty", "lv1", "--topic", "array", "--target-minutes", "30", "--kind", "generated", "--workspace-path", "solutions/d", "--replace")
        self.run_cli("defer")
        self.assertEqual(self.read_json(".study/current.json")["status"], "deferred")

    def test_tier_boundaries_and_unranked(self):
        import importlib.util
        spec = importlib.util.spec_from_file_location("study_system", SOURCE)
        module = importlib.util.module_from_spec(spec); spec.loader.exec_module(module)
        self.assertEqual(module.tier_label(450, 5), "UNRANKED")
        expected = {0:"BRONZE IV", 299:"BRONZE I", 300:"SILVER IV", 449:"SILVER I", 450:"GOLD IV", 599:"GOLD I", 600:"PLATINUM IV", 749:"PLATINUM I", 750:"DIAMOND IV", 879:"DIAMOND I", 880:"MASTER IV", 1000:"MASTER I"}
        for sr, label in expected.items():
            self.assertEqual(module.tier_label(sr, 6), label)

    def test_status_json_and_weekly_report(self):
        self.init(); self.start()
        cp = self.run_cli("status", "--json")
        data = json.loads(cp.stdout)
        self.assertEqual(data["current"]["id"], "p-1")
        self.assertIn("tier", data)
        text = self.run_cli("status").stdout
        self.assertIn("주간", text); self.assertIn("실제 수준", text)

    def test_git_allowlist_preserves_unrelated_and_existing_staged(self):
        self.init()
        subprocess.run(["git", "init", "-q"], cwd=self.repo, check=True)
        subprocess.run(["git", "config", "user.email", "t@example.com"], cwd=self.repo, check=True)
        subprocess.run(["git", "config", "user.name", "T"], cwd=self.repo, check=True)
        (self.repo / ".gitignore").write_text(".study/current.json\n.study/*.lock\n", encoding="utf-8")
        (self.repo / "keep.txt").write_text("base", encoding="utf-8")
        subprocess.run(["git", "add", ".gitignore", "keep.txt", "roadmap/progress.json", "roadmap/achievements.json"], cwd=self.repo, check=True)
        subprocess.run(["git", "commit", "-qm", "base"], cwd=self.repo, check=True)
        (self.repo / "unrelated.txt").write_text("dirty", encoding="utf-8")
        (self.repo / "keep.txt").write_text("staged user change", encoding="utf-8")
        subprocess.run(["git", "add", "keep.txt"], cwd=self.repo, check=True)
        sol = self.repo / "solutions/p-1"; sol.mkdir(parents=True)
        (sol / "Main.java").write_text("class Main {}", encoding="utf-8")
        self.start()
        cp = self.run_cli("complete", "--accepted", "--solution-path", "solutions/p-1", "--elapsed-minutes", "30", "--no-push")
        self.assertIn("커밋", cp.stdout)
        changed = subprocess.run(["git", "show", "--pretty=format:", "--name-only", "HEAD"], cwd=self.repo, text=True, capture_output=True, check=True).stdout.splitlines()
        self.assertIn("solutions/p-1/Main.java", changed)
        self.assertNotIn("keep.txt", changed); self.assertNotIn("unrelated.txt", changed)
        staged = subprocess.run(["git", "diff", "--cached", "--name-only"], cwd=self.repo, text=True, capture_output=True, check=True).stdout.splitlines()
        self.assertIn("keep.txt", staged)

    def test_push_failure_rolls_back_head_progress_and_current(self):
        self.init()
        subprocess.run(["git", "init", "-q"], cwd=self.repo, check=True)
        subprocess.run(["git", "config", "user.email", "t@example.com"], cwd=self.repo, check=True)
        subprocess.run(["git", "config", "user.name", "T"], cwd=self.repo, check=True)
        (self.repo / ".gitignore").write_text(".study/current.json\n.study/*.lock\n", encoding="utf-8")
        subprocess.run(["git", "add", ".gitignore", "roadmap/progress.json", "roadmap/achievements.json"], cwd=self.repo, check=True)
        subprocess.run(["git", "commit", "-qm", "base"], cwd=self.repo, check=True)
        before_head = subprocess.run(["git", "rev-parse", "HEAD"], cwd=self.repo, text=True, capture_output=True, check=True).stdout.strip()
        before_progress = (self.repo / "roadmap/progress.json").read_bytes()
        subprocess.run(["git", "remote", "add", "origin", str(self.repo / "missing-remote")], cwd=self.repo, check=True)
        sol = self.repo / "solutions/p-1"; sol.mkdir(parents=True)
        (sol / "Main.java").write_text("class Main {}", encoding="utf-8")
        self.start()
        cp = self.run_cli("complete", "--accepted", "--solution-path", "solutions/p-1", check=False)
        self.assertNotEqual(cp.returncode, 0)
        after_head = subprocess.run(["git", "rev-parse", "HEAD"], cwd=self.repo, text=True, capture_output=True, check=True).stdout.strip()
        self.assertEqual(after_head, before_head)
        self.assertEqual((self.repo / "roadmap/progress.json").read_bytes(), before_progress)
        self.assertEqual(self.read_json(".study/current.json")["status"], "pending")


if __name__ == "__main__":
    unittest.main()

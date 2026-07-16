#!/usr/bin/env python3
"""Silent-unless-pending wrapper for Hermes no-agent cron delivery."""
from pathlib import Path
import subprocess
import sys


def main() -> int:
    repo = None
    args = sys.argv[1:]
    if "--repo" in args:
        index = args.index("--repo")
        if index + 1 >= len(args):
            return 2
        repo = args[index + 1]
    else:
        repo = str(Path(__file__).resolve().parents[1])
    script = Path(__file__).resolve().with_name("study_system.py")
    completed = subprocess.run([sys.executable, str(script), "--repo", repo, "reminder"], capture_output=True)
    if completed.stdout:
        sys.stdout.buffer.write(completed.stdout)
    if completed.stderr:
        sys.stderr.buffer.write(completed.stderr)
    return completed.returncode


if __name__ == "__main__":
    raise SystemExit(main())

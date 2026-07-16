import json
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest
from datetime import datetime
from zoneinfo import ZoneInfo

STUDY = Path(__file__).parents[1] / "scripts" / "study_system.py"
REMINDER = Path(__file__).parents[1] / "scripts" / "coding_test_reminder.py"


class ReminderTest(unittest.TestCase):
    def setUp(self):
        self.tmp = tempfile.TemporaryDirectory()
        self.repo = Path(self.tmp.name)
        subprocess.run([sys.executable, str(STUDY), "--repo", str(self.repo), "init"], check=True, capture_output=True)

    def tearDown(self):
        self.tmp.cleanup()

    def reminder(self):
        return subprocess.run([sys.executable, str(REMINDER), "--repo", str(self.repo)], text=True, capture_output=True)

    def write_current(self, status="pending", date=None):
        study = self.repo / ".study"; study.mkdir(exist_ok=True)
        (study / "current.json").write_text(json.dumps({
            "id": "1", "title": "오늘 문제", "status": status,
            "date": date or datetime.now(ZoneInfo("Asia/Seoul")).date().isoformat(),
            "target_minutes": 45,
        }), encoding="utf-8")

    def test_only_todays_pending_prints_korean_reminder_verbatim(self):
        self.write_current()
        direct = subprocess.run([sys.executable, str(STUDY), "--repo", str(self.repo), "reminder"], text=True, capture_output=True)
        wrapped = self.reminder()
        self.assertEqual(wrapped.returncode, 0)
        self.assertEqual(wrapped.stdout, direct.stdout)
        self.assertIn("아직 미완료", wrapped.stdout)
        self.assertIn("힌트", wrapped.stdout)

    def test_suppressed_states_and_no_current_are_exactly_silent(self):
        self.assertEqual(self.reminder().stdout, "")
        for status in ("completed", "rest", "deferred"):
            self.write_current(status)
            cp = self.reminder()
            self.assertEqual(cp.returncode, 0)
            self.assertEqual(cp.stdout, "")
        self.write_current("pending", "2000-01-01")
        self.assertEqual(self.reminder().stdout, "")


if __name__ == "__main__":
    unittest.main()

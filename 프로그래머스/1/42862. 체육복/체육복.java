import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);

        boolean[] isExist = new boolean[n];
        boolean[] lostFlag = new boolean[n];
        boolean[] reserveFlag = new boolean[n];

        Arrays.fill(isExist, true);

        // lost 표시
        for (int l : lost) {
            lostFlag[l - 1] = true;
        }

        // reserve 처리 (lost와 겹치는 학생 제거)
        for (int r : reserve) {
            int idx = r - 1;
            if (lostFlag[idx]) {
                // 겹치는 학생 → 그냥 평범한 학생으로 만들기
                lostFlag[idx] = false;
            } else {
                reserveFlag[idx] = true;
            }
        }

        // 진짜 체육복 없는 애들만 false로
        for (int i = 0; i < n; i++) {
            if (lostFlag[i]) {
                isExist[i] = false;
            }
        }

        // 여벌 있는 애들이 왼쪽/오른쪽에게 빌려줌
        for (int i = 0; i < n; i++) {
            if (!reserveFlag[i]) continue;

            if (i - 1 >= 0 && !isExist[i - 1]) {
                isExist[i - 1] = true;
                continue;
            }

            if (i + 1 < n && !isExist[i + 1]) {
                isExist[i + 1] = true;
                continue;
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (isExist[i]) answer++;
        }

        return answer;
    }
}

import java.util.*;
class Solution {
    //               U, D, R, L
    int[] directX = {0, 0, 1, -1};
    int[] directY = {1, -1, 0, 0};

    public int solution(String dirs) {
        int answer = 0;
        int x1 = 5;
        int y1 = 5;
        boolean[][][][] visited = new boolean[11][11][11][11];
        for (char dir : dirs.toCharArray()) {
            int direct = 0;
            switch (dir) {
                case 'U': direct = 0; break;
                case 'D': direct = 1; break;
                case 'R': direct = 2; break;
                case 'L': direct = 3; break;
            };
            int x2 = x1 + directX[direct];
            int y2 = y1 + directY[direct];

            if (x2 < 0 || x2 > 10 || y2 < 0 || y2 > 10) continue;
            if (!visited[y1][x1][y2][x2] && !visited[y2][x2][y1][x1]) {
                visited[y1][x1][y2][x2] = true;
                visited[y2][x2][y1][x1] = true;
                answer++;
            }

            x1 = x2;
            y1 = y2;
        }
        return answer;
    }
}
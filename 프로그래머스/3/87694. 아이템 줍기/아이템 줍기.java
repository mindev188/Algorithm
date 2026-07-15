import java.util.*;
class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        characterX *= 2; characterY *= 2; itemX *= 2; itemY *= 2;

        int [][] board = new int[102][102];
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2, x2 = rect[2] * 2, y2 = rect[3] * 2;
            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    board[y][x] = 1;
                }
            }
        }

        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2, x2 = rect[2] * 2, y2 = rect[3] * 2;
            for (int y = y1 + 1; y < y2; y++) {
                for (int x = x1 + 1; x < x2; x++) {
                    board[y][x] = 0;
                }
            }
        }
        boolean[][] visited = new boolean[102][102];
        visited[characterY][characterX] = true;

        int[][] count = new int[102][102];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{characterY, characterX});
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentY = current[0], currentX = current[1];
            int currentCount = count[currentY][currentX];

            if (currentX == itemX && currentY == itemY) {
                return currentCount / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = currentX, nextY = currentY;
                switch (i) {
                    case 0: nextX++; break;
                    case 1: nextX--; break;
                    case 2: nextY++; break;
                    case 3: nextY--; break;
                }
                if (nextX >= 0 && nextX < 102 && nextY >= 0 && nextY < 102 && board[nextY][nextX] == 1 && !visited[nextY][nextX]) {
                    visited[nextY][nextX] = true;
                    count[nextY][nextX] = currentCount + 1;
                    queue.offer(new int[]{nextY, nextX});
                }
            }
        }

        return 0;
    }
}

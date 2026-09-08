import java.util.*;

class Solution {
    static final int[] DY = {0, 0, 1, -1};
    static final int[] DX = {1, -1, 0, 0};

    public int orangesRotting(int[][] grid) {
        // BFS (최소 시간 구하는 문제)
        // O(n * m)
        // 전체 썩은 오렌지 부터 시작해서 1이 없어질 때 까지의 최소값을 구해야 함.
        // queue 안에 인덱스를 넣으면 다음 depth 를 어떻게 저장하지

        int answer = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                int orange = grid[i][j];
                if (orange != 2) continue;

                queue.offer(new int[]{i, j}); // 2인 값들을 전부 집어넣음
            }
        }

        // 각 시작 값에서 1씩 증가 해야함...
        int heigth = grid.length;
        int width = grid[0].length;
        int[][] visited = new int[grid.length][grid[0].length];
        while (queue.isEmpty() == false) {
            int[] currentIndex = queue.poll();
            int currentY = currentIndex[0];
            int currentX = currentIndex[1];

            for (int k = 0; k < 4; k++) {

                int nextY = currentY + DY[k];
                int nextX = currentX + DX[k];
                if (nextY < 0 || nextY >= heigth || nextX < 0 || nextX >= width) continue;

                int nextOrange = grid[nextY][nextX];
                if (nextOrange != 1) continue;
                grid[nextY][nextX] = 0;

                queue.offer(new int[]{nextY, nextX});
                int depth = visited[currentY][currentX] + 1;
                visited[nextY][nextX] = depth;
                answer = Math.max(answer, depth);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }

        return answer;
    }
}

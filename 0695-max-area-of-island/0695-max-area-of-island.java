class Solution {
    private static final int[] DY = {0, 0, 1, -1};
    private static final int[] DX = {1, -1, 0, 0};

    public int maxAreaOfIsland(int[][] grid) {
        // 이중 for문 사용, O(m * n)
        // DFS로 전체 1을 찾아 0으로 반환, 총 깊이가 해당 섬의 값
        int answer = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) continue;

                grid[i][j] = 0;
                answer = Math.max(answer, dfs(grid, i, j, 1));
            }
        }
        return answer;
    }

    private int dfs(int[][] grid, int currentY, int currentX, int count) {
        int height = grid.length;
        int width = grid[0].length;

        for (int i = 0; i < 4; i++) {
            int nextY = currentY + DY[i];
            int nextX = currentX + DX[i];

            if (nextY < 0 || nextY >= height || nextX < 0 || nextX >= width) continue;
            if (grid[nextY][nextX] == 0) continue;

            grid[nextY][nextX] = 0;
            count = Math.max(count, dfs(grid, nextY, nextX, count + 1));
        }

        return count;
    }
}
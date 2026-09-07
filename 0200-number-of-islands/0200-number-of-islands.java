class Solution {
    private static final int[] yDirection = {0, 0, 1, -1};
    private static final int[] xDirection = {1, -1, 0, 0};

    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int answer = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') continue;
                if (visited[i][j]) continue;

                answer++;
                visited[i][j] = true;
                dfs(grid, visited, i, j);
            }
        }

        return answer;
    }

    private void dfs(char[][] grid, boolean[][] visited, int currentY, int currentX) {
        int maxHeight = grid.length - 1;
        int maxWidth = grid[0].length - 1;

        for (int i = 0; i < 4; i++) {
            int targetY = currentY + yDirection[i];
            int targetX = currentX + xDirection[i];

            if (targetX < 0 || targetX > maxWidth || targetY < 0 || targetY > maxHeight) continue;
            if (grid[targetY][targetX] == '0') continue;
            if (visited[targetY][targetX]) continue;

            visited[targetY][targetX] = true;
            dfs(grid, visited, targetY, targetX);
        }
    }
}
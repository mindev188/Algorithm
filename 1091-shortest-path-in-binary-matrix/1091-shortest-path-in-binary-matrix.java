class Solution {
    private static final int[] YD = {1, -1, 0, 0, 1, 1, -1, -1};
    private static final int[] XD = {0, 0, -1, 1, -1, 1, 1, -1};

    public int shortestPathBinaryMatrix(int[][] grid) {

        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length -1] == 1) return -1;

        if (grid.length == 1) return 1;

        int[][] depth = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                depth[i][j] = Integer.MAX_VALUE;
            }
        }
        depth[0][0] = 1;
        grid[0][0] = 1;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0});

        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] currentIndex = queue.poll();
            int currentY = currentIndex[0];
            int currentX = currentIndex[1];
            int currentDepth = depth[currentY][currentX];

            for (int i = 0; i < 8; i++) {
                int nextY = currentY + YD[i];
                int nextX = currentX + XD[i];

                if (nextY < 0 || nextY >= grid.length || nextX < 0 || nextX >= grid[0].length) continue;
                if (grid[nextY][nextX] == 1) continue;

                if (nextY == grid.length - 1 && nextX == grid[0].length - 1) {
                    answer = Math.min(answer, currentDepth + 1);
                } else {
                    queue.offer(new int[]{nextY, nextX});
                    depth[nextY][nextX] = currentDepth + 1;
                    grid[nextY][nextX] = 1;
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
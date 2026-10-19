class Solution {
    static final private int[] DY = {1, -1, 0, 0};
    static final private int[] DX = {0, 0, 1, -1};

    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] distance = new int[rows][cols];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[0][0] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[2], b[2])
        );

        queue.offer(new int[] {0, 0, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentY = current[0];
            int currentX = current[1];
            int currentEffort = current[2];

            if (currentEffort > distance[currentY][currentX]) continue;

            if (currentY == rows - 1 && currentX == cols - 1) return currentEffort;

            for (int i = 0; i < 4; i++) {
                int nextY = currentY + DY[i];
                int nextX = currentX + DX[i];

                if (nextY < 0 || nextY >= rows || nextX < 0 || nextX >= cols) continue;

                int heightDifference = Math.abs(heights[currentY][currentX] - heights[nextY][nextX]);

                int nextEffort = Math.max(currentEffort, heightDifference);

                if (nextEffort < distance[nextY][nextX]) {
                    distance[nextY][nextX] = nextEffort;
                    queue.offer(new int[] {nextY, nextX, nextEffort});
                }
            }
        }

        return 0;
    }
}
class Solution {
    int[] DY = {0, 0, 1, -1};
    int[] DX = {1, -1, 0, 0};
    /*
        각 시작점에서 가까운 0 까지의 거리
        -> 최소 거리를 구해야 함. DFS vs BFS -> BFS
     */
    public int[][] updateMatrix(int[][] mat) {
        Deque<int[]> queue = new ArrayDeque<>();
        int[][] result = new int[mat.length][mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                int current = mat[i][j];
                if (current == 0) {
                    result[i][j] = 0;
                    queue.offer(new int[] {i, j});
                } else {
                    result[i][j] = -1;
                }
            }
        }

        /*
            1. 1인 값들만 queue에 저장
            2. queue 값 소비
            3. queue 상하좌우에 값이 0 이상이면 해당 값 + 1로 저장
         */
        int YLimit = mat.length;
        int XLimit = mat[0].length;
        while (!queue.isEmpty()) {
            int[] currentIdx = queue.poll();
            int currentY = currentIdx[0];
            int currentX = currentIdx[1];

            for (int i = 0; i < 4; i++) {
                int nextY = currentY + DY[i];
                int nextX = currentX + DX[i];

                if (nextY < 0 || nextY >= YLimit || nextX < 0 || nextX >= XLimit) continue;
                if (mat[nextY][nextX] != 1) continue;

                if (result[nextY][nextX] == -1) {
                    result[nextY][nextX] = result[currentY][currentX] + 1;
                    queue.offer(new int[] {nextY, nextX});
                };
            }
        }
        return result;
    }
}
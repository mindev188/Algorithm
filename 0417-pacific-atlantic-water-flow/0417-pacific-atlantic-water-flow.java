class Solution {
    private boolean[][] pacificVisited;
    private boolean[][] atlanticVisited;

    private static final int[] DY = {1, -1, 0, 0};
    private static final int[] DX = {0, 0, 1, -1};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        pacificVisited = new boolean[heights.length][heights[0].length];
        atlanticVisited = new boolean[heights.length][heights[0].length];

        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                if (i > 0 && j > 0) continue;

                pacificVisited[i][j] = true;
                DFS(i, j, heights, 0, pacificVisited);
            }
        }

        int limit = heights.length - 1;
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                if (i < heights.length - 1 && j < heights[i].length - 1) continue;

                atlanticVisited[i][j] = true;
                DFS(i, j, heights, limit, atlanticVisited);
            }
        }

        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    answer.add(list);
                }
            }
        }
        return answer;
    }

    private void DFS(int currentY, int currentX, int[][] heights, int targetIndex, boolean[][] visited) {
        int height = heights.length;
        int width = heights[0].length;
        for (int i = 0; i < 4; i++) {
            int nextY = currentY + DY[i];
            int nextX = currentX + DX[i];

            if (nextY < 0 || nextY > height - 1 || nextX < 0 || nextX > width - 1) continue;
            if (heights[currentY][currentX] > heights[nextY][nextX]) continue;

            if (!visited[nextY][nextX]) {
                visited[nextY][nextX] = true;
                DFS(nextY, nextX, heights, targetIndex, visited);
            }
        }
    }
}
import java.util.*;

class Solution {
    private static final int[] DY = {0, 0, 1, -1};

    private static final int[] DX = {1, -1, 0, 0};

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        List<List<int[]>> emptyShapes = findShape(game_board, 0);
        List<List<int[]>> puzzleShapes = findShape(table, 1);

        boolean[] chooseShape = new boolean[puzzleShapes.size()];
        for (List<int[]> emptyShape : emptyShapes) {
            for (int i = 0; i < puzzleShapes.size(); i++) {
                if (chooseShape[i]) continue;
                if (emptyShape.size() != puzzleShapes.get(i).size()) continue;

                List<int[]> shape = puzzleShapes.get(i);
                for (int j = 0; j < 4; j++) {
                    if (isSame(shape, emptyShape)) {
                        chooseShape[i] = true;
                        answer += emptyShape.size();
                        break;
                    }
                    shape = rotate(shape);
                }
                if (chooseShape[i]) break;
            }
        }

        return answer;
    }

    private List<List<int[]>> findShape(int[][] gameBoard, int target) {
        boolean[][] visited = new boolean[gameBoard.length][gameBoard[0].length];
        List<List<int[]>> shapes = new ArrayList<>();

        List<int[]> shape;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                if (gameBoard[i][j] != target || visited[i][j]) continue;
                shape = new ArrayList<>();
                visited[i][j] = true;
                dfs(gameBoard, visited, i, j, target, shape);

                normalize(shape);
                shapes.add(shape);
            }
        }
        return shapes;
    }

    private void dfs(int[][] gameBoard, boolean[][] visited, int startY, int startX, int target, List<int[]> shape) {
        shape.add(new int[]{startY, startX});

        for (int i = 0; i < 4; i++) {
            int nextX = startX + DX[i];
            int nextY = startY + DY[i];

            if (nextX < 0 || nextX >= gameBoard[0].length || nextY < 0 || nextY >= gameBoard.length) continue;
            if (gameBoard[nextY][nextX] != target || visited[nextY][nextX]) continue;

            visited[nextY][nextX] = true;
            dfs(gameBoard, visited, nextY, nextX, target, shape);
        }
    }

    private void normalize(List<int[]> shape) {
        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        for (int[] index : shape) {
            minY = Math.min(index[0], minY);
            minX = Math.min(index[1], minX);
        }

        for (int[] index : shape) {
            index[0] -= minY;
            index[1] -= minX;
        }

        shape.sort((a, b) -> {
            if (Integer.compare(a[0], b[0]) == 0) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
    }

    private List<int[]> rotate (List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();

        for (int[] index : shape) {
            int x = index[1];
            int y = index[0];
            rotated.add(new int[] {x, -y});
        }
        normalize(rotated);
        return rotated;
    }

    private boolean isSame(List<int[]> listA, List<int[]> listB) {
        if (listA.size() != listB.size()) return false;
        for (int i = 0; i < listA.size(); i++) {
            if (listA.get(i)[0] != listB.get(i)[0]) return false;
            if (listA.get(i)[1] != listB.get(i)[1]) return false;
        }
        return true;
    }
}

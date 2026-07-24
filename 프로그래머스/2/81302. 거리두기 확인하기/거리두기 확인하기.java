import java.util.*;
class Solution {
    int[] directionX = {1, -1, 0, 0};
    int[] directionY = {0, 0, 1, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);

        for (int roomNum = 0; roomNum < places.length; roomNum++) {
            String[] room = places[roomNum];
            int ySize = room.length;
            int xSize = room[0].length();

            Queue<String> queue = new LinkedList<>();
            for (int i = 0; i < ySize; i++) {
                for (int j = 0; j < xSize; j++) {
                    String row = room[i];
                    String place = String.valueOf(row.charAt(j));
                    if (!"P".equals(place)) continue;

                    boolean[][] visited = new boolean[ySize][xSize];
                    visited[i][j] = true;
                    if (dfs(visited, room, j, i, 0)) {
                        answer[roomNum] = 0;
                        break;
                    }
                }
                if (answer[roomNum] == 0) break;
            }
        }

        return answer;
    }

    private boolean dfs(boolean[][] visited, String[] room, int x, int y, int dept) {
        if (dept >= 2) return false;

        for (int i = 0; i < directionX.length; i++) {
            int nextY = y + directionY[i];
            int nextX = x + directionX[i];

            if (nextX >= 0 && nextX < 5 && nextY >= 0 && nextY < 5) {
                if (visited[nextY][nextX] || room[nextY].charAt(nextX) == 'X') continue;
                if (room[nextY].charAt(nextX) == 'P') return true;
                visited[nextY][nextX] = true;
                if (dfs(visited, room, nextX, nextY, dept + 1)) return true;
            }

        }
        return false;
    }
}
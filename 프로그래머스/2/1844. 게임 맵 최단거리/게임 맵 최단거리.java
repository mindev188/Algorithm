import java.util.*;
class Solution {
    int n;
    int m;

    public int solution(int[][] maps) {
        int[][] visited = new int[maps.length][maps[0].length];
        n = maps.length;
        m = maps[0].length;

        // 사방으로 서치하며 일치하는 곳으로 가는지 확인
        bfs(maps, 0, 0, visited);
        int answer = visited[n-1][m-1];
        return answer == 0 ? -1 : answer;
    }

    private void bfs(int[][] maps, int y, int x, int[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        visited[y][x] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = current[0];
                int nextX = current[1];

                switch (i) {
                    // 하
                    case 0: nextY = nextY + 1; break;
                    // 우
                    case 1: nextX = nextX + 1; break;
                    // 좌
                    case 2: nextX = nextX - 1; break;
                    // 상
                    case 3: nextY = nextY - 1; break;
                }
                if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= m
                        || visited[nextY][nextX] != 0
                        || maps[nextY][nextX] == 0)
                { continue; }

                visited[nextY][nextX] = visited[current[0]][current[1]] + 1;
                queue.offer(new int[] {nextY, nextX});
            }
        }

    }
}
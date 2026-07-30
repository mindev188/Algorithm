import java.util.*;

class Solution {
    boolean[] visited;
    Queue<int[]> queue;
    public int solution(int x, int y, int n) {
        queue = new ArrayDeque<>();
        queue.add(new int[] {x, 0});
        visited = new boolean[y + 1];

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int num = arr[0];
            int count = arr[1];
            if (num == y) return count;

            bfs(y, num + n, count);
            bfs(y, num * 2, count);
            bfs(y, num * 3, count);
        }
        return -1;
    }

    private void bfs(int y, int next1, int count) {
        if (next1 <= y && !visited[next1]) {
            visited[next1] = true;
            queue.add(new int[]{next1, count + 1});
        }
    }
}
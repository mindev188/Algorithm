import java.util.*;
class Solution {

    public int solution(int n, int[][] edge) {
        int answer = 0;
        boolean[] visited = new boolean[n + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {1,0});
        visited[1] = true;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int node = arr[0];
            int depth = arr[1];

            if (depth > max) {
                max = depth;
                answer = 1;
            } else if (depth == max) {
                answer++;
            }

            for (int target : graph.get(node)) {
                if (visited[target]) continue;
                visited[target] = true;
                queue.offer(new int[] {target, depth + 1});
            }
        }

        return answer;
    }
}
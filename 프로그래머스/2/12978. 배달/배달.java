import java.util.*;
class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        List<int[]>[] graph = new ArrayList[N];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < road.length; i++) {
            int a = road[i][0] - 1;
            int b = road[i][1] - 1;
            int length = road[i][2];

            graph[a].add(new int[]{b, length});
            graph[b].add(new int[]{a, length});
        }

        int[] visited = new int[N];
        Arrays.fill(visited, -1);
        visited[0] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0,0});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int source = node[0];
            int length = node[1];

            for (int i = 0; i < graph[source].size(); i++) {
                int target = graph[source].get(i)[0];
                int totalLength = length + graph[source].get(i)[1];
                if (totalLength > K) continue;
                if (visited[target] != -1 && visited[target] < totalLength) continue;
                visited[target] = totalLength;
                queue.add(new int[] {target, totalLength});
            }
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i] != -1 && visited[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
}
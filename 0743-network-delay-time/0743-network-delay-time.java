import java.util.*;
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < times.length; i++) {
            int a = times[i][0] - 1;
            int b = times[i][1] - 1;
            int time = times[i][2];
            graph[a].add(new int[] {b, time});
        }

        int[] dept = new int[n];
        Arrays.fill(dept, Integer.MAX_VALUE);
        dept[k - 1] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.offer(new int[] {k - 1, 0});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int current = node[0];
            int time = node[1];

            for (int i = 0; i < graph[current].size(); i++) {
                int nextNode = graph[current].get(i)[0];
                int totalTime = time + graph[current].get(i)[1];

                if (dept[nextNode] > totalTime) {
                    dept[nextNode] = totalTime;
                    queue.offer(new int[] {nextNode, totalTime});
                }
            }
        }

        int result = -1;
        for (int i = 0; i < dept.length; i++) {
            if (dept[i] == Integer.MAX_VALUE) return -1;
            result = Math.max(result, dept[i]);
        }
        return result;
    }
}
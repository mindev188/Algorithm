import java.util.*;
class Solution {

    public int solution(int n, int[][] edge) {
        int max = 0;
        int answer = 0;

        boolean[] visited = new boolean[n + 1];
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(1, 0));
        visited[1] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.depth > max) {
                answer = 1;
                max = node.depth;
            } else if (node.depth == max) {
                answer++;
            }

            List<Integer> list = graph[node.index];
            for (int i : list) {
                if (visited[i]) continue;
                visited[i] = true;
                queue.offer(new Node(i, node.depth + 1));
            }
        }
        return answer;
    }

    class Node {
        int index;
        int depth;

        public Node(int index, int depth) {
            this.index = index;
            this.depth = depth;
        }
    }
}
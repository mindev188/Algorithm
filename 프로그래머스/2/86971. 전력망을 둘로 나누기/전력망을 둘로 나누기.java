import java.util.*;
class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0] - 1;
            int b = wires[i][1] - 1;
            tree[a].add(b);
            tree[b].add(a);
        }

        boolean[] visited;
        for (int i = 0; i < wires.length; i++) {
            visited = new boolean[n];

            int a = wires[i][0] - 1;
            int b = wires[i][1] - 1;
            visited[a] = true;
            visited[b] = true;
            int aDept = dfs(tree, visited, a);
            int bDept = n - aDept;

            if (aDept - bDept == 0) return 0;
            answer = Math.min(Math.abs(aDept - bDept), answer);
        }
        return answer;
    }

    private int dfs(List<Integer>[] tree, boolean[] visited, int a) {
        int total = 1;
        for (int i = 0; i < tree[a].size(); i++) {
            if (visited[tree[a].get(i)]) continue;
            visited[tree[a].get(i)] = true;
            total += dfs(tree, visited, tree[a].get(i));
        }
        return total;
    }
}
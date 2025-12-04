import java.util.Arrays;
import java.util.ArrayList;
class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        Node[] tree = new Node[n + 1];
        for (int i = 0; i < wires.length; i++) {
            int[] wire = wires[i];
            if (tree[wire[0]] == null) tree[wires[i][0]] = new Node();
            if (tree[wire[1]] == null) tree[wires[i][1]] = new Node();

            tree[wire[0]].add(wire[1]);
            tree[wire[1]].add(wire[0]);
        }

        boolean[] visited;
        for (int i = 0; i < wires.length; i++) {
            visited = new boolean[n + 1];
            int a = wires[i][0];
            int b = wires[i][1];

            visited[b] = true;
            int sizeA = dfs(tree, visited, a);
            int sizeB = n - sizeA;

            answer = Math.min(answer, Math.abs(sizeA - sizeB));
        }

        return answer;
    }

    int dfs(Node[] tree, boolean[] visited, int startNum) {
        visited[startNum] = true;
        int count = 1;

        for (int next : tree[startNum].list) {
            if (!visited[next]) {
                count += dfs(tree, visited, next);
            }
        }

        return count;
    }

    class Node {
        ArrayList<Integer> list;

        Node() {
            this.list = new ArrayList<>();
        }

        void add(int num) {
            list.add(num);
        }
    }
}
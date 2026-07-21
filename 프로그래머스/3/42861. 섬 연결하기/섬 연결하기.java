import java.util.*;
class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        Arrays.sort(costs, (a,b)-> {
            if (a[2] == b[2]) return a[0] - b[0];
            return a[2] - b[2];
        });
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        int edgeCount = 0;
        for (int[] cost : costs) {
            int from = cost[0];
            int to = cost[1];
            int weight = cost[2];

            if (union(parents, from, to)) {
                answer += weight;
                edgeCount++;
                if (edgeCount == n - 1) break;
            }
        }

        return answer;
    }

    private boolean union(int[] parents, int a, int b) {
        int rootA = find(parents, a);
        int rootB = find(parents, b);
        if (rootA == rootB) return false;
        parents[rootB] = rootA;
        return true;
    }

    private int find(int[] parents, int a) {
        if (parents[a] == a) return a;
        parents[a] = find(parents, parents[a]);
        return parents[a];
    };
}
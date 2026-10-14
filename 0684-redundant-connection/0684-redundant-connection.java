class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[edges.length + 1];

        for (int node = 1; node <= n; node++) {
            parent[node] = node;
        }

        for (int[] edge : edges) {
            int nodeA = edge[0];
            int nodeB = edge[1];

            int rootA = find(nodeA, parent);
            int rootB = find(nodeB, parent);

            if (rootA == rootB) {
                return edge;
            }

            parent[rootA] = rootB;
        }

        return new int[0];
    }

    private int find(int node, int[] parent) {
        if (parent[node] == node) {
            return node;
        }

        parent[node] = find(parent[node], parent);
        return parent[node];
    }
}
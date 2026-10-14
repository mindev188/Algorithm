class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parents = new int[n];

        for (int node = 0; node < n; node++) {
            parents[node] = node;
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if (i == j || isConnected[i][j] != 1) continue;
                int cityA = find(i, parents);
                int cityB = find(j, parents);

                parents[cityA] = cityB;
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int parent : parents) {
            set.add(find(parent, parents));
        }
        return set.size();
    }

    private int find(int city, int[] parents) {
        if (parents[city] == city) return city;

        parents[city] = find(parents[city], parents);
        return parents[city];
    }
}
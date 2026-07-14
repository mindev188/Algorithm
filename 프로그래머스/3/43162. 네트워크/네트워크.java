class Solution {
    int[] visited;

    public int solution(int n, int[][] computers) {
        visited = new int[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                answer++;
                dfs(i, n, computers);
            }
        }

        return answer;
    }

    private void dfs(int current, int n , int[][] computers) {
        visited[current] = 1;

        for (int next = 0; next < n; next++) {
            if (visited[next] == 0 && computers[current][next] == 1) {
                dfs(next, n, computers);
            }
        }
    }
}

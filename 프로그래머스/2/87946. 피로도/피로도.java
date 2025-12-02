class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = -1;

        boolean[] visited = new boolean[dungeons.length];

        answer = dfs(k, dungeons, visited, 0);

        return answer;
    }

    int dfs(int k, int[][] dungeons, boolean[] visited, int count) {
        int max = count;
        
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;

            if (dungeons[i][0] > k) continue; // 최소피로도 조건
            visited[i] = true; // 탐색 여부

            // 반환된 값이 maxCount 보다 이상인 경우 해당 값으로 초기화
            int result = dfs(k - dungeons[i][1], dungeons, visited, count + 1);
            max = Math.max(max, result);

            visited[i] = false; // 탐색 여부 원복
        }

        return max;
    }
}
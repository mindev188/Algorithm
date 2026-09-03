class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = -1;

        boolean[] visited = new boolean[dungeons.length];
        answer = search(k, dungeons, visited);
        return answer - 1;
    }

    private int search(int status, int[][] dungeons, boolean[] visited) {
        int maxCount = 0;
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i] || status < dungeons[i][0]) continue;

            visited[i] = true;
            maxCount = Math.max(maxCount, search(status - dungeons[i][1], dungeons, visited));
            visited[i] = false;
        }

        return maxCount + 1;
    }
}
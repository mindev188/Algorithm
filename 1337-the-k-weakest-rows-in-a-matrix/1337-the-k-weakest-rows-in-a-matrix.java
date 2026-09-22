class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> pQueue = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) count++;
            }
            pQueue.offer(new int[] {i, count});
        }

        int[] answer = new int[k];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = pQueue.poll()[0];
        }
        return answer;
    }
}
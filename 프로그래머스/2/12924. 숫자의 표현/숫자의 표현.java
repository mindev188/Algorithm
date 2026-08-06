class Solution {
    public int solution(int n) {
        int answer = 1;

        for (int i = 1; i <= n / 2; i++) {
            int total = 0;
            for (int j = i; j < n; j++) {
                total += j;
                if (total == n) {
                    answer++;
                    break;
                }
                
                if (total > n) break;
            }

        }
        return answer;
    }
}
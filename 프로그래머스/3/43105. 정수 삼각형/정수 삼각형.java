
class Solution {
    public int solution(int[][] triangle) {
        int [][] dp = new int[triangle.length][];
        for (int i = 0; i < triangle.length; i++) {
            dp[i] = new int[triangle[i].length];
        }
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < triangle.length; i++) {
            int lastIndex = triangle[i].length - 1;
            for (int j = 0; j < triangle[i].length; j++) {
                int a = 0;
                int b = 0;
                if (j == 0) {
                    b = dp[i - 1][0] + triangle[i][j];
                } else if (j == lastIndex) {
                    a = dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    a = dp[i - 1][j - 1] + triangle[i][j];
                    b = dp[i - 1][j] + triangle[i][j];
                }
                dp[i][j] = Math.max(a, b);
            }
        }

        int answer = 0;
        for (int num : dp[triangle.length - 1]) {
            answer = Math.max(answer, num);
        }
        return answer;
    }
}
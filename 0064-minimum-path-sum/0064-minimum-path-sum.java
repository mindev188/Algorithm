class Solution {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) continue;

                int upSum = Integer.MAX_VALUE;
                int leftSum = Integer.MAX_VALUE;
                if (i != 0) {
                    upSum = dp[i - 1][j] + grid[i][j];
                }
                if (j != 0) {
                    leftSum = dp[i][j - 1] + grid[i][j];
                }
                dp[i][j] = Math.min(upSum, leftSum);
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
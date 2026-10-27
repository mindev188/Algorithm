class Solution {
    public int lengthOfLIS(int[] nums) {
        int answer = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int num : dp) {
            answer = Math.max(answer, num);
        }
        return answer;
    }
}
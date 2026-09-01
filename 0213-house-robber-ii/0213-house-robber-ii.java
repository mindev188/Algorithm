class Solution {

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        // 0 ~ n - 2, 1 ~ n - 1 까지 직선을 나눠서 진행
        int a = robLinear(nums, 0, nums.length - 2);
        int b = robLinear(nums, 1, nums.length - 1);
        return Math.max(a, b);
    }

    private int robLinear(int[] nums, int start, int end) {
        if (start == end) return nums[start];
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }
}
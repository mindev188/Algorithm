class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int answer = nums.length + 1;

        int total = 0;
        int left = 0, right = 0;
        while (right < nums.length && left <= right) {
            if (total < target) {
                total += nums[right++];
            }

            while (total >= target) {
                answer = Math.min(answer, right - left);
                total -= nums[left++];
            }
        }
        return answer == nums.length + 1 ? 0 : answer;
    }
}
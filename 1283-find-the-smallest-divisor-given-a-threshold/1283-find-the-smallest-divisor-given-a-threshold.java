class Solution {
    public int smallestDivisor(int[] nums, int threshold) {

        Arrays.sort(nums);
        int maxNum = nums[nums.length - 1];

        int left = 1;
        int right = maxNum;

        if (threshold == nums.length) {
            return maxNum;
        }

        int minValue = threshold;
        while (left < right) {
            // 제수
            int mid = left + (right - left) / 2;

            int value = 0;
            for (int num : nums) {
                value += (num + mid - 1) / mid;
            }

            // mid 제수값의 합이
            if (value <= minValue) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
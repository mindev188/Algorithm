class Solution {
    public int smallestDivisor(int[] nums, int threshold) {

        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

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
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        int left = 0;
        int total = 1;
        int count = 0;
        for (int right = 0; right < nums.length; right++) {
            total *= nums[right];

            while (left < right && total >= k) {
                total /= nums[left++];
            }

            if (total >= k) continue;

            count += right - left + 1;
        }

        return count;
    }
}
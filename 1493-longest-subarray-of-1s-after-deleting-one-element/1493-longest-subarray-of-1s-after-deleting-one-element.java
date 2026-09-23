class Solution {
    public int longestSubarray(int[] nums) {

        int answer = 0;
        int zeroNum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            int current = nums[right];

            if (current == 0) {
                zeroNum++;
            }

            while (zeroNum > 1) {
                if (nums[left++] == 0) zeroNum--;
            }

            answer = Math.max(answer, right - left);
        }

        return answer;
    }
}
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> set = new HashSet<>();

        int left = 0;
        int answer = 0;
        int total = 0;
        for (int right = 0; right < nums.length; right++) {

            while (set.contains(nums[right])) {
                int num = nums[left++];
                set.remove(num);
                total -= num;
            }

            set.add(nums[right]);
            total += nums[right];

            answer = Math.max(answer, total);
        }

        return answer;
    }
}
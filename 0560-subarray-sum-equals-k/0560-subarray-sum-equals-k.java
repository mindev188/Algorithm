class Solution {
    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int sum = 0;
        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            answer += prefixCount.getOrDefault(sum - k, 0);

            prefixCount.put(sum, prefixCount.getOrDefault(sum, 0) + 1);
        }

        return answer;
    }
}
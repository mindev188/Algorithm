class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            sum += num == 1 ? 1 : -1;

            if (sum == 0) {
                answer = Math.max(answer, i + 1);
            }

            // sum을 0으로 만들 수 있는 값이 이전에 존재하는 경우 해당 인덱스까지가 0 과 1이 동일하게 있는 길이
            int prefixIndex = map.getOrDefault(sum, -1);
            if (prefixIndex != -1) {
                answer = Math.max(answer, i - prefixIndex);
            } else {
                map.put(sum, i);
            }
        }

        return answer;
    }
}
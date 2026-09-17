class Solution {
    @SuppressWarnings("unchecked")
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }



        int[] result = map.entrySet().stream().sorted((a, b) -> {
            return b.getValue() - a.getValue();
        }).limit(k)
                .mapToInt(a -> a.getKey())
                .toArray();
        return result;
    }
}
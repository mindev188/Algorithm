class Solution {
    public int totalFruit(int[] fruits) {

        Map<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int answer = 0;
        int totalCount = 0;
        for (int right = 0; right < fruits.length; right++) {
            int current = fruits[right];

            int count = map.getOrDefault(current, 0);
            if (count == 0) {
                while (map.size() >= 2) {
                    int leftCount = map.getOrDefault(fruits[left], 0) - 1;
                    if (leftCount < 1) {
                        map.remove(fruits[left]);
                    } else {
                        map.put(fruits[left], leftCount);
                    }
                    left++;
                    totalCount--;
                }
            }
            map.put(current, count + 1);
            totalCount++;
            answer = Math.max(answer, totalCount);
        }
        return answer;
    }
}
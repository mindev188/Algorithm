import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < tangerine.length; i++) {
            int size = tangerine[i];
            map.put(size, map.getOrDefault(size, 0) + 1);
        }

        int[][] arrays = map.entrySet().stream().map(entry -> new int[] {entry.getKey(), entry.getValue()}).toArray(int[][]::new);
        Arrays.sort(arrays, (a, b) -> b[1] - a[1]);

        int answer = 0;
        for (int[] array : arrays) {
            int count = array[1];
            answer++;

            if (k <= count) {
                return answer;
            } else {
                k -= count;
            }
        }
        return answer;
    }
}
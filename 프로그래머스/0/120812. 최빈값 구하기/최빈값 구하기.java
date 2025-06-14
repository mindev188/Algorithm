import java.util.*;
class Solution {
    public static int solution(int[] array) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 최빈값을 구하기
        int maxMode = 0;
        int modeValue = 0;
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            int key = iterator.next();
            int value = map.get(key);
            if (value > maxMode) {
                maxMode = value;
                modeValue = key;
            }
        }

        // 만약 최빈값이 2개 이상이면 -1 반환
        int count = 0;
        for (int key : map.keySet()) {
            if (map.get(key) == maxMode) {
                count++;
            }
        }
        return count > 1 ? -1 : modeValue;
    }
}
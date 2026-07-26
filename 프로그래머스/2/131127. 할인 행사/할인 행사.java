import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }

        for (int i = 0; i < discount.length - 9; i++) {
            Map<String, Integer> copyMap = new HashMap<>(map);
            boolean isContains = true;
            for (int j = i; j < i + 10; j++) {
                if (copyMap.containsKey(discount[j])) {
                    int num = copyMap.get(discount[j]) - 1;
                    if (num < 0) {
                        isContains = false;
                        break;
                    }
                    copyMap.put(discount[j], num);
                } else {
                    isContains = false;
                    break;
                }
            }
            if (isContains) answer++;
        };
        return answer;
    }
}
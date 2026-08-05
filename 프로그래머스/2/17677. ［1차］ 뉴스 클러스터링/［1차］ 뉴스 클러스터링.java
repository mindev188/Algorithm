import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = makeMap(str1);
        Map<String, Integer> map2 = makeMap(str2);

        int intersection = 0;
        int union = 0;
        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            int count1 = map1.getOrDefault(key, 0);
            int count2 = map2.getOrDefault(key, 0);

            intersection += Math.min(count1, count2);
            union += Math.max(count1, count2);
        }

        if (union == 0) return 65536;
        return intersection * 65536 / union;
    }

    private Map<String, Integer> makeMap(String str) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String subStr = str.substring(i, i + 2).toUpperCase();

            if (isSpecialChar(subStr)) continue;
            map.put(subStr, map.getOrDefault(subStr, 0) + 1);
        }
        return map;
    }

    boolean isSpecialChar(String str) {
        for (char a : str.toCharArray()) {
            if (((a >= 'A' && a <= 'Z') || (a >= 'a' && a <= 'z')) == false) return true;
        }
        return false;
    }
}
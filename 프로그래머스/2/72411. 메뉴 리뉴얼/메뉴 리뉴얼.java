import java.util.*;
class Solution {
    Map<String, Integer> courseMap;

    /**
     * 1. 각 메뉴 갯수에 맞는 코스 요리 조합을 찾고, 일치하는 갯수만큼 count
     * 2. 각 갯수별 코스 요리 좋 최대 주문 횟수가 같은 조합끼리 추가.
     * 3. 반환
     * @param orders
     * @param course
     * @return
     */
    public String[] solution(String[] orders, int[] course) {
        courseMap = new HashMap<>();

        for (int menuNum : course) {
            for (String menus : orders) {
                if (menus.length() < menuNum) continue;
                char[] arr = menus.toCharArray();
                Arrays.sort(arr);
                combo(arr, 0, new StringBuilder(), menuNum);
            }
        }

        List<String> list = new ArrayList<>();
        for (int menuNum : course) {
            int maxOrderCount = 0;
            for (Map.Entry<String, Integer> entry : courseMap.entrySet()) {
                String combo = entry.getKey();
                int orderCount = entry.getValue();
                if (combo.length() == menuNum &&  orderCount >= 2) {
                    maxOrderCount = Math.max(maxOrderCount, orderCount);
                }
            }

            for (Map.Entry<String, Integer> entry : courseMap.entrySet()) {
                String combo = entry.getKey();
                int orderCount = entry.getValue();
                if (combo.length() == menuNum && orderCount == maxOrderCount) {
                    list.add(combo);
                }
            }
        }

        Collections.sort(list);
        return list.toArray(String[]::new);
    }

    // 각 메뉴별로 재귀를 통해 문자열을 만들어 원하는 코스를 찾는다
    private void combo(char[] menus, int index, StringBuilder courseMenu, int limit) {
        if (courseMenu.length() == limit) {
            String key = courseMenu.toString();
            courseMap.put(key, courseMap.getOrDefault(key, 0) + 1);
            return;
        }

        for (int i = index; i < menus.length; i++) {
            courseMenu.append(menus[i]);
            combo(menus, i + 1, courseMenu, limit);
            courseMenu.deleteCharAt(courseMenu.length() - 1);
        }
    }
}
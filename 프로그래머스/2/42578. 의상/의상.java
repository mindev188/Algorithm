import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> hMap = new HashMap();
        for (String[] clothe : clothes) {
            if (hMap.containsKey(clothe[1])) {
                hMap.put(clothe[1], hMap.getOrDefault(clothe[1], 1) + 1);
            } else {
                hMap.put(clothe[1], 2);
            }
        }

        Iterator<Entry<String,Integer>> iterator = hMap.entrySet().iterator();
        while (iterator.hasNext()) {
            answer *= iterator.next().getValue();
        }
        return answer - 1;
    }
}
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < participant.length; i++) {
            String name = participant[i];
            if (map.containsKey(name)) {
                map.put(name, map.get(name) + 1);
            } else {
                map.put(name, 1);
            }
        }

        for (int i = 0; i < completion.length; i++) {
            String completionName = completion[i];
            if (map.get(completionName) > 1) {
                map.put(completionName, map.get(completionName) - 1);
            } else {
                map.remove(completionName);
            }
        }
        Iterator<Entry<String,Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            answer = (String) iterator.next().getKey();
        }
        return answer;
    }
}
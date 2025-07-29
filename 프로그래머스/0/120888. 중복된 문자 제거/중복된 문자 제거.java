import java.util.LinkedHashSet;
import java.util.Iterator;
class Solution {
    public String solution(String my_string) {
        String answer = "";
        LinkedHashSet<Character> set = new LinkedHashSet<Character>();

        for (char a : my_string.toCharArray()) {
            set.add(a);
        }

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            answer += iterator.next().toString();
        }
        return answer;
    }
}
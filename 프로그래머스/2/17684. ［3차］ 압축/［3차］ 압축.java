import java.util.*;
class Solution {
    public int[] solution(String msg) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add(Character.toString(('A' + i)));
        }

        List<Integer> answerList = new ArrayList<>();
        int i = 0;
        while (i < msg.length()) {
            String a = "" + msg.charAt(i);
            int j = i + 1;
            while (j < msg.length() && list.contains(a + msg.charAt(j))) {
                a = a + msg.charAt(j);
                j++;
            }

            answerList.add(list.indexOf(a) + 1);

            if (j < msg.length()) {
                list.add(a + msg.charAt(j));
            }

            i += a.length();
        }

        int[] answer = answerList.stream().mapToInt(Integer::valueOf).toArray();
        return answer;
    }
}
import java.util.ArrayList;
class Solution {
    public int[] solution(String my_string) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<Integer>();
        /**
         * 문자열 중 숫자만 골라 오름차순
         */
        for (char a : my_string.toCharArray()) {
            if (a + 0 >= 48 && a + 0 <= 57) {
                list.add(a - '0');
            }
        }

        answer = list.stream().mapToInt(Integer::intValue).sorted().toArray();
        return answer;
    }
}
class Solution {
    public String solution(String my_string, String letter) {
        String answer = "";
        for (char a : my_string.toCharArray()) {
            if (letter.equals(a + "")) continue;
            answer += a;
        }
        return answer;
    }
}
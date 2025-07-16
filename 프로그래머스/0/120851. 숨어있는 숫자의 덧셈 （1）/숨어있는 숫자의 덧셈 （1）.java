class Solution {
    public int solution(String my_string) {
        int answer = 0;
        // 47 ~ 57 까지가 char 0 ~ 9 까지다
        for (char a : my_string.toCharArray()) {
            if (a >= 47 && a <= 57) {
                answer += a - '0';
            }
        }
        return answer;
    }
}
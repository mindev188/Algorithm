class Solution {
    public String[] solution(String my_str, int n) {
        int strLength = my_str.length();
        String[] answer = new String[strLength % n == 0 ? strLength / n : strLength / n + 1];

        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < strLength; i++) {
            sb.append(my_str.charAt(i));
            // 문자열이 n개만큼 채워진 경우
            if (sb.length() == n || i + 1 == strLength) {
                answer[j] = sb.toString();
                j++;
                sb = new StringBuilder();
            }
        }
        return answer;
    }
}
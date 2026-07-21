class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char num = number.charAt(i);

            while (sb.length() > 0 && k > 0 && sb.charAt(sb.length() - 1) < num) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(num);
        }

        if (k > 0) {
            sb.delete(sb.length() - k, sb.length());
        }
        return sb.toString();
    }
}
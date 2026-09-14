
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char nextChar = s.charAt(i);

            if (sb.indexOf(nextChar + "") > -1) {
                sb = new StringBuilder(sb.substring(sb.indexOf(nextChar + "") + 1) + nextChar);
            } else {
                sb.append(nextChar + "");
            }

            answer = Math.max(answer, sb.length());
        }
        return answer;
    }
}
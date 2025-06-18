class Solution {
    public static String solution(String my_string, int n) {
        StringBuilder sb = new StringBuilder();
        for (char a : my_string.toCharArray()) { 
            for (int i = 0; i < n; i++) {
                sb.append(a);
            }
        }
        return sb.toString();
    }
}
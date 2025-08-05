class Solution {
    public String solution(String my_string) {
        StringBuilder sb = new StringBuilder();
        for (char a : my_string.toCharArray()) {
            if (Character.isUpperCase(a)) {
                a = Character.toLowerCase(a);
            } else {
                a = Character.toUpperCase(a);
            }
            sb.append(a);
        }
        return sb.toString();
    }
}
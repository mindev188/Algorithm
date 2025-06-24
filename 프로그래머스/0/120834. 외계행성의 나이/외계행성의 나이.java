class Solution {
    public String solution(int age) {
        StringBuilder sb = new StringBuilder();
        String str = String.valueOf(age);
        for(char a : str.toCharArray()) {
            sb.append((char)('a' + (a - '0')));
        }

        return sb.toString();
    }
}
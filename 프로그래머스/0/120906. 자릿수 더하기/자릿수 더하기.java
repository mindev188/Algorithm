class Solution {
    public int solution(int n) {
        int answer = 0;
        for (char a : String.valueOf(n).toCharArray()) {
            answer += (a - '0');
        }
        return answer;
    }
}
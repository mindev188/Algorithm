class Solution {
    public int solution(int num, int k) {
        int answer = 0;

        answer = String.valueOf(num).indexOf(k + "");
        return answer >= 0 ? answer + 1 : answer;
    }
}
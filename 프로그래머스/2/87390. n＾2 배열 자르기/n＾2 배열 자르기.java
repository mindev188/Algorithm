
class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = {};

        int length = (int) (right - left);
        answer = new int[length + 1];

        int j = 0;
        for (Long i = left; i <= right; i++, j++) {
            answer[j] = Math.toIntExact(Math.max(i / n + 1, i % n + 1));
        }
        return answer;
    }
}
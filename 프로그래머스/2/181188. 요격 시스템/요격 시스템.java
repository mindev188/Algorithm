import java.util.Arrays;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (a, b) -> a[1] - b[1]);

        int endIndex = 0;
        for (int[] target : targets) {
            int s = target[0];
            int e = target[1];
            if (s >= endIndex) {
                answer++;
                endIndex = e;
            }
        }
        return answer;
    }
}
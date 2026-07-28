import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        long totalSum = 0, sum1 = 0, sum2 = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            int q1Value = queue1[i];
            int q2Value = queue2[i];

            totalSum += q1Value;
            sum1 += q1Value;
            q1.add(q1Value);

            totalSum += q2Value;
            sum2 += q2Value;
            q2.add(q2Value);
        }
        if (totalSum % 2 != 0) return -1;

        int limit = queue1.length * 3;
        for (int i = 0; i < limit; i++) {
            if (sum1 == sum2) {
                return answer;
            } else if (sum1 > sum2) {
                int value = q1.poll();
                q2.add(value);
                sum1 -= value;
                sum2 += value;
            } else if (sum2 > sum1) {
                int value = q2.poll();
                q1.add(value);
                sum2 -= value;
                sum1 += value;
            }
            answer += 1;
        }

        return -1;
    }
}
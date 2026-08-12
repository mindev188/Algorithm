import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        long min = Long.MAX_VALUE;

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < stones.length; i++) {
            // 구간 초과 제거
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 나보다 작거나 같은 뒤 후보 제거
            int next = stones[i];
            while (!deque.isEmpty() && stones[deque.peekLast()] <= next) {
                deque.pollLast();
            }
            deque.offerLast(i);

            // 구간 완성 후 대소 비교
            if (i >= k - 1) {
                int max = stones[deque.peekFirst()];
                min = Math.min(min, max);
            }
        }
        return (int) min;
    }
}
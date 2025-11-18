import java.util.PriorityQueue;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i = 0; i < scoville.length; i++) {
            queue.add(scoville[i]);
        }

        while (queue.size() >= 2) {
            if (queue.peek() < K) {
                int a = queue.poll();
                int b = queue.poll();
                queue.add(a + (b * 2));
                answer++;
            } else {
                break;
            }
        }

        return queue.peek() >= K ? answer : -1;
    }
}
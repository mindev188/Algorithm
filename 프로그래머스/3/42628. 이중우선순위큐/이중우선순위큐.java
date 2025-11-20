import java.util.Collections;
import java.util.PriorityQueue;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};

        // PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> { return b - a; });
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            String command = operation.split(" ")[0];
            int value = Integer.parseInt(operation.split(" ")[1]);

            if (command.equals("I")) { // "I"
                maxQueue.add(value);
                minQueue.add(value);
            } else { // "D"
                if (maxQueue.isEmpty() || minQueue.isEmpty()) continue;

                if (value == 1) { // 최대값 삭제
                    int max = maxQueue.poll();
                    minQueue.remove(max);
                } else {
                    int min = minQueue.poll();
                    maxQueue.remove(min);
                }
            }
        }

        if (!maxQueue.isEmpty()) {
            answer[0] = maxQueue.peek();
        }
        
        if  (!minQueue.isEmpty()) {
            answer[1] = minQueue.peek();
        }

        return answer;
    }
}
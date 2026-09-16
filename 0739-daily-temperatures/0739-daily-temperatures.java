class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int[] answer = new int[temperatures.length];
        Deque<Integer>  stack = new ArrayDeque<>();

        for (int current = 0; current < temperatures.length; current++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[current]) {
                int previous = stack.pop();
                answer[previous] = current - previous;
            }
            stack.push(current);
        }
        return answer;
    }
}
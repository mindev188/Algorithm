class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < asteroids.length; i++) {
            int current = asteroids[i];
            if (current > 0) {
                stack.push(current);
                continue;
            }

            boolean currentAlive = true;
            while (!stack.isEmpty() && stack.peek() > 0 && currentAlive) {

                if (stack.peek() > Math.abs(current)) {
                    currentAlive = false;
                } else if (stack.peek() == Math.abs(current)) {
                    stack.remove();
                    currentAlive = false;
                } else if (stack.peek() < Math.abs(current)) {
                    stack.remove();
                }
            }

            if (currentAlive) {
                stack.push(current);
            }
        }

        int[] answer = new int[stack.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = stack.pollLast();
        }

        return answer;
    }
}
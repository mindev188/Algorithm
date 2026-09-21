class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for(int stone : stones) {
            queue.offer(stone);
        }

        while (queue.size() > 1) {
            int a = queue.poll();
            int b = queue.poll();

            int value = a - b;
            if (value != 0) queue.offer(value);
        }

        return queue.isEmpty() ? 0 : queue.peek();
    }
}
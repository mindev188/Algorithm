class KthLargest {
     PriorityQueue<Integer> pQueue = new PriorityQueue<>();

    int limitSize;

    public KthLargest(int k, int[] nums) {
        limitSize = k;
        for (int num : nums) {
            pQueue.offer(num);
        }

        while (pQueue.size() > limitSize) {
            pQueue.poll();
        }
    }

    public int add(int val) {
        pQueue.offer(val);
        while (pQueue.size() > limitSize) {
            pQueue.poll();
        }

        return pQueue.isEmpty() ? 0 : pQueue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
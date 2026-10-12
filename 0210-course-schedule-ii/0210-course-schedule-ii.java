class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] preCourseCounts = new int[numCourses];
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
            preCourseCounts[prerequisite[0]]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < preCourseCounts.length; i++) {
            if (preCourseCounts[i] == 0) queue.offer(i);
        }

        List<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            answer.add(current);
            for (int next : graph[current]) {
                if (--preCourseCounts[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return answer.size() != numCourses ? new int[]{} : answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
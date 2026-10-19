class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> answer = new ArrayList<>();
        int[] current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (current[1] < intervals[i][0]) {
                answer.add(current);
                current = intervals[i];
            } else {
                current[1] = Math.max(current[1], intervals[i][1]);
            }
        }
        answer.add(current);
        return answer.toArray(int[][]::new);
    }
}
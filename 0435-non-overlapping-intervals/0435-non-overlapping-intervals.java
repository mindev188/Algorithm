class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int answer = 0;

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (end <= interval[0]) {
                end = interval[1];
                continue;
            }

            answer++;
            end = Math.min(end, interval[1]);
        }
        return answer;
    }
}
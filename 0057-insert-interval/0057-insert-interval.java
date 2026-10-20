class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> answer = new ArrayList<>();
        for (int[] interval : intervals) {
            // newInterval 사이의 값에 해당 하지 않는 경우
            if (interval[1] < newInterval[0] || interval[0] > newInterval[1]) {
                answer.add(interval);
            } else {
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        answer.add(newInterval);

        return answer.stream().sorted((a, b) -> Integer.compare(a[0], b[0])).toArray(int[][]::new);
    }
}
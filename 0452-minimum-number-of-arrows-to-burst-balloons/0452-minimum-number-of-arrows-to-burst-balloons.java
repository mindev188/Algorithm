class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            return Integer.compare(a[1], b[1]);
        });

        int currentIndex = points[0][1];
        int totalCount = 1;
        for (int i = 1; i < points.length; i++) {
            int start = points[i][0];
            int end = points[i][1];
            if (currentIndex < start) {
                currentIndex = end;
                totalCount++;
            }
        }
        return totalCount;
    }
}
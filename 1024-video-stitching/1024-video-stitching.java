class Solution {
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (a,b) -> Integer.compare(a[0], b[0]));

        int answer = 0;
        int limitEnd = 0;
        int currentEnd = 0;
        int index = 0;
        while (limitEnd < time) {
            while (index < clips.length && clips[index][0] <= limitEnd) {
                if (clips[index][1] > currentEnd) {
                    currentEnd = clips[index][1];
                }
                index++;
            }
            // 확장이 안되는 경우
            if (currentEnd <= limitEnd) {
                return -1;
            }

            limitEnd = currentEnd;
            answer++;
        }

        return answer;
    }
}
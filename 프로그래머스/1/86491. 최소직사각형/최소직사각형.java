class Solution {
    public int solution(int[][] sizes) {

        int maxWidth = 0;
        int maxHeight = 0;
        for (int[] card : sizes) {
            int width = card[0] > card[1] ? card[0] : card[1];
            int height = card[0] > card[1] ? card[1] : card[0];
            maxWidth = Math.max(maxWidth, width);
            maxHeight = Math.max(maxHeight, height);
        }

        return maxHeight * maxWidth;
    }
}
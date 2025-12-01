class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0, 0};
        int H = 0;
        int W = 0;

        for (int h = 1; h <= Math.sqrt(yellow); h++) {
            if (yellow % h == 0) { // 약수
                int w = yellow / h;

                W = w + 2;
                H = h + 2;

                // brown 갯수
                if (W * H - yellow == brown) {
                    return new int[] {W, H};
                }

            }

        }
        return answer;
    }
}
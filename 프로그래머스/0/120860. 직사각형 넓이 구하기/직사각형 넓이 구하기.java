class Solution {
    public int solution(int[][] dots) {
        int answer = 0;
        int height = 0;
        int width = 0;

        /**
         * 첫번째 원소와 두번째 원소의 x축이 같다면
         */
        int x1 = dots[0][0];
        int y1 = dots[0][1];
        int x2, y2;

        if (x1 == dots[1][0]) {
            x2 = dots[2][0];
        } else {
            x2 = dots[1][0];
        }
        if (y1 == dots[1][1]) {
            y2 = dots[2][1];
        } else {
            y2 = dots[1][1];
        }

        height = Math.abs(y2 - y1);
        width = Math.abs(x2 - x1);

        answer = height * width;
 
        return answer;
    }
}
class Solution {
    public int solution(int[] box, int n) {
        int answer = 1;
        /**
         * box안에 n길이의 정사각형 주사위가 몇개 들어갈 수 있을까?
         * 여기서 문제는 box안에 가로 세로 높이의 길이에 n이 정확히 맞아 떨어지지 않는 이상 
         * box 부피를 n 주사위 부피로 나눈 값이 정답이 될 수 없다.
         * 
         * 즉, box의 가로, 세로, 높이를 n으로 나눠 정수 만큼만 들어갈 수 있다.
         */
        for (int i = 0; i < box.length; i++) {
            answer *= box[i] / n;
        }
        return answer;
    }
}
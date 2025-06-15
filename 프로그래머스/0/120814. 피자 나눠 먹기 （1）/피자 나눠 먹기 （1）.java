class Solution {
    public static int solution(int n) {
        /** 
         * 피자 한판을 7 조간으로 나눠 배분할 때 
         * n명의 사람들에게 하나의 피자조각을 배분하기 위해 필요한 피자의 갯수는?
         */
        int answer = 0;
        // n(사람의 수)를 7(피자 한판의 조각 수)로 나누었을때 딱 맞아 떨어지지 않는 경우 피자 한판을 추가.
        answer = n % 7 > 0 ? n / 7 + 1 : n / 7;
        return answer;
    }
}
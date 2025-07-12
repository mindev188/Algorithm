class Solution {
    public static int solution(int n) {
        int answer = 0;
        /**
         * 각 숫자가 약수가 아닌 경우 (즉, 약수의 개수가 세 개 이상인 수)를 구하자
         */
        for (int i = 4; i <= n; i++) {
            if (isComposite(i)) answer++;
        }
        return answer;
    }

    /**
     * 합성수인가
     * @param n
     * @return
     */
    public static boolean isComposite(int n) {
        // n의 제곱근까지만 반복문을 돌리면 최소화 할 수 있다ㅏ
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return true;
        }
        return false;
    }
}
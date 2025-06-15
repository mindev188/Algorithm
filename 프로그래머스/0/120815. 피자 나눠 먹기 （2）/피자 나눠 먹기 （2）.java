class Solution {
    /**
     * n명의 사람이 피자를 먹을 때 필요한 최소 피자 판 수를 구하는 문제
     * 피자는 6조각으로 나뉘어 있으며, 모든 사람이 동일한 수의 조각을 먹어야 함
     * @param n 사람의 수
     * @return 필요한 최소 피자 판 수
     */
    public static int solution(int n) {
        // n과 6의 최소공배수를 구한 후 6으로 나누어 필요한 피자 판 수를 계산
        return lcm(n, 6) / 6;
    }

    /**
     * Lcm(Lowest Common Multiple)
     * 
     * @param a
     * @param b
     * @return
     */
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    /**
     * Gcd (Greatest Common Divisor)
     * 
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
}
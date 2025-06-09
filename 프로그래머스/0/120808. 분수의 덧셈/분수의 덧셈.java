class Solution {
    public static int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = {};
        /**
         * number1 / denom1 + number2 / denom2 = ?
         * 기약 분수로 나타내기
         * 두 denom의 값이 배수인지 확인 후 배수인 경우 해당 값 만큼 곱해 사용
         * 아닌 경우 두 수의 최소공배수를 구해 사용
         */
        int a = denom1 < denom2 ? denom2 : denom1;
        int b = denom1 > denom2 ? denom2 : denom1;

        // 통분
        int commonDenom = lcm(a, b); // 최소공배수
        int newNumer1 = numer1 * (commonDenom / denom1);
        int newNumer2 = numer2 * (commonDenom / denom2);

        // 더한 분자 값
        int sumNumer = newNumer1 + newNumer2;

        // 최대공약수(통분한 분자 및 분모값)
        int commonGcd = gcd(sumNumer, commonDenom);
        int simplifiedNumer = sumNumer / commonGcd;
        int simplifiedDenom = commonDenom / commonGcd;

        answer = new int[]{simplifiedNumer, simplifiedDenom};
        return answer;
    }

    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    /**
     * 최대공약수 구하기
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
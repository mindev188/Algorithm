import java.util.Scanner;

public class AxByC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        /* 해를 구하는 방정식
         *
         * x = y^1, y = x^1 - y^1 * 몫 (확장 유클리드 호재법 재귀, 나머지가 0인 경우 종료)
         */
        long gcd = gcd(a, b); // 최대공약수
        if (c % gcd != 0) { // c의 값이 최대공약수의 배수가 아닌 경우 종료
            System.out.println(-1);
            return;
        }

        long mok = c / gcd; // 최대 공약수로 변경
        long[] ret = excute(a, b);
        // 구해진 x, y값에 mok을 곱하면 원하는 해가 나온다.
        System.out.println(ret[0] * mok + " " + ret[1] * mok);
    }

    /**
     * 확장 유클리드 호제법
     * x = y^1, y = x^1 - y^1 * 몫
     *
     * @param a
     * @param b
     * @return
     */
    public static long[] excute(int a, int b) {
        long[] ret = new long[2];
        if (b == 0) {
            ret[0] = 1;
            ret[1] = 0;
            return ret;
        }
        long q = a / b; // 몫
        long[] v = excute(b, a % b);
        ret[0] = v[1];
        ret[1] = v[0] - v[1] * q;
        return ret;
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}

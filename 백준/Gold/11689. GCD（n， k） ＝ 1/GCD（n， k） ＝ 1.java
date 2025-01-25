import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long result = N;
        for (long i = 2; i <= Math.sqrt(N); i++) {
            if (N % i == 0) { // 소인수인 경우
                result = result - result / i;
                while (N % i == 0) {
                    N = N / i;
                }
            }
        }
        if (N > 1) { // 소인수 구성이 남은 경우, ex) N이 소수이거나 N을 나눈 값이 N 제곱슨 이상의 소수인 경우 종료
            result = result - result / N;
        }
        System.out.println(result);
        // 즉 반복문 내의 'result = result - result/N'은 N이라는 소인수의 제곱을 모두 제하기 위함
    }
}

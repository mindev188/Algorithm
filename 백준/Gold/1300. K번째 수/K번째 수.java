import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        // 2차원 배열의 N행이 N의 배수인 경우 k 번째 수는 k를 넘지 못한다.
        // 즉 end 값은 K
        int start = 1;
        int end = K;
        int answer = 0;
        while (start <= end) {
            int middle = (start + end) / 2;
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                // 핵심 로직, 한 행에서 중앙값과 같거나 작은 수의 개수는 "중앙값 / i번째 행" 이다.
                // but N개를 넘지 않는다.
                cnt += Math.min(middle / i, N);
            }

            if (cnt < K) {
                start = middle + 1;
            } else {
                answer = middle;
                end = middle - 1;
            }
        }

        System.out.println(answer);
    }

}
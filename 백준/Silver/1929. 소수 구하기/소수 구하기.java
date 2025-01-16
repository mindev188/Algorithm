import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int e = sc.nextInt();
        int[] A = new int[e + 1];
        for (int i = 2; i < e + 1; i++) {
            A[i] = i;
        }

        // 이미 2부터 배수들을 지워 나가기 때문에 e의 제곱근 이상인 배수가 나올 수 없다.
        for (int i = 2; i <= Math.sqrt(e); i++) {
            if (A[i] == 0) continue;
            for (int j = i + i; j <= e; j += i) {
                A[j] = 0;
            }
        }

        for (int i = s; i < e + 1; i++) {
            if (A[i] == 0) continue;
            System.out.println(A[i]);
        }
    }
}

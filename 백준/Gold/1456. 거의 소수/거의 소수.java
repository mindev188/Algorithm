import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long min = sc.nextLong();
        long max = sc.nextLong();
        long[] A = new long[10000001];
        for (int i = 2; i < A.length; i++) {
            A[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(A.length); i++) {
            if (A[i] == 0) continue;
            for (int j = i + i; j < A.length; j = j + i) { // i의 배수 지우기
                A[j] = 0;
            }
        }
        int count = 0;
        // 각 인자별 거의 소수 구하기
        for (int i = 2; i < A.length; i++) {
            if (A[i] == 0) continue;

            // 최소 A[i]의 제곱부터 시작
            long temp = A[i];
            while((double)A[i] <= (double)max/(double)temp) { // max를 temp로 나눈 값이 A[i] 와 같거나 작은 경우만 진행, 즉 temp * A[i] 값이 max까지인 경우만
                if ((double)A[i] >= (double)min / (double)temp) {
                    count++;
                }
                temp = temp * A[i];
            }
        }
        System.out.println(count);
    }
}

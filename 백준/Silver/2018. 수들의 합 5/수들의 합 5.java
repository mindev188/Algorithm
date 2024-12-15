import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // N에 대한 연속된 자연수 합의 경우의 수
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int startPointer = 1;
        int endPointer = 1;
        int sum = 0;
        int answer = 1;

        /**
         * 투 포인터 이동
         * sum > N : sum = sum + start_indexer; start_indexer++;
         * sum < N : sum = sum + end_indexer; end_indexer++;
         * sum == N : count++; sum + end_indexer; end_indexer++;
         */
        while(endPointer <= N) {
            if (sum == N) {
                answer++;
                sum += endPointer++;
            } else if (sum > N) {
                sum -= startPointer++;
            } else {
                sum += endPointer++;
            }
        }
        System.out.println(answer);
    }
}
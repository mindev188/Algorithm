import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader dr = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(dr.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] S = new long[N];
        long[] C = new long[M];
        long answer = 0;

        // 총합의 배열 구하기
        st = new StringTokenizer(dr.readLine());
        S[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            S[i] = S[i-1] + Integer.parseInt(st.nextToken());
        }

        // 총합의 나머지값 카운트 배열 구하기
        for (int i = 0; i < N; i++) {
            int remainder = (int) (S[i] % M);
            if (remainder == 0) answer++;
            C[remainder]++;
        }

        // 총합 배열에서 나머지 값이 같은 2개의 원소를 뽑는 경우의 개수 
        for (int i = 0; i < M; i++) {
            if (C[i] > 1) {
                answer = answer + (C[i] * (C[i] -1) / 2);
            }
        }
        System.out.println(answer);
    }
}

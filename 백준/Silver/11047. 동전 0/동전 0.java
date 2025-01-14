import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for(int i = N - 1; i >= 0; i--) {
            int coin = A[i];
            if (coin > K) continue;

            count += K / coin;
            K = K % coin;
            if (K == 0) break;
        }
        System.out.println(count);
    }
}
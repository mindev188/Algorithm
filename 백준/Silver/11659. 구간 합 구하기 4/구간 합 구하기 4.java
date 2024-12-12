import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int arrSize = Integer.parseInt(st.nextToken());
        int quizSize = Integer.parseInt(st.nextToken());
        long[] s = new long[arrSize + 1];
        
        /*
         * s에 arr의 값 총 합을 각각 추가한다.
         * s[j] - s[i-1]
         */
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= arrSize; i++) {
            s[i] = s[i-1] + Integer.parseInt(st.nextToken());
        }

        // quizSize만큼 반복하며 i 부터 j까지의 합을 출력한다.
        for (int z = 0; z < quizSize; z++) {
            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            System.out.println(s[j] - s[i-1]);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] items = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자 정렬
        Arrays.sort(items);

        // start_index와 end_indexe를 합쳐 M이 되는 수
        // 두값이 만나면 종료
        // 총합이 M보다 큰 경우 end_index--;
        // 총합이 M보다 작은 경우 start_index++;
        // 총합이 M과 같은 경우 result++; start_index++; end_index--;
        int start_index = 0;
        int end_index = N - 1;
        while (start_index < end_index) {
            int sum = items[start_index] + items[end_index];
            if (sum > M) {
                end_index--;
            } else if (sum < M) {
                start_index++;
            } else {
                // sum == M
                answer++;
                start_index++;
                end_index--;
            }
        }
        System.out.println(answer);
        br.close();
    }
}

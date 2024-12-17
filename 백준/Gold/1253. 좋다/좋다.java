import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] items = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자 정렬
        Arrays.sort(items);

        // i 부터 k 이전까지의 수를 합해 k값 만들기
        for (int k = 0; k < N; k++) {
            int i = 0;
            int j = N - 1;
            while(i < j) {
                int sum = items[i] + items[j];
                if (sum == items[k]) {
                    // items[k]값이 두 수의 합으로 표시되는 경우 좋은 수 임이 확정됨으로 break;
                    // 경우의 수가 아님
                    if (i == k) {
                        i++;
                    } else if (j == k) {
                        j--;
                    } else {
                        answer++;
                        break;
                    }
                } else if (sum < items[k]) {
                    i++;
                } else { // sum > items[k]
                    j--;
                }
            }
        }
        System.out.println(answer);
        br.close();
    }
}

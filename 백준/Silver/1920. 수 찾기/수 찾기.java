import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 이진 탐색은 정렬됨을 기반으로 진행
        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int start = 0;
            int end = N - 1;
            int target = Integer.parseInt(st.nextToken());
            int isFind = 0; // 존재하지 않는 값인 경우 0, 존재하는 경우 1
            while (start <= end) {
                    int midi = (start + end) / 2;
                    if (A[midi] > target) {
                        end = midi - 1;
                    } else if (A[midi] < target) {
                        start = midi + 1;
                    } else {
                        isFind = 1;
                        break;
                    }
            }
            System.out.println(isFind);
        }

    }
}
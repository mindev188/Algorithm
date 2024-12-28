import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] arr = new int[N];
        int[] sums = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 삽입 정렬
        // 정렬된 범위 내에서 오름차순 삽입 정렬
        // i = 1인 경우 j = 0과 비교해 삽입 정렬
        for (int i = 1; i < arr.length; i++) {
            int insertPoint = i;
            int insertValue = arr[i];
            // 왼쪽부터 오른쪽으로 범위를 넓혀 나가며 작은 수를 앞으로 삽입
            for (int j = i - 1; j >= 0; j--) {
                // arr[j]들과 대소 비교해
                // 가장 낮은수인 경우 맨앞
                // 그 외에는 j 인덱스 + 1 위치에 삽입
                if (arr[j] < arr[i]) {
                    insertPoint = j + 1;
                    break;
                }
                if (j == 0) {
                    insertPoint = 0;
                }
            }
            // i부터 insertPoint까지의 값을 우측으로 +1 이동
            for (int j = i; j > insertPoint; j--) {
                arr[j] = arr[j-1];
            }
            arr[insertPoint] = insertValue;
        }

        // 값들의 합 구하기
        sums[0] = arr[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i-1] + arr[i];
        }

        int answer = 0;
        for (int a : sums) {
            answer += a;
        }
        System.out.println(answer);
    }
}
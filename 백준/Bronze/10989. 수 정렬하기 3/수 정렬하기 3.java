import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
public class Main {
    public static int[] A;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        int N = Integer.parseInt(br.readLine());
        A = new int[N];

        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        // 기수 정렬
        sort();
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < A.length; i++) {
            bw.write(A[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    // 기수 정렬
    /**
     * 기수 정렬을 통해 
     * 각 자리수별 정렬 반복
     */
    private static void sort() {
        // 임시 정렬을 위한 배열
        int[] output = new int[A.length];
        // 나눌 자리수 값
        int jarisu = 1;
        // 자리수 
        int count = 0;

        // 최대 자리수 만큼 반복
        while (count != 5) {
            int[] bucket = new int[10];
            for (int i = 0; i < A.length; i++) {
                bucket[(A[i] / jarisu) % 10]++; // 일의 자리수 부터 시작
            }
            // 합 배열 이용 index 계산하기
            for (int i = 1; i < bucket.length; i++) {
                bucket[i] += bucket[i - 1];
            }
            // 현재 자리수 기준 정렬
            for (int i = A.length - 1; i >= 0; i--) {
                output[bucket[(A[i] / jarisu % 10)] - 1] = A[i];
                bucket[(A[i] / jarisu) % 10]--;
            }
            for (int i = 0; i < A.length; i++) {
                A[i] = output[i];
            }
            jarisu = jarisu * 10;
            count++;
        }

    }
}
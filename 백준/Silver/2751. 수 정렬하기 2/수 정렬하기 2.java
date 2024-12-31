import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
public class Main{
    static int[] A, tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        tmp = new int[N];
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(0, N - 1);
        for (int i = 0; i < A.length; i++) {
            bw.write(A[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    /**
     * 병합 정렬
     * 
     */
    private static void mergeSort(int s, int e) {
        if (e - s < 1) {
            return;
        }

        int m = s + (e - s)/2;
        /**
         * 재귀 함수
         * 배열을 반으로 쪼개 1개의 배열부터 정렬을 해나간다.
         */ 
        mergeSort(s, m);
        mergeSort(m + 1, e);
        for (int i = s; i <= e; i++) {
            tmp[i] = A[i];
        }

        // 각 정렬된 두 배열을 병합
        int k = s;
        int index1 = s;
        int index2 = m + 1;
        while (index1 <= m && index2 <= e) {
            // 두 인덱스의 값 중 낮은 값을 먼저 A 배열에 담는다.
            if (tmp[index1] > tmp[index2]) {
                A[k] = tmp[index2];
                index2++;
            } else {
                A[k] = tmp[index1];
                index1++;
            }
            k++;
        }

        // 두 인덱스(index1, index2) 중 남아있는 인덱스의 배열값을 정리
        while (index1 <= m) {
            A[k] = tmp[index1];
            k++;
            index1++;
        }
        while (index2 <= e) {
            A[k] = tmp[index2];
            k++;
            index2++;
        }
    }
}
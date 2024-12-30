import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, K 값 선언
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // 배열 선언
        int[] A = new int[N];
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 퀵 정렬
        quickSort(A, 0, N - 1, K - 1); 
        System.out.println(A[K - 1]);
    }
        
    private static void quickSort(int[] A, int S, int E, int K) {
        if (S < E) {
            int pivot = partition(A, S, E);
            if (pivot == K) {                   // k번째 수가 pivot이면 더 이상 구할 필요가 없음
                return;
            } else if (K < pivot) {
                quickSort(A, S, pivot - 1, K);  // K가 pivot보다 작으면 왼쪽 그룹만 정렬 수행하기
            } else {
                quickSort(A, pivot + 1, E, K);  // K가 pivot보다 크면 오른쪽 그룹만 정렬 수행하기
            }
        }
    }
            
    private static int partition(int[] A, int S, int E) {
        // S와 E의 값이 1 차이 이면서, A[S]의 값이 A[E] 값보다 큰 경우
        // 자리 변경, pivot값으로 E값을 전달
        if (S + 1 == E && A[S] > A[E]) {
            swap(A, S, E);
            return E;
        }

        int M = (S + E) / 2;
        swap(A, S, M); // M 값을 0번째 인덱스로 자리 변경
        int pivot = A[S];
        int i = S + 1, j = E;
        while (i <= j) {
            while (pivot < A[j] && j > 0) {
                j--;
            }
            while (pivot > A[i] && i < A.length - 1) {
                i++;
            }
            if (i <= j) { // pivot 기점으로 i 와 j의 값의 위치를 스왑
                swap (A, i++, j--); //스왑 이후 다음 스텝으로 이동
            }
        }

        // i == j 피벗의 값을 양쪽으로 분리한 가운데에 오도록 설정하기
        A[S] = A[j];
        A[j] = pivot;
        return j;
    }
        
    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
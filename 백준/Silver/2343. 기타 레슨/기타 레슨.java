import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];

        /**
         * 블루레이의 최솟값을 구해야 하는 문제
         * 
         * 어떻게 하면 최솟값을 구하는가
         * 각 값을 더한값이 기준값을 넘지 않는 최소 값을 구한다
         * 반복문(start <= end) {
         *      기준값을 초과 하는 경우 종료
         *      사용한 블루레이 갯수가 초과되는 경우 기준값을 줄여야 한다.
         *      아닌 경우는 기준값을 늘린다.
         * }
         *      
         */
        st = new StringTokenizer(br.readLine());
        int start = 0;  // 인자 중 최댓값
        int end = 0;    // 인자의 총합
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (A[i] > start) start = A[i];
            end += A[i];
        }

        // mid 값을 구해 M으로 나눈 인자의 합이 mid값을 넘어가지 않도록
        while (start <= end) {
            int mid = (start + end) / 2;
            int count = 0;  // M을 넘어가지 않도록
            int sum = 0;    // sum 값이 mid 값을 넘어가는 경우 다음 Sum
            for (int i = 0; i < A.length; i++) {
                if (mid < sum + A[i]) {
                    sum = 0;
                    count++;
                }
                sum = sum + A[i];
            }

            if (sum != 0) {     // 마지막 인자의 값까지 넣은 후 sum이 있다면 하나의 블루레이를 증감 해야 된다.
                count++;
            }

            if (count > M) {    // 블루레이를 나눈 값이 M을 초과한 경우 
                start = mid + 1;
            } else {            // 나눈 값이 M보다 작거나 일치하는 경우
                end = mid - 1;
            }
        }
        System.out.println(start);
    }
}
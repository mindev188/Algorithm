import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Main{
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 일의 자리 수는 2,3,5,7 만 가능
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);

    }

    /**
     * 
     * @param a : 소수로 기대되는 숫자
     * @param jarisu : 자릿수
     */
    private static void DFS(int a, int jarisu) {
        // 자리수가 N 자리 이면서 모두 소수인 경우 
        if (jarisu == N && isPrime(a)) {
            System.out.println(a);
        }

        if (isPrime(a)) {
            for (int i = 1; i <= 9; i+=2) {
                DFS(a * 10 + i, jarisu + 1);
            }
        }
    }

    private static boolean isPrime(int a) {
        for (int i = 2; i < a / 2; i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }
}
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[]  args) throws IOException {
        // 최대 공약수 : b % (a % b) ... => b가 0이 되는 경우의 a 값, 즉 나머지 값으로 b의 값을 나눌 수 없을때까지 나누기
        // 최소 공배수 : a * b / 최대 공약수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(a * b / gcd(a, b));
        }
    }

    public static int gcd(int a , int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
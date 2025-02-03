import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.io.BufferedWriter;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long result = gcd(b, a);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (long i = 0; i < result; i++) {
            bw.write("1");
        }
        bw.flush();
        bw.close();
    }

    /**
     * 유클리드 호제법 메서드
     * @param a
     * @param b
     * @return
     */
    private static long gcd (long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}

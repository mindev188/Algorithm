import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[10000001];

        // 값 초기화
        for (int i = 2; i < A.length; i++) {
            A[i] = i;
        }

        // 배수 지우기
        for (int i = 2; i < Math.sqrt(A.length); i++) {
            if (A[i] == 0) continue;
            for (int j = i + i; j < A.length; j = j + i) {
                A[j] = 0;
            }
        }

        int answer = 0;
        for (int i = N; i < A.length; i++) {
            if (A[i] == 0) continue;
            if (isPalindrome(String.valueOf(A[i]))) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    public static boolean isPalindrome(String str) {
        char[] a = str.toCharArray();
        int s = 0;
        int e = a.length - 1;
        while (s <= e) {
            if (a[s] != a[e]) {
                return false;
            }
            s++;
            e--;
        }
        return true;
    }
}

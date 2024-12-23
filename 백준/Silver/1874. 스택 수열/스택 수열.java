import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<Integer>();
        int num = 1;
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            if (a >= num) {
                while (a >= num) { // 값이 같아질 때까지 push 수행
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            } else {
                int x = stack.pop();
                // 스택의 가장 위의 수가 만들어야 하는 수열의 수보다 크면 수열을 출력할 수 없음.
                if (x > a) {
                    bf = new StringBuffer("NO");
                    break;
                } else {
                    bf.append("-\n");
                }
            }
        }
        System.out.println(bf.toString());
    }
}

import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
    
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < arr.length; i++) {
            // stack에 값이 있는 상태에서 arr[i](오큰수) 값이 stack의 가장 위의 수(낮은 수) 보다 큰 경우 해당 위치에 arr[i]값을 넣는다.
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        while (stack.size() > 0) {
            result[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
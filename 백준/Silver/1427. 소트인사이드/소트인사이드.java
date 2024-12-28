import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String question = sc.next();
        int[] arr = new int[question.length()];
        for (int i = 0; i < question.length(); i++) {
            arr[i] = Integer.parseInt(question.substring(i,i+1));
        }

        // 선택 정렬 방식으로 내림차순 정렬
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            int max = arr[i];
            int tmp = 0;
            int maxIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (max < arr[j]) {
                    max = arr[j];
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                tmp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = tmp;
            }
            sb.append(arr[i]);
        }

        System.out.println(sb.toString());
    }
}
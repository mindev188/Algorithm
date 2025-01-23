import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long min = sc.nextLong();
        long max = sc.nextLong();
        boolean[] check = new boolean[(int) (max - min + 1)];
        // 제곱의 수로 나눠떨어지는 값은 count 하지 않는다
        for (long i = 2; i * i <= max; i++) {
            long pow = i * i;
            long start_index = min / pow; // 제곱의 최소 나누기 값부터 최대 나누기 값 까지를 true로 변경
            if (min % pow != 0) start_index++; // 나머지 값이 있는 경우 start_index를 최소(min)이상으로 맞추기 위해 + 1
            for (long j = start_index; j * pow <= max; j++) {
                check[(int) (j * pow - min)] = true;
            }
        }

        int count = 0;
        for (int i = 0; i < check.length; i++) {
            if (check[i]) continue;
            count++;
        }
        System.out.println(count);
    }
}

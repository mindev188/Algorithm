import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        // 우선순위큐에 담아 낮은 수부터 더할 수 있도록 한다.
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (queue.size() >= 2) {
            int a = queue.poll();
            int b = queue.poll();
            int sum = a + b;
            answer += sum;
            queue.add(sum);
        }
        System.out.println(answer);
    }
}


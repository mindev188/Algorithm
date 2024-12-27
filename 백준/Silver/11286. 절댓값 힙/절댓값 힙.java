import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer> ((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2); 
            if (abs1 == abs2) {
                return o1 > o2 ? 1 : -1;
            } else {
                return abs1 - abs2;
            }
        }); 
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            if (a == 0) {
                if (pQueue.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(pQueue.poll());
                }
            } else {
                pQueue.add(a);
            }
        }

    }
}
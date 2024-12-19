import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Deque<Node> que = new LinkedList<Node>();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            // que 마지막부터 num보다 큰 값이 있는 경우 제거
            while (!que.isEmpty() && que.getLast().value > num) {
                que.removeLast();
            }
            // que 마지막에 추가
            que.addLast(new Node(i, num));

            // index값이 L을 벗어 나는 경우
            if (que.getFirst().index <= i-L) {
                que.removeFirst();
           }

            bw.append(que.getFirst().value + " ");
        }
        bw.flush();
        bw.close();
    }

    static public class Node {
        int index;
        int value;
        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
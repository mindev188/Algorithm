import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    private static ArrayList<Integer>[] A;
    private static boolean[] visited;
    private static int[] answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        A = new ArrayList[N + 1];
        answer = new int[N + 1];

        for (int i = 0; i < A.length; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e);
        }

        for (int i = 0; i <= N; i++) {
            visited = new boolean[N + 1];
            BFS(i);
        }

        int max = 0;
        for (int i = 0; i <= N; i++) {
            max = Math.max(max, answer[i]);
        }

        for (int i = 0; i <= N; i++) {
            if (max == answer[i]) {
                bw.write(i + " ");
            }
        }
        bw.flush();
        bw.close();

    }

    public static void BFS(int start) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        visited[start] = true;

        // start, 즉 node가 신뢰하는 next값부터 start 까지 역순으로 길이를 조회. (때문에 answer를 증감 시킴)
        while(!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : A[node]) {
                if (visited[next]) continue;
                answer[next]++;
                visited[next] = true;
                queue.add(next);

            }


        }
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static ArrayList<Integer>[] A;
    static boolean[] visited;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        A = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e);
            A[e].add(s);
        }

        // 번호가 작은 원소부터 방문하기 위해
        for (int i = 1; i < N + 1; i++) {
            Collections.sort(A[i]);
        }

        visited = new boolean[N + 1];
        DFS(V);
        System.out.println();
        visited = new boolean[N + 1];
        BFS(V);
        System.out.println();

    }

    /**
     * 깊이 우선 탐색
     * 
     * @param num
     */
    private static void DFS(int num) {
        if (visited[num]) {
            return;
        }
        visited[num] = true;
        System.out.print(num + " ");
        for (int a : A[num]) {
            DFS(a);
        }

    }

    /**
     * 너비 우선 탐색
     * 
     * @param num
     */
    private static void BFS(int num) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(num);
        visited[num] = true;

        while(!queue.isEmpty()) {
            int a = queue.poll();

            System.out.print(a + " ");
            for (int b : A[a]) {
                if (!visited[b]) {
                    queue.add(b);
                    visited[b] = true;
                }
            }
        }

    }
}
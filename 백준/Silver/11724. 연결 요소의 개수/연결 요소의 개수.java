import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
public class Main{
    public static ArrayList<Integer>[] A;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 각 노드별 이어진 값
        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        // 각 배열 원소 초기화
        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            // 노드는 양방향이기 때문에 s, e에 모두 추가
            A[s].add(e);
            A[e].add(s);
        }
        // 총 연결된 노드의 수
        int count = 0;
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) {
                count++;
                DFS(i);
            }
        }
        System.out.println(count);
    }

    /**
     * 깊이 우선 탐색
     * 
     * @param i
     */
    private static void DFS(int i) {
        visited[i] = true;
        for (int a : A[i]) {
            if (!visited[a]) {
                DFS(a);
            }
        }
    }
}
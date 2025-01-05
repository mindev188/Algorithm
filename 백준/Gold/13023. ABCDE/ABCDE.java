import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
public class Main{
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static boolean isAlive;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N명의 각각의 친한 친구 목록
        A = new ArrayList[N];
        visited = new boolean[N];
        isAlive = false;
        for (int i = 0; i < A.length; i++) {
            A[i] = new ArrayList<Integer>();
            visited[i] = false;
        }

        // 에지 목록 list 추가. 
        // 친한 친구 목록을 i 원소에 추가ㅣ
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A[a].add(b);
            A[b].add(a);
        }

        for (int i = 0; i < A.length; i++) {
            if (!visited[i]) {
                DFS(i, 1);
            }
        }
        if (isAlive) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    /**
     * 깊이 우선 탐색
     * @param a : 노드값
     * @param relationCount : 연결된 에지 깊이, 관계 연결 깊이
     */
    private static void DFS(int node, int relationCount) {
        if (isAlive || relationCount >= 5 ) {
            isAlive = true;
            return;
        }
        visited[node] = true;
        for (int a : A[node]) {
            if (!visited[a]) {
                DFS(a, relationCount + 1);
            }
        }

        // 한번 방문한 node는 다시 방문하지 못하는 것이 아닌
        // 다른 노드에서 부터 시작하는 엣지의 깊이를 확인하기 위해 다시 false로 초기화해준다.
        visited[node] = false;
    }
}
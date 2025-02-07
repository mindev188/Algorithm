import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static ArrayList<Integer>[] A;
    public static int[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도시 개수
        int M = Integer.parseInt(st.nextToken()); // 도로 개수 (엣지 갯수)
        int K = Integer.parseInt(st.nextToken()); // 최단 거리값
        int X = Integer.parseInt(st.nextToken()); // 출발 도시 번호
        ArrayList<Integer> answer = new ArrayList<Integer>();

        A = new ArrayList[N + 1];
        // A 배열 초기화
        for (int i = 0; i < A.length; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a].add(b);
        }

        visited = new int[N + 1];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        // 최단 거리가 K인 도시의 번호들을 구한다.
        // BFS를 통해 너비로 검색. (최단 거리 도시들)
        BFS(X);
        for (int i = 0; i <= N; i++) {
            if (visited[i] == K) {
                answer.add(i);
            }
        }

        if (answer.isEmpty()) {
            System.out.println("-1");
            return;
        }
        Collections.sort(answer);
        for (int a : answer) {
            System.out.println(a);
        }

    }
    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        visited[node]++;
        while (!queue.isEmpty()) {
            int value = queue.poll();
            for (int nextNode : A[value]) {
                if (visited[nextNode] != -1) continue;
                visited[nextNode] = visited[value] + 1;
                queue.add(nextNode);
            };
        }
    }
}

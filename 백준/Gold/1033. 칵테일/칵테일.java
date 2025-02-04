import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    // 노드
    private static ArrayList<Node>[] A;
    // 방문 여부
    private static boolean[] visited;
    // 질량 비율
    private static long[] D;

    public static void main(String[] args) throws IOException {
        // A 객체 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        A = new ArrayList[N];
        D = new long[N];
        visited = new boolean[N];
        long lcm = 1; // 최소공배수
        long mgcd = 1; // 공약수

        for (int i = 0; i < A.length; i++) {
            A[i] = new ArrayList<Node>();
        }

        StringTokenizer st = null;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            A[a].add(new Node(a, b, p, q));
            A[b].add(new Node(b, a, q, p));
            lcm *= (p * q / gcd(p, q)); // 최소공배수는 두 수의 곱을 최대 공약수로 나눈것
        }
        D[0] = lcm; // 모든 값의 최소공배수를 구함
        DFS(0); // 각 노드별 질량 비율을 구함

        // 각 노드별 질량 비율을 토대로 최대공약수 산정
//        mgcd = lcm;
        mgcd = D[0];
        for (int i = 1; i < D.length; i++) {
            mgcd = gcd(mgcd, D[i]);
        }

        // 전체 재료의 최대공약수를 가지고 각 질량의 최소 비율(최소공배수)를 구한다.
        for (int i = 0; i < D.length; i++) {
            System.out.print(D[i] / mgcd + " ");
        }
    }

    // 최대공약수 산정
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // DFS 산정
    public static void DFS(int a) {
        visited[a] = true;
        for (Node node : A[a]) {
            int next = node.b;
            if (visited[next]) continue;

            // 이미 구해진 a의 질량으로 b의 질량 비율을 구한다.
            // a:b == p:q => a/b = p/q => b = p / q * a
            D[next] = D[node.a] * node.q / node.p;
            DFS(next);
        };

    }

    static class Node {
        int a;
        int b;
        int p;
        int q;
        public Node(int a, int b, int p, int q) {
            this.a = a;
            this.b = b;
            this.p = p;
            this.q = q;
        }
    }
}


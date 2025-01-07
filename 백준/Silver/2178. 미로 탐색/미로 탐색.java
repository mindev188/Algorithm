import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static int[][] A;
    static boolean[][] visited;
    static int N, M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }

        BFS(0, 0);

        // 최종적으로 마지막 배열의 값엔 depth가 남아있다. 
        System.out.println(A[N-1][M-1]);
    }
    
    /**
     * 너비 우선 탐색
     * 
     * @param i : x
     * @param j : y
     */
    private static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<int[]>(); 
        queue.add(new int[] {i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] a = queue.poll();
            // 상하 좌우에 대한 값을 불어와 검증한다.
            for (int k = 0; k < 4; k++) {
                int x = a[0] + dx[k];
                int y = a[1] + dy[k];
                // 값이 최소, 최대치를 넘어가지 않고, 1이며, 방문하지 않은 곳만
                if (x >= 0 && y >= 0 && x < N && y < M) {
                    if (A[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        queue.add(new int[] {x, y});
                        A[x][y] = A[a[0]][a[1]] + 1; // 기존의 값의 + 1한 값으로 A배열에 대한 depth를 만든다.
                    }
                }
            }
        }
    }

}
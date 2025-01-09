import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static ArrayList<Node>[] list;  // 노드 목록
    static boolean[] visited;       // 방문 여부 목록
    static int[] distance;          // 거리 목록
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();

        list = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < V + 1; i++) {
            int mainNode = sc.nextInt();
            while(true) {
                int index = sc.nextInt();
                if (index == -1) break;
                int distance = sc.nextInt();
                list[mainNode].add(new Node(index, distance));
            }
        }

        visited = new boolean[V + 1];
        distance = new int[V + 1];
        BFS(1);

        // 가장 거리 값이 큰 노드 인덱스 찾기
        int max = 1;
        for (int i = 2; i < V + 1; i++) {
            if (distance[max] < distance[i]) {
                max = i;
            }
        }
    
        // 가장 먼 노드에서부터 가장 먼 노드를 다시 찾기/
        visited = new boolean[V + 1];
        distance = new int[V + 1];
        BFS(max);
        Arrays.sort(distance);
        System.out.println(distance[V]);
    }

    public static void BFS(int start) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int num = queue.poll();

            for (Node node : list[num]) {
                int index = node.index;
                if (visited[index]) continue;
                visited[index] = true;
                queue.add(index);
                distance[index] = distance[num] + node.distance; // 거리의 총합 배열
            }
        }
    }
    public static class Node {
        int index;
        int distance;
        Node (int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}
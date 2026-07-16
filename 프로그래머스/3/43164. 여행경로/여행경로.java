import java.util.*;
class Solution {
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        answer[0] = "ICN";

        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            };
            return a[0].compareTo(b[0]);
        });
        boolean[] visited = new boolean[tickets.length];
        String[] route = new String[tickets.length + 1];
        route[0] = "ICN";
        dfs(tickets, visited, route, "ICN", 0);

        return route;
    }

    private boolean dfs(String[][] tickets, boolean[] visited, String[] route, String current, int currentIndex) {
        if (currentIndex == tickets.length) return true;

        for (int i = 0; i < tickets.length; i++) {
            if (!current.equals(tickets[i][0])) continue;
            if (visited[i]) continue;

            route[currentIndex + 1] = tickets[i][1];
            visited[i] = true;
            if (dfs(tickets, visited, route, tickets[i][1], currentIndex + 1)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }
}
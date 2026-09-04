class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];
        answer = dfs(begin, target, words, visited, 0);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private int dfs(String begin, String target, String[] words, boolean[] visited, int count) {
        if (begin.equals(target)) return count;

        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue;
            if (!isMatch(begin, words[i])) continue;

            visited[i] = true;
            minCount = Math.min(minCount, dfs(words[i], target, words, visited, count + 1));
            visited[i] = false;
        }
        return minCount;
    }

    boolean isMatch(String AString, String BString) {
        int matchCount = 0;
        for (int i = 0; i < AString.length(); i++) {
            if (AString.charAt(i) == BString.charAt(i)) matchCount++;
        }

       return matchCount + 1 == AString.length() ? true : false;
    }
}
import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        boolean isInclude = false;
        for (String word : words) {
            if (word.equals(target)) { isInclude = true; break; }
        }
        if (!isInclude) {return answer;}

        answer = bfs(begin, target, words);
        return answer;
    }

    private int bfs(String begin, String target, String[] words) {
        Queue<String> queue = new LinkedList<>();
        Queue<Integer> countQueue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];

        queue.offer(begin);
        countQueue.offer(0);

        // begin 에서 하나의 문자열을 바꾼 값이 words에 있는가 check 하기
        while (!queue.isEmpty()) {
            String current = queue.poll();
            int count = countQueue.poll();

            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canChange(current, words[i])) {
                    if (words[i].equals(target)) {
                        return count + 1;
                    }
                    visited[i] = true;
                    queue.offer(words[i]);
                    countQueue.offer(count + 1);
                }
            }
        }

        return 0;
    }

    private boolean canChange(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }
}
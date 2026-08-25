import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};

        char lastChar = ' ';
        Set<String> wordSet = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (wordSet.contains(word)) return new int[] { i % n + 1, i / n + 1};
            if (i != 0 && !word.substring(0, 1).equals("" + lastChar)) return new int[] { i % n + 1, i / n + 1};

            wordSet.add(word);
            lastChar = word.charAt(word.length() - 1);
        }
        return answer;
    }
}
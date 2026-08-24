import java.util.*;
class Solution {
    String[] str = {"A", "E", "I", "O", "U"};
    List<String> dictionary;
    public int solution(String word) {
        dictionary = new ArrayList<>();

        // bfs
        for (int i = 0; i < str.length; i++) {
            bfs(str[i]);
        }

        return dictionary.indexOf(word) + 1;
    }

    private void bfs(String s) {
        dictionary.add(s);

        if (s.length() == 5) return;
        for (int i = 0; i < str.length; i++) {
            bfs(s + str[i]);
        }
    }
}
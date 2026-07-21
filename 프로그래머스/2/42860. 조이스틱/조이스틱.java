class Solution {
    public int solution(String name) {

        int answer = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            int move = c - 'A';
            if (move > 13) move = 26 - move;
            answer += move;
        }

        int length = name.length();
        int minMove = length - 1;
        for (int i = 0; i < length; i++) {
            int next = i + 1;
            while (next < length && name.charAt(next) == 'A') next++;

            // total move 수
            minMove =  Math.min(minMove, (length - next) * 2 + i);
            minMove = Math.min(minMove, i * 2 + (length - next));
        }
        return answer + minMove;
    }
}
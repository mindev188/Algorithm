import java.util.Arrays;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int[] command = commands[i];
            int start = command[0] - 1;
            int end = command[1] - 1;
            int chooseIndex = command[2] - 1;

            int[]  tmpArray = array.clone();
            Arrays.sort(tmpArray, start, end + 1);
            answer[i] = tmpArray[start + chooseIndex];
        }

        return answer;
    }
}
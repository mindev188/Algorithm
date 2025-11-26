import java.util.ArrayList;
class Solution {
    public int[] solution(int[] answers) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        int[] aAnswer = {1,2,3,4,5};
        int[] bAnswer = {2,1,2,3,2,4,2,5};
        int[] cAnswer = {3,3,1,1,2,2,4,4,5,5};
        int[] scores = new int[3];

        for (int i = 0; i < answers.length; i++) {
            int num = answers[i];

            if (aAnswer[i % aAnswer.length] == num) scores[0]++;
            if (bAnswer[i % bAnswer.length] == num) scores[1]++;
            if (cAnswer[i % cAnswer.length] == num) scores[2]++;
        }

        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));

        for (int i = 0; i < 3; i++) {
            if (max == scores[i]) list.add(i + 1);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

}
class Solution {
    public int[] solution(int[] array) {
        int[] answer = new int[2];

        int maxVal = 0;
        int maxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (maxVal < array[i]) {
                maxVal = array[i];
                maxIndex = i;
            }
        }
        answer[0] = maxVal;
        answer[1] = maxIndex;
        return answer;
    }
}
class Solution {
    public int[] solution(int[] numbers, String direction) {
        int[] answer = new int[numbers.length];
        
        int index = 0;
        if ("right".equals(direction)) index = -1;
        else index = 1;

        for(int i = 0; i < answer.length; i++) {
            answer[i] = numbers[(numbers.length + i + index) % numbers.length];
        }
        return answer;
    }
}
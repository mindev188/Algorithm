class Solution {
    /**
     * 정수 배열의 평균값을 반환
     * @param numbers
     * @return
     */
    public static double solution(int[] numbers) {
        double answer = 0;
        for (int i = 0; i < numbers.length; i++) {
            answer += numbers[i];
        }

        answer /= numbers.length;
        return answer;
    }
}
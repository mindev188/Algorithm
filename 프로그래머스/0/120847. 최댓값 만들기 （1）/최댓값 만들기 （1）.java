import java.util.Arrays;
class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        /**
         * 정수 배열 중 두 개를 곱해 만들 수 있느 최대값을 구하라
         */
        Arrays.sort(numbers);
        answer = numbers[numbers.length - 1] * numbers[numbers.length - 2];
        return answer;
    }
}
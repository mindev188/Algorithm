import java.util.Arrays;
class Solution {
    public int solution(int[] numbers) {
        Arrays.sort(numbers);

        int max = numbers[numbers.length - 1];
        int subMax = numbers[numbers.length - 2];
        int min = numbers[0];
        int subMin = numbers[1];
        return max * subMax > min * subMin ? max * subMax : min * subMin;
    }
}
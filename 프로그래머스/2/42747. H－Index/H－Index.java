import java.util.Arrays;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        Integer[] arr = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        // Arrays.sort(arr, (a, b) -> a - b); // 오름 차순 : (a > b) 인경우 + 이고 변환이 이뤄짐
        // Arrays.sort(arr, (a, b) -> b - a); // 내림 차순 : (a > b) 인경우 - 이고 변환이 이뤄지지 않음
        Arrays.sort(arr, (a, b) -> b - a);

        for (int i = 1; i <= arr.length; i++) {
            int citation = arr[i - 1];
            if (citation >= i) {
                answer = i;
            } else {
                break;
            }
        }

        return answer;
    }

}
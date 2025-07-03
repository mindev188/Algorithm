class Solution {
    public int solution(int[] numbers, int k) {
        int answer = 0;
        // k번째로 공을 전달하는 사람의 번호라는 뜻은 마지막 숫자의 직전이라는 뜻으로 : - 1
        // 한 명을 건너뛰고 그 다음사람에게 던지므로 : * 2
        // 배열의 값을 넘어가는 경우 해당 값은 제외 해야 하므로 : % numbers.length
        // k - 1 * 2 번째에 해당 되는 인덱스 값을 구한다.
        answer = numbers[(k - 1) * 2 % numbers.length];
        return answer;
    }
}
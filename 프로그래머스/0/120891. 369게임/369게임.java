class Solution {
    public int solution(int order) {
        int answer = 0;
        for (String num : String.valueOf(order).split("")) {
            if (Integer.parseInt(num) != 0 && Integer.parseInt(num) % 3 == 0) answer++;
        }
        return answer;
    }
}
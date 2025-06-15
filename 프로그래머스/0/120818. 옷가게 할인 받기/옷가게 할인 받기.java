class Solution {
    public int solution(int price) {
        int answer = price;  // 기본값으로 원래 가격 설정

        if (price >= 500000) {
            answer = (int)(price * 0.8);
        } else if (price >= 300000) {
            answer = (int)(price * 0.9);
        } else if (price >= 100000) {
            answer = (int)(price * 0.95);
        } else {
            answer = price;
        }
        return answer;
    }
}
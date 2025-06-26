class Solution {
    public int solution(int hp) {
        int answer = 0;
        /**
         * 장군개미 5
         * 병정개미 3
         * 일개미 1
         * 
         * hp를 사냥하기 위한 최소한의 병력 개미 수는?
         */
        while (hp > 0) {
            if (hp - 5 >= 0) {
                hp -= 5;
            } else if (hp - 3 >= 0) {
                hp -= 3;
            } else {
                --hp;
            }
            answer++;
        }
        return answer;
    }
}
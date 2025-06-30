class Solution {
    public int solution(int balls, int share) {
        long answer = 1;
        
        if (share > balls - share) {
            share = balls - share;
        }

        for (int i = 0; i < share; i++) {
            answer = answer * (balls - i) / (i + 1);
        }
        
        return (int) answer;
    }
}
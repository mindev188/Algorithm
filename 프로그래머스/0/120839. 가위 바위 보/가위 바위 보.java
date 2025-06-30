class Solution {
    public String solution(String rsp) {
        String answer = "";
        /*
         * 바위 0 
         * 가위 2 
         * 보 5
         */
        for (char a : rsp.toCharArray()) {
            switch (a) {
                case '0':
                    answer += '5'; break;
                case '2':
                    answer += '0'; break;
                case '5':
                    answer += '2'; break;
            }
        }
        return answer;
    }
}
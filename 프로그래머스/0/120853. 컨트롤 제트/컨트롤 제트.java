class Solution {
    public int solution(String s) {
        int answer = 0;
        int beforeNum = 0;
        for (String str : s.split(" ")) {
            if ("Z".equals(str)) {
                answer -= beforeNum;
            } else {
                beforeNum = Integer.valueOf(str);
                answer += beforeNum;
            }
        }
        return answer;
    }
}
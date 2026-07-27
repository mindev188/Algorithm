import java.util.Stack;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> subBelt = new Stack<>();
        int mainBeltBoxNum = 1;
        for (int i = 0; i < order.length; i++) {
            int orderBoxNum = order[i];
            /**
             * 1. main 벨트에서 발견하는 경우 종료
             * 2. sub 벨트에서 발견하는 경우 종료
             * 3. 둘다 아닌 경우 sub 벨트에 추가 및 다음 조건 진행
             *      - 만약 main 벨트값이 초과된 경우 끝
             */
            do {
                 if (orderBoxNum == mainBeltBoxNum) {
                    mainBeltBoxNum++;
                    answer++;
                    break;
                }
                if (!subBelt.isEmpty() && orderBoxNum == subBelt.peek()) {
                    subBelt.pop();
                    answer++;
                    break;
                }
                subBelt.add(mainBeltBoxNum++);
            } while (mainBeltBoxNum <= order.length);
        }

        return answer;
    }
}
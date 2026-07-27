import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Deque<Integer> subBelt = new ArrayDeque<>();
        int mainBeltBoxNum = 1;
        for (int orderBoxNum : order) {
            // orderBoxNum이 mainBeltBox 와 일치하지 않은 경우 subBelt에 다 올린다.
            while (mainBeltBoxNum < orderBoxNum) {
                subBelt.push(mainBeltBoxNum++);
            }

            /**
             * 1. main 벨트에서 발견하는 경우 종료
             * 2. sub 벨트에서 발견하는 경우 종료
             * 3. 둘다 아닌 경우 끝
             */
             if (orderBoxNum == mainBeltBoxNum) {
                mainBeltBoxNum++;
                answer++;
             } else if (!subBelt.isEmpty() && orderBoxNum == subBelt.peek()) {
                subBelt.pop();
                answer++;
             } else {
                 break;
             }
        }

        return answer;
    }
}
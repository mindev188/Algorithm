import java.util.Queue;
import java.util.LinkedList;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        /**
         * 1. 주식의 가격 Queue 선언
         * 2. while문 선언(!Queue.비었음)
         * 3. 하위 for문 선언 (해당 prices 의 인덱스부터 값이 떨어지는 값이 있을 때 까지) {
         *      있는 경우 해당 숫자까지 변수에 담음ㅁ
         * }
         * 4. 반환
         */
        Queue<Integer> pricesQueue = new LinkedList<Integer>();
        for (int i = 0; i < prices.length; i++) {
            pricesQueue.add(prices[i]);
        }

        int index = 0;
        while (!pricesQueue.isEmpty()) {
            int price = pricesQueue.poll();
            int sec = 0;
            for (int i = index + 1; i < prices.length; i++) {
                sec++;
                if (price > prices[i]) break;
            }
            answer[index++] = sec;
        }
        return answer;
    }
}
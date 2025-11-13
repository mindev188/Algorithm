import java.util.*;
class Solution {
    /**
     * 1. 각 기능이 100이 되는 시점까지 day를 증감
     * 2. 기능이 100 이상이 되는 시점에 successProgresses 를 증감.
     * 3. 다음 기능도 동일
     * 4. 만약 다음 기능이 100이 되지 못한다면 해당 successProgreeses 까지를 배열에 담음.
     * 5. 다음 배열에 담기.
     * 
     * @param progresses
     * @param speeds
     * @return
     */
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        // 총 경과일
        int totalDay = 1;
        // 해당 일자 완료 기능 갯수
        int successProgresses = 0;
        Queue<Integer>queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; ) {
            
            // 기능이 100이 된 경우
            if ((progresses[i] + speeds[i] * totalDay) >= 100) { 
                successProgresses++;
                i++;

            // 기능이 100이 되지 못한 경우
            } else { 
                // 앞에 완료된 기능들이 있는 경우
                if (successProgresses > 0) { 
                    // 해당 progresses 까지를 배열에 담기
                    queue.add(successProgresses);
                    successProgresses = 0;
                }
                totalDay++;
            }
        }

        queue.add(successProgresses);

        answer = queue.stream().mapToInt(a -> a).toArray();
        return answer;
    }
}
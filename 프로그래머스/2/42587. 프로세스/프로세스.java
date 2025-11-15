import java.util.LinkedList;
import java.util.Queue;
class Solution {
    /**
     * 1. queue에 각 인덱스 값을 넣어둔다.
     * 2. queue에서 pop한 인덱스의 prioritie 값의 우선 순위가 낮으면 다시 add
     * 3. 반복
     * 4. location과 일치하는 인덱스 값이 빠져나갈대 해당 값을 return
     * 
     * @param priorities
     * @param location
     * @return
     */
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(i);
        }

        int successCount = 0;
        while (!queue.isEmpty()) {
            int index = queue.poll();
            int priority = priorities[index];

            boolean isTopPriority = true;
            for (int a : queue) {
                // queue 안에 중요도가 더 놓은 프로세스가 있는 경우 다시 집어 넣는다
                if (priority < priorities[a]) {
                    queue.add(index);
                    isTopPriority = false;
                    break;
                }
            }

            // break가 안된 경우 처리
            // 없는 경우
            if (isTopPriority) {
                successCount++;
                if (location == index) return successCount;
            }
        }

        return answer;
    }
}
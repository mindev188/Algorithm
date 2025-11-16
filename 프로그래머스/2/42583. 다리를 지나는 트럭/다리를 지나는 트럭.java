import java.util.Queue;
import java.util.LinkedList;
class Solution {
    /**
     * 
     * 
     * @param bridge_length 다리 길이 (해당 초 만큼 트럭이 다리에 존재)
     * @param weight 최대 하중
     * @param truck_weights 트럭들 (무게)
     * @return
     */
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        /**
         * Queue 사용
         * 
         * 제약 조건 : 
         *  > 다리의 길이보다 많이 올라갈 수 없음
         *  > 다리의 최대 하중을 넘어갈 수 없음
         *  > 다리 길이만큼 각 트럭이 다리위에 존재함 (초 계산 필요)
         *  > 총 소요 시간 산정
         */
        int totalSec = 0; // 총 소요 시간
        int totalWeightInBridge = 0; // 현재 총 하중
        int nextTruckIndex = 0;
        Queue<Integer> bridgeQueue = new LinkedList<>();

        // 다리 길이 만큼 추가
        for (int i = 0; i < bridge_length; i++) {
            bridgeQueue.add(0);
        }

        while (true) {
            totalSec++;
            
            // 다음 트럭이없는 경우 종료
            if (nextTruckIndex >= truck_weights.length) break;

            // 다리를 내려오는 트럭
            if (!bridgeQueue.isEmpty()) {
                totalWeightInBridge -= bridgeQueue.poll();
            }

            // 다리에 올라오는 트럭
            int nextTruck = truck_weights[nextTruckIndex];
            if (bridge_length * 2 > bridgeQueue.size() && weight >= totalWeightInBridge + nextTruck) {
                bridgeQueue.add(nextTruck);
                totalWeightInBridge += nextTruck;
                nextTruckIndex++;
            } else {
                bridgeQueue.add(0);
            }

        }

        // 마지막 트럭이 다리에서 내려오는 시간 추가.
        totalSec += bridge_length - 1;


        return totalSec;
    }
}
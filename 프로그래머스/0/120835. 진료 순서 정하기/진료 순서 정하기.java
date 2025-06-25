class Solution {
   public static int[] solution(int[] emergency) {
        int arrSize = emergency.length;
        int[] answer = new int[arrSize];

        // 최대 중요도 구하기
        int maxEmergencyVal = 0;
        int maxEmergencyIndex = 0;
        for (int i = 0; i < arrSize; i++) {
            if (emergency[i] > maxEmergencyVal) {
                maxEmergencyVal = emergency[i];
                maxEmergencyIndex = i;
            }
        }

        // 이미 중요도가  매겨진 인덱스는 패스
        answer[maxEmergencyIndex] = 1;
        for (int i = 2; i <= arrSize; i++) {
            maxEmergencyVal = 0;
            for (int j = 0; j < arrSize; j++) {
                if (answer[j] > 0) continue;
                if (emergency[j] > maxEmergencyVal) {
                    maxEmergencyVal = emergency[j];
                    maxEmergencyIndex = j;
                }
            }
            answer[maxEmergencyIndex] = i;
        }
        return answer;
    }
}
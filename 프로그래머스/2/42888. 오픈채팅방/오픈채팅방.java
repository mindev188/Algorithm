import java.util.*;
class Solution {
    /**
     * 들어오고, 나가고, 변경하고
     * 해당 명령어들이 이뤄질 때마다 사용자의 아이디가 변경됨.
     * 이걸 레코드로 만들어 반환 해야함.
     *
     * 각 id 별로 닉네임을 매핑해서 진행해야 될 듯..
     * 1. 최종 닉네임들만 매칭
     * 2. 각 id 별 닉네임 - 레코드 생성 및 반환
     *
     * for 문 하위 map 사용해 id별 닉네임 매칭.?
     */
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();

        // 각 행별 사전 처리 사용자별 최종 id - nickname 계산
        int messageCount = record.length;
        for (int i = 0; i < record.length; i++) {
            String[] row = record[i].split(" ");
            String command = row[0];
            if ("Leave".equals(command)) continue;
            if ("Change".equals(command)) messageCount--;

            String id = row[1];
            String nickname = row[2];
            map.put(id, nickname);
        }

        // 최종 record 계산
        String[] answer = new String[messageCount];
        int answerIndex = 0;
        for (String row : record) {
            String[] arr = row.split(" ");
            String command = arr[0];
            String id = arr[1];

            if ("Change".equals(command)) continue;

            switch (command) {
                case "Enter":
                    answer[answerIndex++] = map.get(id) + "님이 들어왔습니다."; break;
                case "Leave":
                    answer[answerIndex++] = map.get(id) + "님이 나갔습니다."; break;
            }
        }

        return answer;
    }
}
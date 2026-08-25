import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (a, b) -> {
            if (a[0].equals(b[0]))
                return a[1].compareTo(b[1]);
            return a[0].compareTo(b[0]);
        });

        List<PriorityQueue<Integer>> rooms = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        queue.add(toMinute(book_time[0][1]));
        rooms.add(queue);

        // 예약 확인
        for (int i = 1; i < book_time.length; i++) {
            int targetStartTime = toMinute(book_time[i][0]);
            int targetEndTime = toMinute(book_time[i][1]);

            // 방 확인 (가장 빠른 방)
            int earlyRoom = -1;
            int earlyStartTime = Integer.MAX_VALUE;
            for (int j = 0; j < rooms.size(); j++) {
                PriorityQueue<Integer> room = rooms.get(j);

                if (room.peek() + 10 <= targetStartTime && room.peek() + 10 < earlyStartTime) {
                    earlyStartTime = room.peek() + 10;
                    earlyRoom = j;
                }
            }

            if (earlyRoom == -1) {
                PriorityQueue<Integer> newRoom = new PriorityQueue<>(Collections.reverseOrder());
                newRoom.add(targetEndTime);
                rooms.add(newRoom);
            } else {
                rooms.get(earlyRoom).add(targetEndTime);
            }
        }
        return rooms.size();
    }

    private int toMinute(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (a, b) -> {
            if (a[0].equals(b[0]))
                return a[1].compareTo(b[1]);
            return a[0].compareTo(b[0]);
        });

        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        // 예약 확인
        for (int i = 0; i < book_time.length; i++) {
            int start = toMinute(book_time[i][0]);
            int end = toMinute(book_time[i][1]) + 10;

            if (!rooms.isEmpty() && rooms.peek() <= start) {
                rooms.poll();
            }
            rooms.offer(end);
        }
        return rooms.size();
    }

    private int toMinute(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
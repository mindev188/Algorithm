import java.util.Arrays;
class Solution {
    public int[] solution(String[] strlist) {
        // null에 대한 값 처리 추가.
        if (strlist == null) return new int[0];
        return Arrays.stream(strlist).mapToInt(s -> s == null ? 0 : s.length()).toArray();
    }
}
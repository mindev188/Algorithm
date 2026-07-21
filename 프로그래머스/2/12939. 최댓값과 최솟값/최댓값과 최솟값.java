import java.util.*;
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] arr = s.split(" ");
        Arrays.sort(arr, (a, b) -> {
            return Integer.parseInt(a) - Integer.parseInt(b);
        });

        sb.append(arr[0]).append(" ").append(arr[arr.length - 1]);
        return sb.toString();
    }
}
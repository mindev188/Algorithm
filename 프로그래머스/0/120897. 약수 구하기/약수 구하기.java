import java.util.ArrayList;
class Solution {
    public int[] solution(int n) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) list.add(i);
        }
        list.add(n);

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
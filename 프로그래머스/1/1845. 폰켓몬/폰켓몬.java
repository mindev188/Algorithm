
import java.util.HashMap;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int maxChoose = nums.length / 2;

        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : nums) {
            if (hm.containsKey(num)) {
                hm.put(num, hm.getOrDefault(num, 0));
            } else {
                hm.put(num, 1);
            }
        }

        if (hm.size() < maxChoose) {
            answer = hm.size();
        } else {
            answer = maxChoose;
        }

        return answer;
    }
}
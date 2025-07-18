import java.util.HashSet;
class Solution {
    private HashSet<Integer> set; // static 제거하여 인스턴스 변수로 변경

    public int[] solution(int n) {
        set = new HashSet<Integer>();
        소인수분해(n);

        return set.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    public void 소인수분해(int n) {
        // 명시적인 base case 추가
        if (n <= 1) {
            return;
        }
        
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                set.add(i);
                소인수분해(n / i);
                break;
            }
        }
    }
}
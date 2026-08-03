import java.util.*;
class Solution {
    public long solution(int n) {
        final int mod = 1_234_567;

        if (n <= 2) {
            return n;
        }

        long previous = 1;
        long current = 2;

        for (int i = 3; i <= n; i++) {
            long next = (previous + current) % mod;
            previous = current;
            current = next;
        }

        return current;
    }
}
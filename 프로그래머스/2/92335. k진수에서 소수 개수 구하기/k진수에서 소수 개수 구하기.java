import java.util.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        while (n >= k) {
            sb.insert(0, n % k);
            n = n / k;
        }
        sb.insert(0, n % k);
        String[] arr = sb.toString().split("0");
        for (String a : arr) {
            if (a.equals("")) continue;
            if (isPrime(Long.valueOf(a))) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isPrime(long num) {
        if (num == 1) return false;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
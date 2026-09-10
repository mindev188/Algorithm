import java.util.Arrays;
class Solution {
    /**
     * - 그리디가 아닌 DP 사용, 그리디는 해당 선택이 최선인 경우만 사용
     * - DP를 통해 0원일 때부터 순차적으로 증가하며 amount 까지의 값의 최적의 해를 구함임
     */
    public int coinChange(int[] coins, int amount) {

        if (amount == 0) return 0;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int money = 1; money <= amount; money++) {
            for (int coin : coins) {
                if (coin > money) continue;

                dp[money] = Math.min(dp[money], dp[money - coin] + 1);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}

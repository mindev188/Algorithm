import java.util.*;

class Solution {
    int[] discounts = {10, 20, 30, 40};

    int bestSubscriber;
    int bestSales;

    int[][] users;
    int[] emoticons;
    int[] selected;

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        this.selected = new int[emoticons.length];

        this.bestSubscriber = -1;
        this.bestSales = -1;

        dfs(0);
        return new int[] {bestSubscriber, bestSales};
    }

    private void dfs(int idx) {
        if (idx == emoticons.length) {
            evaluate();
            return;
        }

        for (int d : discounts) {
            selected[idx] = d;
            dfs(idx + 1);
        }
    }

    private void evaluate() {
        int subscriber = 0;
        int sales = 0;

        for (int[] user : users) {
            int minDiscount = user[0];
            int limit = user[1];

            int sum = 0;
            for (int i = 0; i < emoticons.length; i++) {
                if (selected[i] >= minDiscount) {
                    sum += emoticons[i] * (100 - selected[i]) / 100;
                }
            }

            if (sum >= limit) subscriber++;
            else sales += sum;
        }

        if (subscriber > bestSubscriber) {
            bestSubscriber = subscriber;
            bestSales = sales;
        } else if (subscriber == bestSubscriber && sales > bestSales) {
            bestSales = sales;
        }
    }
}
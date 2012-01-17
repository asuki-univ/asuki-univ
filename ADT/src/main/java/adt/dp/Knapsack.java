package adt.dp;

class Item {
    public int weight; // 重さ
    public int value; // 価値
}

public class Knapsack {
    public int knapsack(Item[] items, int W) {
        int[][] dp = new int[items.length + 1][W + 1];
        for (int w = 0; w <= W; ++w)
            dp[0][w] = 0;

        for (int k = 1; k <= items.length; ++k) {
            Item item = items[k-1];
            for (int w = 0; w <= W; ++w) {
                dp[k][w] = dp[k-1][w];
                if (w-1 >= 0)
                    dp[k][w] = Math.max(dp[k][w], dp[k][w-1]);
                if (w - item.weight >= 0)
                    dp[k][w] = Math.max(dp[k][w], dp[k-1][w-item.weight] + item.value);
            }
        }

        return dp[items.length][W];
    }
}

package DynammicProgramming;

import java.util.Arrays;

public class BestTimeToBuyAndSellStock2 {
    public static void main(String[] args) {
        int[] prices = {7,6,4,3,1};

        System.out.println(maxProfit1(prices));
    }

    public static int maxProfit(int[] prices) {

        int[][] dp = new int[prices.length][2];
        for (int[] row : dp){
            Arrays.fill(row, -1);
        }
        return helper(0, 1, prices, dp);
    }

    private static int helper(int idx, int buy, int[] prices, int[][] dp) {

        if(idx == prices.length) return 0;

        if(dp[idx][buy] != -1) return dp[idx][buy];

        int profit = 0;
        if(buy == 1){
            int take = -prices[idx] + helper(idx + 1, 0, prices,dp);
            int notTake = helper(idx + 1,1, prices,dp);
            profit = Math.max(take, notTake);
        } else {
            int take = prices[idx] + helper(idx + 1, 1, prices,dp);
            int notTake = helper(idx + 1, 0, prices,dp);

            profit = Math.max(take, notTake);
        }

        return dp[idx][buy] = profit;
    }

    // Tabulation
    public static int maxProfit1(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n+1][2];

        dp[n][0] = 0;
        dp[n][1] = 0;

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy < 2; buy++) {
                int profit = 0;
                if(buy == 1){
                    int take = -prices[idx] + dp[idx + 1][0];
                    int notTake = dp[idx + 1][1];
                    profit = Math.max(take, notTake);
                } else {
                    int take = prices[idx] + dp[idx + 1][1];
                    int notTake = dp[idx + 1][0];

                    profit = Math.max(take, notTake);
                }

                dp[idx][buy] = profit;
            }
        }

        return dp[0][1];
    }
}

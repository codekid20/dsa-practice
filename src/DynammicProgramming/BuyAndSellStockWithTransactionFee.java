package DynammicProgramming;

import java.util.Arrays;

public class BuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        int[] prices = {1,3,7,5,10,3};
        int fee = 3;

        System.out.println(maxProfit(prices, fee));
    }

    public static int maxProfit(int[] prices, int fee) {

        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp){
            Arrays.fill(row, -1);
        }
        return profit(0, 1, prices, fee, dp);
    }

    private static int profit(int idx, int buy, int[] prices, int fee, int[][] dp) {

        if(idx == prices.length) return 0;

        int take;
        int notTake;

        if(dp[idx][buy] != -1) return dp[idx][buy];

        if(buy == 1){
            take = -prices[idx] + profit(idx + 1, 0, prices, fee, dp);
            notTake = profit(idx + 1, 1, prices, fee, dp);
        } else {
            take = prices[idx] - fee + profit(idx + 1, 1, prices, fee, dp);
            notTake = profit(idx + 1, 0, prices, fee, dp);
        }

        return dp[idx][buy] = Math.max(take, notTake);
    }


    // Tabulation

    public static int maxProfit1(int[] prices, int fee) {

        int n = prices.length;
        int[][] dp = new int[n+1][2];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {

                int take;
                int notTake;
                if(buy == 1){
                    take = -prices[idx] + dp[idx + 1][0];
                    notTake = dp[idx + 1][1];
                } else {
                    take = prices[idx] - fee + dp[idx + 1][1];
                    notTake = dp[idx + 1][0];
                }

                dp[idx][buy] = Math.max(take, notTake);
            }
        }

        return dp[0][1];
    }
}

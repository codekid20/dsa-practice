package DynammicProgramming;

import java.util.Arrays;

public class BuyAndSellStockWithCoolDown {
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        System.out.println(maxProfit1(prices));

    }

    public static int maxProfit(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][2];
        for(int[] row : dp) Arrays.fill(row, -1);
        return profit(0, 1, prices, dp);
    }

    private static int profit(int idx, int buy, int[] prices, int[][] dp) {

        if(idx > prices.length-1) return 0;

        if(dp[idx][buy] != -1 ) return dp[idx][buy];
        int take;
        int notTake;
        if(buy == 1){
            take = -prices[idx] + profit(idx + 1, 0, prices, dp);
            notTake = profit(idx + 1, 1, prices, dp);
        } else {
            take = prices[idx] + profit(idx + 2, 1, prices, dp);
            notTake = profit(idx + 1, 0, prices, dp);
        }
        
        return dp[idx][buy] = Math.max(take, notTake);
    }


    // Tabulation

    public static int maxProfit1(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n+2][2];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                int take;
                int notTake;
                if(buy == 1){
                    take = -prices[idx] + dp[idx + 1][0];
                    notTake = dp[idx + 1][1];
                } else {
                    take = prices[idx] + dp[idx + 2][1];
                    notTake = dp[idx + 1][0];
                }

                dp[idx][buy] = Math.max(take, notTake);
            }
        }

        return dp[0][1];
    }
}

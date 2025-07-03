package DynammicProgramming;

import java.util.Arrays;

public class BuyAndSellStock4 {
    public static void main(String[] args) {
        int k = 4;
        int[] prices = {1,2,4,2,5,7,2,4,9,0};

        System.out.println(maxProfit3(k, prices));
    }

    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][k+1];
        for (int[][] row : dp){
            for (int[] r : row){
                Arrays.fill(r, -1);
            }
        }

        return profit(0, 1, k, prices, dp);
    }

    private static int profit(int idx, int buy, int cap, int[] prices, int[][][] dp) {

        if(cap == 0) return 0;
        if(idx == prices.length) return 0;

        if(dp[idx][buy][cap] != -1) return dp[idx][buy][cap];
        int take;
        int notTake;
        if(buy == 1){
            take = -prices[idx] + profit(idx + 1, 0, cap, prices, dp);
            notTake = profit(idx + 1, 1, cap, prices,dp);

        } else {
            take = prices[idx] + profit(idx + 1, 1, cap - 1, prices,dp);
            notTake = profit(idx + 1, 0, cap, prices,dp);

        }
        return dp[idx][buy][cap] = Math.max(take, notTake);
    }


    // Tabulation

    public static int maxProfit1(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n+1][2][k+1]; // No need for base cases as dp is already initialized as 0.

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) { // works for cap = 0 as well
                    int take;
                    int notTake;
                    if(buy == 1){
                        take = -prices[idx] + dp[idx + 1][0][cap];
                        notTake = dp[idx + 1][1][cap];

                    } else {
                        take = prices[idx] + dp[idx + 1][1][cap - 1];
                        notTake = dp[idx + 1][0][cap];

                    }
                    dp[idx][buy][cap] = Math.max(take, notTake);
                }
            }
        }

        return dp[0][1][k];
    }

    // Space Optimization
    public static int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[2][k+1]; // No need for base cases as dp is already initialized as 0.

        for (int idx = n - 1; idx >= 0; idx--) {
            int[][] curr = new int[2][k+1];
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) { // works for cap = 0 as well
                    int take;
                    int notTake;
                    if(buy == 1){
                        take = -prices[idx] + dp[0][cap];
                        notTake = dp[1][cap];

                    } else {
                        take = prices[idx] + dp[1][cap - 1];
                        notTake = dp[0][cap];

                    }
                    curr[buy][cap] = Math.max(take, notTake);
                }


            }

            dp = curr;
        }

        return dp[1][k];
    }



    // Approach 2:
    // Using N * 4 DP
    public static int maxProfit3(int k, int[] prices) {
        int n = prices.length;
        int transAllowed = 2 * k;
        int[][] dp = new int[n][transAllowed];
        for (int[] row : dp){
            Arrays.fill(row, -1);

        }

        return profit4(0, 0, prices, dp, transAllowed);
    }


    private static int profit4(int idx, int trans, int[] prices, int[][] dp, int transAllowed) {

        if(idx == prices.length || trans == transAllowed) return 0;

        if(dp[idx][trans] != -1) return dp[idx][trans];
        int take;
        int notTake;
        if(trans % 2 == 0){
            take = -prices[idx] + profit4(idx + 1, trans + 1, prices, dp,transAllowed);
            notTake = profit4(idx + 1, trans, prices,dp, transAllowed);

        } else {
            take = prices[idx] + profit4(idx + 1, trans + 1, prices,dp, transAllowed);
            notTake = profit4(idx + 1, trans, prices,dp, transAllowed);

        }
        return dp[idx][trans] = Math.max(take, notTake);
    }
}

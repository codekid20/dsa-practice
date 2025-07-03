package DynammicProgramming;

import java.util.Arrays;

public class BuyAndSellStock3 {
    public static void main(String[] args) {
        int[] prices = {2,1,2,0,1};

        System.out.println(maxProfit5(prices));
    }

    public static int maxProfit(int[] prices) {

        int n = prices.length;
        int[][][] dp = new int[n][2][3];
        for (int[][] row : dp){
            for (int[] r : row){
                Arrays.fill(r, -1);
            }
        }

        return profit(0, 1, 2, prices, dp);
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

    public static int maxProfit1(int[] prices) {

        int n = prices.length;
        int[][][] dp = new int[n+1][2][3]; // No need for base cases as dp is already initialized as 0.

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) { // works for cap = 0 as well
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

        return dp[0][1][2];
    }

    // Space Optimization
    public static int maxProfit2(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[2][3]; // No need for base cases as dp is already initialized as 0.

        for (int idx = n - 1; idx >= 0; idx--) {
            int[][] curr = new int[2][3];
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) { // works for cap = 0 as well
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

        return dp[1][2];
    }



    // Approach 2:
    // N * 4 DP
    public static int maxProfit4(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][4];
        for (int[] row : dp){

            Arrays.fill(row, -1);
        }

        return profit4(0, 0 , prices, dp);
    }

    private static int profit4(int idx, int trans, int[] prices, int[][] dp) {

        if(idx == prices.length || trans == 4) return 0;

        if(dp[idx][trans] != -1) return dp[idx][trans];
        int take;
        int notTake;
        if(trans % 2 == 0){
            take = -prices[idx] + profit4(idx + 1, trans + 1, prices, dp);
            notTake = profit4(idx + 1, trans, prices,dp);

        } else {
            take = prices[idx] + profit4(idx + 1, trans + 1, prices,dp);
            notTake = profit4(idx + 1, trans, prices,dp);

        }
        return dp[idx][trans] = Math.max(take, notTake);
    }


    // Tabulation

    public static int maxProfit5(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n+1][5];

        for (int idx = n-1; idx >= 0; idx--) {
            for (int trans = 3; trans >= 0; trans--) {
                int take;
                int notTake;
                if(trans % 2 == 0){
                    take = -prices[idx] + dp[idx + 1][trans + 1];
                    notTake = dp[idx + 1][trans];

                } else {
                    take = prices[idx] + dp[idx + 1][trans + 1];
                    notTake = dp[idx + 1][ trans];

                }
                dp[idx][trans] = Math.max(take, notTake);
            }
        }

        return dp[0][0];
    }
}

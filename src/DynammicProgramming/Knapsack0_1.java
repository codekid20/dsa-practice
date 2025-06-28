package DynammicProgramming;

import java.util.Arrays;

public class Knapsack0_1 {
    public static void main(String[] args) {
        int W = 4;
        int[] val = {1, 2, 3};
        int[] wt = {4, 5, 1};

        System.out.println(knapsack1(W, val, wt));
    }

    public static int knapsack(int W, int[] val, int[] wt) {
        int n = val.length;
        int[][] dp = new int[n][W + 1];
        for(int[] row : dp) Arrays.fill(row, -1);
        return KS(n - 1, W, val, wt, dp);
    }

    private static int KS(int idx, int w, int[] val, int[] wt, int[][] dp) {

        if(idx == 0){
            if(wt[0] <= w) return val[0];
            else return 0;
        }

        if(dp[idx][w] != -1) return dp[idx][w];

        int notTake = KS(idx - 1, w, val, wt,dp);
        int take = Integer.MIN_VALUE;
        if(wt[idx] <= w){
            take = val[idx] + KS(idx - 1, w - wt[idx], val, wt, dp);
        }

        return dp[idx][w] = Math.max(take, notTake);
    }


    // Tabulation
    public static int knapsack1(int W, int[] val, int[] wt) {

        int n = val.length;
        int[][] dp = new int[n][W + 1];
        for (int i = wt[0]; i <= W; i++) {
            dp[0][i] = val[0];
        }

        for (int idx = 1; idx < n; idx++) {
            for (int weight = 0; weight <= W; weight++) {
                int notTake = dp[idx - 1][weight];
                int take = Integer.MIN_VALUE;
                if(wt[idx] <= weight){
                    take = val[idx] + dp[idx - 1][weight - wt[idx]];
                }

                dp[idx][weight] = Math.max(take, notTake);
            }
        }

        return dp[n-1][W];
    }


    // Space Optimization

    public static int knapsack2(int W, int[] val, int[] wt) {

        int n = val.length;
        int[] dp = new int[W + 1];
        int[] curr = new int[W + 1];
        for (int i = wt[0]; i <= W; i++) {
            dp[i] = val[0];
        }

        for (int idx = 1; idx < n; idx++) {
            for (int weight = 0; weight <= W; weight++) {
                int notTake = dp[weight];
                int take = Integer.MIN_VALUE;
                if(wt[idx] <= weight){
                    take = val[idx] + dp[weight - wt[idx]];
                }

                curr[weight] = Math.max(take, notTake);
            }

            dp = curr;
        }

        return dp[W];
    }
}

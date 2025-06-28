package DynammicProgramming;

import java.util.Arrays;

public class MinCostClimbingStairs {
    public static void main(String[] args) {
        int[] cost = {10,15,20};
        System.out.println(minCostClimbingStairs2(cost));
    }

    // Memoization
    public static int minCostClimbingStairs(int[] cost) {

        int n = cost.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return Math.min(minCost(cost, n - 1, dp), minCost(cost, n - 2, dp));
    }

    private static int minCost(int[] cost, int n, int[] dp) {
        if(n < 0){
            return 0;
        }

        if(n == 0 || n == 1) return cost[n];

        if(dp[n] != -1) return dp[n];

        return dp[n] = cost[n] + Math.min(minCost(cost, n - 1, dp), minCost(cost, n - 2, dp));
    }

    // Tabulation
    public static int minCostClimbingStairs1(int[] cost) {

        int n = cost.length;
        int[] dp = new int[n];

        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        return Math.min(dp[n-1], dp[n - 2]);
    }

    // Fine Tuning: Optimizing Space to O(1)
    public static int minCostClimbingStairs2(int[] cost) {

        int n = cost.length;
        int prev1 = cost[0];
        int prev = cost[1];

        for (int i = 2; i < n; i++) {
            int curr = cost[i] + Math.min(prev1, prev);
            prev1 = prev;
            prev = curr;
        }

        return Math.min(prev1, prev);
    }
}

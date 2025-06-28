package DynammicProgramming;

import java.util.Arrays;

/*
Problem: Unbounded Knapsack
Given weights and values of items, and a total capacity,
maximize the total value by picking items multiple times (unbounded).

Approach:
- This is a variation of the 0/1 Knapsack where we can pick the same item multiple times.

We implement 3 approaches:

1. Top-down (Recursion + Memoization):
   - Try both: not taking the current item or taking it (and staying at the same index).
   - Memoize results in dp[index][capacity].

2. Bottom-up (Tabulation):
   - Fill a 2D dp table: dp[i][cap] = max value using items[0..i] for given cap.
   - For "take", stay on same row: `val[i] + dp[i][cap - wt[i]]`.

3. Space Optimized:
   - Use 1D `dp` and `curr` arrays.
   - For "take", use `curr[cap - wt[idx]]` instead of `dp`, because we can reuse the same item.

Note:
- In all cases, "take" means we can pick the item again ⇒ stay in same index.
- Initialize base case: `dp[0][i] = (i / wt[0]) * val[0]`.

Time: O(n * capacity)
Space:
- Top-down: O(n * capacity) + recursion stack
- Tabulation: O(n * capacity)
- Space optimized: O(capacity)
*/

public class UnboundedKnapsack {
    public static void main(String[] args) {
        int[] val = {1, 1};
        int[] wt = {2, 1};
        int capacity = 3;

        System.out.println(knapSack2(val,wt,capacity));
    }


    public static int knapSack(int[] val, int[] wt, int capacity) {
        int n = wt.length;
        int[][] dp = new int[n][capacity + 1];
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return unboundedknapsack(n-1,capacity,wt,val, dp);
    }

    private static int unboundedknapsack(int idx, int capacity, int[] wt, int[] val, int[][] dp) {

        if(idx == 0){
            return ((int)(capacity / wt[0]) * val[0]);
        }

        if(dp[idx][capacity] != -1) return dp[idx][capacity];

        int notTake = unboundedknapsack(idx - 1, capacity, wt, val, dp);
        int take = Integer.MIN_VALUE;

        if(wt[idx] <= capacity){
            take = val[idx] + unboundedknapsack(idx, capacity - wt[idx], wt, val, dp);
        }

        return dp[idx][capacity] = Math.max(take, notTake);
    }

    // Tabulation

    public static int knapSack1(int[] val, int[] wt, int capacity){

        int n = wt.length;
        int[][] dp = new int[n][capacity + 1];

        for (int i = 0; i <= capacity; i++) {
            dp[0][i] = (int)(i / wt[0]) * val[0];
        }

        for (int idx = 1; idx < n; idx++) {
            for (int cap = 0; cap <= capacity; cap++) {
                int notTake = dp[idx - 1][cap];
                int take = Integer.MIN_VALUE;

                if(wt[idx] <= cap){
                    take = val[idx] + dp[idx][cap - wt[idx]];
                }

                dp[idx][cap] = Math.max(take, notTake);
            }
        }

        return dp[n - 1][capacity];
    }


    // Space Optimization

    public static int knapSack2(int[] val, int[] wt, int capacity){

        int n = wt.length;
        int[] dp = new int[capacity + 1];

        for (int i = 0; i <= capacity; i++) {
            dp[i] = (int)(i / wt[0]) * val[0];
        }

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[capacity + 1];
            for (int cap = 0; cap <= capacity; cap++) {
                int notTake = dp[cap];
                int take = Integer.MIN_VALUE;

                if(wt[idx] <= cap){
                    take = val[idx] + curr[cap - wt[idx]];
                }

                curr[cap] = Math.max(take, notTake);
            }

            dp = curr;
        }

        return dp[capacity];
    }
}

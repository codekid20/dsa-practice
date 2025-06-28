package DynammicProgramming;

import java.util.Arrays;

/*
Problem: Coin Change II (Number of combinations to make up a target amount)

Approach:
This is an Unbounded Knapsack variant:
- Use each coin any number of times to make up the target amount.
- Find total number of **combinations**, not minimum coins.

We implement 3 approaches:

1. Top-down (Recursion + Memoization):
   - Try taking or not taking each coin.
   - Memoize using a 2D array: dp[index][amount].
   - Base case: if idx == 0, return 1 if amount divisible by coins[0], else 0.

2. Bottom-up (Tabulation):
   - Fill dp[i][amt] = number of ways to form amt using coins[0..i].
   - Use previous row for notTake, current row for take (since we can reuse the coin).

3. Space Optimized:
   - Use two 1D arrays (`dp` and `curr`) to reduce space.
   - For "take", refer to `curr[amt - coins[coin]]` as coins can be used multiple times.

Note:
- In all approaches, we **add** take and notTake since we are counting total combinations.
- This is different from the min coin problem (which uses `Math.min`).

Time: O(n * amount)
Space:
- Top-down: O(n * amount) + recursion stack
- Tabulation: O(n * amount)
- Space optimized: O(amount)
*/

public class coinChange2 {
    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1,2,5};
        System.out.println(change2(amount, coins));
    }

    public static int change(int amount, int[] coins) {

        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp){
            Arrays.fill(row, -1);
        }

        return coins(n-1,coins, amount, dp);
    }

    private static int coins(int idx, int[] coins, int amount, int[][] dp) {

        if(idx == 0){
            if(amount % coins[idx] == 0){
                return 1;
            } else {
                return 0;
            }
        }

        if(dp[idx][amount] != -1) return dp[idx][amount];

        int notTake = coins(idx - 1, coins, amount,dp);
        int take = 0;
        if(coins[idx] <= amount){
            take = take + coins(idx, coins, amount - coins[idx],dp);
        }
        return dp[idx][amount] = notTake + take;
    }


    // tabulation

    public static int change1(int amount, int[] coins) {

        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int i = 0; i <= amount; i++) {
            if(i % coins[0] == 0){
                dp[0][i] = 1;
            } else dp[0][i] = 0;
        }

        for (int coin = 1; coin < n; coin++) {
            for (int amt = 0; amt <= amount; amt++) {

                int notTake = dp[coin - 1][amt];
                int take = 0;
                if(coins[coin] <= amt){
                    take = dp[coin][amt - coins[coin]];
                }


                dp[coin][amt] = take+notTake;
            }
        }

        return dp[n-1][amount];
    }

    public static int change2(int amount, int[] coins) {

        int n = coins.length;
        int[] dp = new int[amount + 1];

        for (int i = 0; i <= amount; i++) {
            if(i % coins[0] == 0){
                dp[i] = 1;
            } else dp[i] = 0;
        }

        for (int coin = 1; coin < n; coin++) {
            int[] curr = new int[amount + 1];
            for (int amt = 0; amt <= amount; amt++) {

                int notTake = dp[amt];
                int take = 0;
                if(coins[coin] <= amt){
                    take = curr[amt - coins[coin]]; // because we can take same again for counting we use current row and not previous row. thats why we used curr here and not dp
                }

                curr[amt] = take+notTake;
            }

            dp = curr;
        }

        return dp[amount];
    }
}

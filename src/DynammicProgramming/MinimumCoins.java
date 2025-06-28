package DynammicProgramming;

import java.util.Arrays;

/*
Problem: Minimum Coins to Make a Target Amount

Approach:
This is an Unbounded Knapsack problem:
- We can use each coin multiple times to make up the target amount.

We implement 3 approaches:

1. Top-down (Recursion + Memoization):
   - Try taking or not taking each coin.
   - Memoize results using a 2D dp array: dp[index][amount].
   - Base case: if index == 0, check if amount is divisible by coins[0].

2. Bottom-up (Tabulation):
   - Build a 2D dp table where dp[i][amt] is min coins needed using first i coins to form amt.
   - First row (0th coin) is filled using multiples of coins[0].

3. Space Optimized:
   - Use two 1D arrays: `dp` (previous row), `curr` (current row).
   - Key detail: For "take", use `curr[amt - coins[coin]]` because we can take the same coin again.

Edge Case:
- If no combination is found, return -1 (we treat unreachable amounts with a large value like 1e9).

Time: O(n * amount)
Space:
- Top-down: O(n * amount) + recursion stack
- Tabulation: O(n * amount)
- Space optimized: O(amount)
*/

public class MinimumCoins {
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(coinChange2(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {

        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp){
            Arrays.fill(row, -1);
        }
        int coin = coins(n-1,coins, amount, dp);
        if(coin == (int) 1e9) return -1;
        else return coin;
    }

    private static int coins(int idx, int[] coins, int amount, int[][] dp) {

        if(idx == 0){
            if(amount % coins[idx] == 0){
                return amount / coins[idx];
            } else {
                return (int)1e9;
            }
        }

        if(dp[idx][amount] != -1) return dp[idx][amount];

        int notTake = coins(idx - 1, coins, amount,dp);
        int take;
        if(coins[idx] <= amount){
            take = 1 + coins(idx, coins, amount - coins[idx],dp);
        } else {
            take = (int) 1e9;
        }

        return dp[idx][amount] = Math.min(notTake, take);
    }

    // Tabulation

    public static int coinChange1(int[] coins, int amount) {

        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int i = 0; i <= amount; i++) {
            if(i % coins[0] == 0){
                dp[0][i] = i / coins[0];
            } else dp[0][i] = (int) 1e9;
        }

        for (int coin = 1; coin < n; coin++) {
            for (int amt = 0; amt <= amount; amt++) {

                int notTake = dp[coin - 1][amt];
                int take;
                if(coins[coin] <= amt){
                    take = 1 + dp[coin][amt - coins[coin]];
                } else {
                    take = (int) 1e9;
                }

                dp[coin][amt] = Math.min(take,notTake);
            }
        }
        if(dp[n-1][amount] == (int) 1e9) return -1;
        return dp[n-1][amount];
    }


    // Space Optimization

    public static int coinChange2(int[] coins, int amount) {

        int n = coins.length;
        int[] dp = new int[amount + 1];

        for (int i = 0; i <= amount; i++) {
            if(i % coins[0] == 0){
                dp[i] = i / coins[0];
            } else dp[i] = (int) 1e9;
        }

        for (int coin = 1; coin < n; coin++) {
            int[] curr = new int[amount + 1];
            for (int amt = 0; amt <= amount; amt++) {

                int notTake = dp[amt];
                int take;
                if(coins[coin] <= amt){
                    take = 1 + curr[amt - coins[coin]]; // because we can take same again for counting we use current row and not previous row. thats why we used curr here and not dp
                } else {
                    take = (int) 1e9;
                }

                curr[amt] = Math.min(take,notTake);
            }

            dp = curr;
        }

        if(dp[amount] == (int) 1e9) return -1;
        return dp[amount];
    }
}

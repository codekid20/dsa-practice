package DynammicProgramming;

import java.util.Arrays;

/*
Problem: Rod Cutting Problem
You are given a rod of length N and an array `price` where price[i] is the price of a rod of length i+1.
You can cut the rod into any number of pieces. Maximize the total value obtained.

Approach:
This is an Unbounded Knapsack variant:
- We can pick the same piece length multiple times.
- For each index (piece length), we choose to either take it or not:
    - Not take: move to smaller piece length (idx - 1)
    - Take: reduce remaining rod length by current piece length (idx + 1), and add its price

We implement 3 approaches:

1. Top-down (Recursion + Memoization):
   - Use dp[idx][size] to memoize max value by considering rod pieces up to index `idx` and remaining rod size `size`.

2. Bottom-up (Tabulation):
   - Build dp table where dp[i][j] = max price for rod of size `j` using pieces up to index `i`.
   - Base case: dp[0][j] = j * price[0] (only use piece of length 1).

3. Space Optimized:
   - Use two 1D arrays: `dp` and `curr`.
   - For "take", use `curr[size - rodLength]` since we can reuse the same piece.

Time: O(N * N)
Space:
- Top-down: O(N * N) + recursion stack
- Tabulation: O(N * N)
- Space optimized: O(N)

*/

public class RodCutting {
    public static void main(String[] args) {
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20};

        System.out.println(cutRod2(price));
    }

    public static int cutRod(int[] price) {

        int n = price.length;
        int N = n;
        int[][] dp = new int[n][N + 1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return rodCutting(n - 1, N, price, dp);
    }

    private static int rodCutting(int idx, int size, int[] price, int[][] dp) {

        if(idx == 0){
            return size * price[0];
        }

        if(dp[idx][size] != -1) return dp[idx][size];

        int notTake = rodCutting(idx - 1, size, price, dp);
        int take = Integer.MIN_VALUE;
        int rodLength = idx + 1;
        if(rodLength <= size){
            take = price[idx] + rodCutting(idx, size - rodLength, price, dp);
        }

        return dp[idx][size] = Math.max(notTake, take);
    }

    // Tabulation

    public static int cutRod1(int[] price) {

        int n = price.length;
        int N = n;
        int[][] dp = new int[n][N + 1];

        for (int i = 0; i <= N; i++) {
            dp[0][i] = i * price[0];
        }

        for (int idx = 1; idx < n; idx++) {
            for (int size = 0; size <= N; size++) {

                int notTake = dp[idx - 1][ size];
                int take = Integer.MIN_VALUE;
                int rodLength = idx + 1;
                if(rodLength <= size){
                    take = price[idx] + dp[idx][size - rodLength];
                }

                dp[idx][size] = Math.max(notTake,take);
            }
        }

        return dp[n-1][N];
    }


    // Space Optimization

    public static int cutRod2(int[] price) {

        int n = price.length;
        int N = n;
        int[] dp = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            dp[i] = i * price[0];
        }

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[N + 1];
            for (int size = 0; size <= N; size++) {

                int notTake = dp[ size];
                int take = Integer.MIN_VALUE;
                int rodLength = idx + 1;
                if(rodLength <= size){
                    take = price[idx] + curr[size - rodLength];
                }

                curr[size] = Math.max(notTake,take);
            }

            dp = curr;
        }

        return dp[N];
    }
}

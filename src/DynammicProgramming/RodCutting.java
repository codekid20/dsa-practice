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

    /*
     * ROD CUTTING PROBLEM
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given a rod of length N and an array of prices where price[i] represents
     * the price of a rod piece of length (i+1). Cut the rod into pieces to
     * MAXIMIZE the total profit.
     *
     * Example: price = [2, 5, 7, 8, 10], N = 5
     *   - Rod length = 5
     *   - price[0]=2 means length-1 piece costs 2
     *   - price[1]=5 means length-2 piece costs 5, etc.
     *
     * Possible cuts:
     *   - No cut: length 5 → profit = 10
     *   - Cut into 2+3: profit = 5+7 = 12
     *   - Cut into 1+1+1+1+1: profit = 2*5 = 10
     *   - Cut into 2+2+1: profit = 5+5+2 = 12
     *   Maximum profit = 12
     *
     * KEY INSIGHT - UNBOUNDED KNAPSACK VARIANT:
     * ------------------------------------------
     * This is similar to Unbounded Knapsack because:
     *   - We can use the SAME piece length MULTIPLE times
     *   - Unlike 0/1 Knapsack where each item used once
     *
     * Mapping to Unbounded Knapsack:
     *   - Rod length (N) = Knapsack capacity
     *   - Piece lengths [1, 2, 3, ..., n] = Item weights
     *   - Prices [price[0], price[1], ...] = Item values
     *   - Goal: Maximize value while filling capacity exactly
     *
     * CORE LOGIC:
     * -----------
     * At each index, we have TWO choices:
     *   1. NOT TAKE: Don't cut a piece of this length
     *   2. TAKE: Cut a piece of this length (can use AGAIN - unbounded!)
     *
     * CRITICAL DIFFERENCE from 0/1 Knapsack:
     * When we TAKE a piece, we stay at SAME index (idx) instead of moving
     * to (idx-1), because we can cut multiple pieces of same length!
     *
     * ==================== MEMOIZATION APPROACH ====================
     */

    public static int cutRod(int[] price) {
        int n = price.length;
        int N = n;  // Rod length equals number of price options

        // dp[idx][size] = Maximum profit using pieces from index 0 to idx
        //                 with remaining rod size = size
        int[][] dp = new int[n][N + 1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return rodCutting(n - 1, N, price, dp);
    }

    private static int rodCutting(int idx, int size, int[] price, int[][] dp) {
        // Base case: At first piece (length = 1)
        // We can cut as many pieces of length 1 as the size allows
        // Profit = size * price[0]
        // Example: If size=5 and price[0]=2, we get 5*2 = 10
        if(idx == 0){
            return size * price[0];
        }

        // Return memoized result if already computed
        if(dp[idx][size] != -1) return dp[idx][size];

        // Choice 1: NOT TAKE - Don't cut piece of length (idx+1)
        // Move to previous index with same size
        int notTake = rodCutting(idx - 1, size, price, dp);

        // Choice 2: TAKE - Cut piece of length (idx+1)
        int take = Integer.MIN_VALUE;
        int rodLength = idx + 1;  // Actual length of piece at this index

        // Only if this piece fits in remaining size
        if(rodLength <= size){
            // CRITICAL: Stay at SAME idx (unbounded - can reuse)
            // Add price[idx] and reduce size by rodLength
            take = price[idx] + rodCutting(idx, size - rodLength, price, dp);
        }

        // Return maximum profit from both choices
        return dp[idx][size] = Math.max(notTake, take);
    }

    /*
     * EXAMPLE WALKTHROUGH (Memoization):
     * -----------------------------------
     * price = [1, 5, 8], N = 3
     * Piece lengths: [1, 2, 3]
     *
     * rodCutting(idx=2, size=3):
     *   notTake: rodCutting(1, 3)
     *   take: 8 + rodCutting(2, 0) = 8 + 0 = 8
     *
     * rodCutting(idx=1, size=3):
     *   notTake: rodCutting(0, 3) = 3*1 = 3
     *   take: 5 + rodCutting(1, 1)
     *         → 5 + 5 + rodCutting(1, -1) = can't fit
     *         → 5 + notTake(0,1) = 5 + 1 = 6
     *   Max = 6
     *
     * Final: max(8, 6) = 8 (cut into single piece of length 3)
     *
     * ==================== TABULATION APPROACH ====================
     */

    public static int cutRod1(int[] price) {
        int n = price.length;
        int N = n;

        // dp[idx][size] = Maximum profit using pieces from 0 to idx
        //                 with rod size = size
        int[][] dp = new int[n][N + 1];

        // Base case: Fill first row (idx=0, piece length=1)
        // For any size i, we can cut i pieces of length 1
        // Profit = i * price[0]
        for (int i = 0; i <= N; i++) {
            dp[0][i] = i * price[0];
        }

        // Fill the DP table bottom-up
        for (int idx = 1; idx < n; idx++) {
            for (int size = 0; size <= N; size++) {

                // Choice 1: NOT TAKE - Don't use piece of length (idx+1)
                // Copy result from previous row (previous piece length)
                int notTake = dp[idx - 1][size];

                // Choice 2: TAKE - Use piece of length (idx+1)
                int take = Integer.MIN_VALUE;
                int rodLength = idx + 1;

                // Only if piece fits in remaining size
                if(rodLength <= size){
                    // CRITICAL: Use SAME row dp[idx] (unbounded)
                    // Not dp[idx-1] like in 0/1 Knapsack!
                    take = price[idx] + dp[idx][size - rodLength];
                }

                // Store maximum profit
                dp[idx][size] = Math.max(notTake, take);
            }
        }

        // Answer: Maximum profit using all pieces with full rod length
        return dp[n-1][N];
    }

    /*
     * DP TABLE VISUALIZATION (Tabulation):
     * -------------------------------------
     * price = [2, 5, 7], N = 3
     * Piece lengths: [1, 2, 3]
     *
     *         size →  0   1   2   3
     *    idx ↓
     *    0 (len=1)   0   2   4   6   (can cut 0,1,2,3 pieces of length 1)
     *    1 (len=2)   0   2   5   7   (max of: don't use len-2, or use len-2)
     *    2 (len=3)   0   2   5   7   (max profit with all options)
     *
     * At dp[1][3]:
     *   notTake = dp[0][3] = 6 (three pieces of length 1)
     *   take = price[1] + dp[1][1] = 5 + 2 = 7 (one len-2 + one len-1)
     *   Max = 7
     *
     * At dp[2][3]:
     *   notTake = dp[1][3] = 7
     *   take = price[2] + dp[2][0] = 7 + 0 = 7 (one piece of length 3)
     *   Max = 7
     *
     * WHY UNBOUNDED (SAME INDEX)?
     * ---------------------------
     * When we take a piece:
     *   - Memoization: rodCutting(idx, size - rodLength) → SAME idx
     *   - Tabulation: dp[idx][size - rodLength] → SAME row
     *
     * This allows us to cut MULTIPLE pieces of same length!
     *
     * Example: For length 4 with piece length 2:
     *   - First cut: take piece-2, remaining=2
     *   - Second cut: take piece-2 again, remaining=0
     *   - Used piece-2 TWICE → Unbounded!
     *
     * TIME COMPLEXITY: O(n * N)
     *   - Two nested loops: n pieces × N sizes
     *
     * SPACE COMPLEXITY:
     *   - Memoization: O(n * N) + O(n) recursion stack
     *   - Tabulation: O(n * N) only
     *
     * KEY INSIGHT:
     * ------------
     * Rod Cutting = Unbounded Knapsack where:
     *   - Each piece length can be used UNLIMITED times
     *   - Recurrence uses SAME index when taking (not idx-1)
     *   - This single change transforms 0/1 to unbounded variant!
     *
     * The problem elegantly demonstrates how a small modification
     * (staying at same index vs moving to previous) fundamentally
     * changes the problem from bounded to unbounded selection!
     */


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

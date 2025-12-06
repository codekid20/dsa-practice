package DynammicProgramming;

import java.util.Arrays;

public class SubsetSumEqualsToK {
    public static void main(String[] args) {
        int[] arr = {4,3,2,1};
        int k = 5;

        System.out.println(subsetSumToK(arr.length, k, arr));
    }

    /*
     * SUBSET SUM PROBLEM - MEMOIZATION APPROACH
     *
     * INTUITION:
     * ----------
     * We need to find if there exists any subset of the array that sums up to target 'k'.
     * At each element, we have TWO CHOICES:
     *   1. Include the current element in the subset (TAKE)
     *   2. Exclude the current element from the subset (NOT TAKE)
     *
     * This creates a decision tree where we explore all possible combinations.
     * To avoid recomputing same subproblems, we use memoization (DP).
     *
     * LOGIC/APPROACH:
     * ---------------
     * 1. Start from the last index (n-1) and work backwards to index 0
     *
     * 2. BASE CASES:
     *    - If target becomes 0: We found a valid subset! Return TRUE
     *    - If we reach index 0: Check if arr[0] equals target (last chance)
     *
     * 3. MEMOIZATION CHECK:
     *    - If dp[index][target] is already computed, return it directly
     *    - This avoids redundant recursive calls
     *
     * 4. RECURSIVE CHOICES:
     *    a) NOT TAKE: Move to previous index with same target
     *       - subsetSum(index-1, target, arr, dp)
     *
     *    b) TAKE: Only if arr[index] <= target (can't take if element > target)
     *       - Reduce target by arr[index] and move to previous index
     *       - subsetSum(index-1, target - arr[index], arr, dp)
     *
     * 5. RESULT:
     *    - If EITHER choice returns true, we found a valid subset
     *    - Store result in dp[index][target] for future use
     *
     * DP STATE:
     * ---------
     * dp[index][target] = Can we make 'target' sum using elements from index 0 to 'index'?
     *
     * TIME COMPLEXITY: O(n * k) - We compute each state once
     * SPACE COMPLEXITY: O(n * k) for DP table + O(n) for recursion stack
     *
     * KEY INSIGHT:
     * ------------
     * This is a classic 0/1 Knapsack variant where we choose to include/exclude elements
     * to achieve a target sum instead of maximizing value under weight constraint.
     */

    public static boolean subsetSumToK(int n, int k, int[] arr){

        Boolean[][] dp = new Boolean[n][k+1];

        return subsetSum(n-1,k,arr, dp);
    }

    public static boolean subsetSum(int index, int target, int[] arr, Boolean[][] dp){

        if(target == 0) return true;
        if(index == 0) return (arr[0] == target);

        if(dp[index][target] != null) return dp[index][target];
        boolean notTake = subsetSum(index - 1, target, arr, dp);
        boolean take = false;
        if(arr[index] <= target){
            take = subsetSum(index - 1, target - arr[index], arr, dp);
        }

        return dp[index][target] = take || notTake;
    }

    // Tabulation
    /*
     * SUBSET SUM PROBLEM - TABULATION (BOTTOM-UP DP) APPROACH
     *
     * INTUITION:
     * ----------
     * Instead of using recursion with memoization (top-down), we build the solution
     * iteratively from bottom-up. We solve smaller subproblems first and use their
     * results to solve larger subproblems.
     *
     * We fill a 2D table where dp[i][j] represents:
     * "Can we achieve sum 'j' using elements from index 0 to i?"
     *
     * LOGIC/APPROACH:
     * ---------------
     * 1. INITIALIZATION:
     *    - Create dp[n][k+1] table and fill with false (no subset found initially)
     *    - Set dp[i][0] = true for all i (we can always make sum 0 with empty subset)
     *    - Set dp[0][arr[0]] = true (using first element, we can make sum = arr[0])
     *
     * 2. FILLING THE DP TABLE:
     *    - Iterate through each index (1 to n-1)
     *    - For each index, iterate through all possible targets (1 to k)
     *    - At each cell dp[index][target], we have two choices:
     *
     *      a) NOT TAKE current element:
     *         - Copy result from previous row: dp[index-1][target]
     *         - "Can we make 'target' without using current element?"
     *
     *      b) TAKE current element (only if arr[index] <= target):
     *         - Check dp[index-1][target - arr[index]]
     *         - "If we take current element, can we make remaining sum?"
     *
     *    - dp[index][target] = take OR notTake
     *      (true if EITHER choice gives us the target sum)
     *
     * 3. FINAL ANSWER:
     *    - dp[n-1][k] tells us if we can make sum 'k' using all elements (0 to n-1)
     *
     * DP TABLE VISUALIZATION (Example: arr=[3,1,2], k=4):
     * ----------------------------------------------------
     *         Target →  0    1    2    3    4
     *    Index ↓
     *      0 (3)      TRUE FALSE FALSE TRUE FALSE
     *      1 (1)      TRUE TRUE FALSE TRUE TRUE   <- (3+1=4)
     *      2 (2)      TRUE TRUE TRUE TRUE TRUE    <- (2+2=4, 3+1=4)
     *
     * KEY DIFFERENCES FROM MEMOIZATION:
     * ----------------------------------
     * - No recursion, no stack overflow risk
     * - Iterative approach, easier to optimize space later
     * - Fills table in predictable order (row by row)
     * - Same time/space complexity but better constants
     *
     * TIME COMPLEXITY: O(n * k) - Two nested loops
     * SPACE COMPLEXITY: O(n * k) - 2D DP table only (no recursion stack)
     *
     * ADVANTAGE OVER MEMOIZATION:
     * ---------------------------
     * - Can be easily optimized to O(k) space using two 1D arrays (prev, curr)
     * - Better cache locality (sequential memory access)
     * - No function call overhead
     */

    public static boolean subsetSumToK1(int n, int k, int[] arr){

        Boolean[][] dp = new Boolean[n][k+1];
        for (Boolean[] row : dp){
            Arrays.fill(row, false);
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if(arr[0] <= k){
            dp[0][arr[0]] = true;
        }


        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[index - 1][target];
                boolean take = false;
                if(arr[index] <= target){
                    take = dp[index - 1][target - arr[index]];
                }

                dp[index][target] = take || notTake;
            }
        }

        return dp[n-1][k];
    }

    // Optimizing Space
    public static boolean subsetSumToK2(int n, int k, int[] arr){

        Boolean[] dp = new Boolean[k+1];

        Arrays.fill(dp, false);


        dp[0] = true;

        if(arr[0] <= k){
            dp[arr[0]] = true;
        }


        for (int index = 1; index < n; index++) {
            Boolean[] curr = new Boolean[k+1];
            Arrays.fill(curr, false);
            curr[0] = true;
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[target];
                boolean take = false;
                if(arr[index] <= target){
                    take = dp[target - arr[index]];
                }

                curr[target] = take || notTake;
            }

            dp = curr;
        }

        return dp[k];
    }
}

package DynammicProgramming;

import java.util.Arrays;

public class CountSubsetsWithSumK {
    public static void main(String[] args) {
        int[] arr =  {5, 2, 6, 4};
        int target = 7;

        System.out.println(perfectSum(arr,target));
    }

    /*
     * COUNT SUBSETS WITH SUM K (PERFECT SUM PROBLEM)
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given an array and a target sum, count the NUMBER of subsets whose sum equals target.
     *
     * Example: nums = [1, 2, 3, 3], target = 6
     *   - Subsets: [1, 2, 3], [1, 2, 3], [3, 3]
     *   - Count = 3
     *
     * KEY DIFFERENCE FROM PREVIOUS PROBLEMS:
     * --------------------------------------
     * - Previous: "Can we make sum K?" → Boolean (true/false)
     * - This: "How many ways can we make sum K?" → Integer (count)
     *
     * Instead of returning true/false, we COUNT the number of valid subsets!
     *
     * KEY INTUITION:
     * --------------
     * At each element, we have TWO choices:
     *   1. NOT TAKE: Count subsets without current element
     *   2. TAKE: Count subsets that include current element
     *
     * Total count = count(NOT TAKE) + count(TAKE)
     *
     * CRITICAL: HANDLING ZEROS
     * -------------------------
     * Zeros are TRICKY because:
     *   - Including/excluding a zero doesn't change the sum
     *   - If we have a zero, we can either take it or leave it → 2 choices
     *
     * Special case at index 0:
     *   - If sum == 0 AND nums[0] == 0:
     *     → We have 2 ways: {} (empty) or {0} (include zero)
     *     → Return 2
     *
     *   - If sum == 0 OR nums[0] == sum:
     *     → Exactly 1 way to achieve the sum
     *     → Return 1
     *
     *   - Otherwise: Return 0 (impossible)
     *
     * APPROACH:
     * ---------
     * 1. INITIALIZATION:
     *    - Create dp[n][target+1] table initialized with -1
     *    - dp[i][j] = Number of subsets from index 0 to i with sum = j
     *
     * 2. BASE CASE (index == 0):
     *    Three scenarios:
     *    a) sum == 0 AND nums[0] == 0 → return 2
     *       (both empty subset and {0} give sum 0)
     *
     *    b) sum == 0 OR nums[0] == sum → return 1
     *       (either empty subset gives 0, or {nums[0]} gives sum)
     *
     *    c) Otherwise → return 0
     *       (no way to achieve the sum with just nums[0])
     *
     * 3. MEMOIZATION CHECK:
     *    - If dp[index][sum] != -1, return cached result
     *
     * 4. RECURSIVE CHOICES:
     *    a) NOT TAKE: Count subsets without current element
     *       → countSubset(index-1, sum, nums, dp)
     *
     *    b) TAKE: Count subsets that include current element
     *       → Only if nums[index] <= sum
     *       → countSubset(index-1, sum - nums[index], nums, dp)
     *
     * 5. RESULT:
     *    - Total ways = take + notTake
     *    - Store in dp and return
     *
     * EXAMPLE WALKTHROUGH:
     * --------------------
     * nums = [1, 1, 2], target = 2
     *
     * Decision tree:
     *                      index=2, sum=2
     *                    /              \
     *          notTake: {1,1}        take: {1,1} with 2
     *          sum=2                  sum=0 ✓ (count=1)
     *         /        \
     *    {1} sum=2  {1} with 1
     *    (count=0)   sum=1
     *               /      \
     *          {} sum=1  {1}
     *          (0)      sum=0 ✓ (count=1)
     *
     * Total ways = 1 (first 1) + 1 (second 1) = 2 subsets: [1,1] and [2]
     *
     * WHY BASE CASE RETURNS 2 FOR ZEROS?
     * -----------------------------------
     * Example: nums = [0], target = 0
     * Subsets that sum to 0: {} and {0}
     * Count = 2
     *
     * This is because including or excluding 0 doesn't change the sum,
     * so both are valid distinct subsets!
     *
     * DP STATE:
     * ---------
     * dp[index][sum] = Number of ways to achieve 'sum' using elements from 0 to index
     *
     * TIME COMPLEXITY: O(n * target)
     *   - n elements, target+1 possible sums
     *   - Each state computed once
     *
     * SPACE COMPLEXITY: O(n * target) + O(n)
     *   - DP table: O(n * target)
     *   - Recursion stack: O(n)
     *
     * KEY INSIGHT:
     * ------------
     * This extends subset sum from "existence" (boolean) to "counting" (integer).
     * The transition changes from OR logic (take || notTake) to SUM logic (take + notTake).
     *
     * The zero handling is crucial - it creates multiple distinct subsets with same sum!
     */

    public static int perfectSum(int[] nums, int target) {

        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        for(int[] row : dp) Arrays.fill(row, -1);
        return countSubset(n - 1, target, nums, dp);
    }

    private static int countSubset(int index, int sum, int[] nums, int[][] dp) {

        if (index == 0) {
            if (sum == 0 && nums[0] == 0) return 2;
            if (sum == 0 || nums[0] == sum) return 1;
            return 0;
        }

        if(dp[index][sum] != -1) return dp[index][sum];

        int notTake = countSubset(index - 1, sum, nums, dp);
        int take = 0;
        if(nums[index] <= sum){
            take += countSubset(index - 1, sum - nums[index], nums, dp);
        }

        return dp[index][sum] = take + notTake;
    }

    // Tabulation

    public static int perfectSum1(int[] nums, int target) {

        int n = nums.length;
        int[][] dp = new int[n][target + 1];

        // Initialize first row carefully
        if (nums[0] == 0) {
            dp[0][0] = 2; // include and exclude
        } else {
            dp[0][0] = 1; // only exclude
            if (nums[0] <= target) {
                dp[0][nums[0]] = 1; // only include
            }
        }

        for (int index = 1; index < n; index++) {
            for (int sum = 0; sum <= target; sum++) {

                int notTake = dp[index - 1][sum];
                int take = 0;
                if(nums[index] <= sum){
                    take += dp[index - 1][sum - nums[index]];
                }

                dp[index][sum] = take + notTake;
            }
        }

        return dp[n-1][target];
    }
}

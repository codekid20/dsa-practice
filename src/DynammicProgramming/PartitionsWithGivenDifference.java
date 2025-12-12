package DynammicProgramming;

import java.util.Arrays;

public class PartitionsWithGivenDifference {
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 1, 1, 5, 3, 3, 1, 5, 2, 2, 5, 3};
        int d = 50;

        System.out.println(countPartitions(arr,d));
    }

    /*
     * COUNT PARTITIONS WITH GIVEN DIFFERENCE
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given an array and a difference 'd', count the number of ways to partition
     * the array into TWO subsets (S1 and S2) such that: S1 - S2 = d
     *
     * Example: arr = [5, 2, 6, 4], d = 3
     *   - Partition: S1 = {6, 4} (sum=10), S2 = {5, 2} (sum=7)
     *   - Difference: 10 - 7 = 3 ✓
     *   - Count all such valid partitions
     *
     * BRILLIANT MATHEMATICAL TRANSFORMATION:
     * --------------------------------------
     * Given constraints:
     *   1. S1 - S2 = d         (required difference)
     *   2. S1 + S2 = totalSum  (all elements must be in one subset)
     *
     * Solving these two equations:
     *   From (1): S1 = d + S2
     *   Substitute in (2): (d + S2) + S2 = totalSum
     *                      d + 2*S2 = totalSum
     *                      S2 = (totalSum - d) / 2
     *
     *   Similarly: S1 = (totalSum + d) / 2
     *
     * KEY INSIGHT:
     * ------------
     * The problem reduces to:
     * "Count subsets with sum = (totalSum - d) / 2"
     *
     * This is the SAME as the Perfect Sum problem we solved earlier!
     *
     * VALIDITY CHECKS (IMPORTANT):
     * ----------------------------
     * Before solving, we must verify the problem has valid solutions:
     *
     * 1. CHECK: totalSum - d >= 0
     *    Why? Because S2 = (totalSum - d) / 2 must be non-negative
     *    If totalSum < d, then S2 would be negative → IMPOSSIBLE
     *
     * 2. CHECK: (totalSum - d) must be EVEN
     *    Why? Because S2 = (totalSum - d) / 2 must be an integer
     *    If (totalSum - d) is odd, we can't split into integer sums → IMPOSSIBLE
     *
     * If either check fails → return 0 (no valid partitions exist)
     *
     * APPROACH:
     * ---------
     * 1. CALCULATE TOTAL SUM:
     *    - Sum all array elements
     *
     * 2. DERIVE TARGET SUM:
     *    - target = (totalSum - d) / 2
     *    - This is the sum that S2 must achieve
     *
     * 3. VALIDITY CHECKS:
     *    - If totalSum - d < 0 → return 0 (impossible)
     *    - If (totalSum - d) is odd → return 0 (can't split evenly)
     *
     * 4. COUNT SUBSETS:
     *    - Use the same countSubset logic as Perfect Sum problem
     *    - Count all subsets with sum = target
     *    - Each such subset represents a valid partition!
     *
     * EXAMPLE WALKTHROUGH:
     * --------------------
     * arr = [1, 1, 2, 3], d = 1
     *
     * Step 1: totalSum = 1 + 1 + 2 + 3 = 7
     *
     * Step 2: target = (7 - 1) / 2 = 3
     *
     * Step 3: Validity checks
     *   - totalSum - d = 7 - 1 = 6 >= 0 ✓
     *   - (7 - 1) % 2 = 6 % 2 = 0 (even) ✓
     *
     * Step 4: Find subsets with sum = 3
     *   - {1, 2} → S1 = {1, 3} (sum=4), S2 = {1, 2} (sum=3), diff = 4-3 = 1 ✓
     *   - {3} → S1 = {1, 1, 2} (sum=4), S2 = {3} (sum=3), diff = 4-3 = 1 ✓
     *   - Count = 2
     *
     * WHY THIS WORKS:
     * ---------------
     * If S2 has sum = (totalSum - d) / 2, then:
     *   S1 = totalSum - S2
     *   S1 = totalSum - (totalSum - d) / 2
     *   S1 = (2*totalSum - totalSum + d) / 2
     *   S1 = (totalSum + d) / 2
     *
     * Now: S1 - S2 = (totalSum + d) / 2 - (totalSum - d) / 2
     *              = (totalSum + d - totalSum + d) / 2
     *              = 2d / 2
     *              = d ✓
     *
     * So finding S2 automatically ensures S1 - S2 = d!
     *
     * EDGE CASES:
     * -----------
     * 1. arr = [0, 0, 0], d = 0
     *    - totalSum = 0, target = 0
     *    - Many subsets sum to 0 (each 0 can be included or excluded)
     *    - Count = 2^3 = 8 ways
     *
     * 2. arr = [1, 2, 3], d = 10
     *    - totalSum = 6, target = (6-10)/2 = -2 (negative!)
     *    - Return 0 immediately
     *
     * 3. arr = [1, 1, 1], d = 2
     *    - totalSum = 3, (3-2) = 1 (odd!)
     *    - Return 0 immediately
     *
     * TIME COMPLEXITY: O(n * target) where target = (totalSum - d) / 2
     *   - Same as Perfect Sum problem
     *
     * SPACE COMPLEXITY: O(n * target) + O(n)
     *   - DP table + recursion stack
     *
     * KEY INSIGHT:
     * ------------
     * This problem beautifully demonstrates how algebraic manipulation can
     * transform a complex constraint (S1 - S2 = d) into a simpler problem
     * (count subsets with sum = target) that we already know how to solve!
     *
     * The transformation: Two-subset difference → Single-subset sum counting
     */

    public static int countPartitions(int[] arr, int d) {
        int totalSum = 0;
        int n = arr.length;
        for(int num : arr) totalSum += num;
        int sum = (totalSum - d) / 2;
        if(totalSum - d < 0 || (totalSum - d) % 2 == 1) return 0;
        int[][] dp = new int[n][sum + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return countSubset(n - 1, sum, arr, dp);
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
}

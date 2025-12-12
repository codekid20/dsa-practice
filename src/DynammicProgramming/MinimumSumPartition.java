package DynammicProgramming;

import java.util.Arrays;

public class MinimumSumPartition {
    public static void main(String[] args) {
        int[] arr = {9, 4, 2, 8};

        System.out.println(minDifference1(arr));
    }

    /*
     * MINIMUM SUBSET SUM DIFFERENCE (PARTITION WITH MINIMUM DIFFERENCE)
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Partition an array into TWO subsets such that the absolute difference between
     * their sums is MINIMIZED.
     *
     * Example: [1, 6, 11, 5]
     *   - Partition 1: [1, 5, 6] (sum=12) and [11] (sum=11) → Difference = 1
     *   - Partition 2: [1, 6] (sum=7) and [11, 5] (sum=16) → Difference = 9
     *   Best partition has minimum difference = 1
     *
     * KEY INTUITION:
     * --------------
     * Let's say we partition array into two subsets S1 and S2:
     *   - S1 + S2 = totalSum (all elements must be in one of the subsets)
     *   - We want to minimize: |S1 - S2|
     *
     * Mathematical insight:
     *   - S2 = totalSum - S1
     *   - Difference = |S1 - S2| = |S1 - (totalSum - S1)| = |2*S1 - totalSum|
     *   - To minimize this, we want S1 to be as close to totalSum/2 as possible
     *
     * Strategy:
     *   - Find ALL possible subset sums we can achieve (using subset sum DP)
     *   - For each possible S1, calculate difference: (totalSum - S1) - S1
     *   - Return the minimum difference
     *
     * APPROACH:
     * ---------
     * 1. CALCULATE TOTAL SUM:
     *    - Sum all array elements
     *
     * 2. BUILD SUBSET SUM DP TABLE:
     *    - dp[i][j] = Can we make sum 'j' using elements from index 0 to i?
     *    - Use tabulation (bottom-up) to fill the table
     *    - This identifies ALL possible subset sums
     *
     * 3. FIND MINIMUM DIFFERENCE:
     *    - Check last row: dp[n-1][i] for i from 0 to totalSum/2
     *    - Why only up to totalSum/2?
     *      Because if S1 > totalSum/2, then S2 < totalSum/2
     *      We'd be counting the same partition twice!
     *
     *    - For each achievable sum S1 (where dp[n-1][S1] = true):
     *      → S2 = totalSum - S1
     *      → Difference = S2 - S1 = (totalSum - S1) - S1 = totalSum - 2*S1
     *      → Track minimum difference
     *
     * EXAMPLE WALKTHROUGH:
     * --------------------
     * Array: [1, 2, 7]
     * Total Sum = 10
     *
     * Possible subset sums: 0, 1, 2, 3, 7, 8, 9, 10
     * Check S1 from 0 to 10/2 = 5:
     *   - S1=0: S2=10, diff = 10-0 = 10
     *   - S1=1: S2=9,  diff = 9-1 = 8
     *   - S1=2: S2=8,  diff = 8-2 = 6
     *   - S1=3: S2=7,  diff = 7-3 = 4  ← Best partition: [1,2] vs [7]
     *   - S1=4: Not achievable
     *   - S1=5: Not achievable
     *
     * Minimum difference = 4
     *
     * WHY CHECK ONLY UP TO totalSum/2?
     * --------------------------------
     * If S1 = 7 (more than half):
     *   - S2 = 3, difference = 7-3 = 4
     * This is the SAME partition as S1=3, S2=7!
     * To avoid redundancy, we only check S1 <= totalSum/2
     *
     * FORMULA DERIVATION:
     * -------------------
     * Given: S1 + S2 = totalSum
     * Difference = |S1 - S2|
     *
     * Since S2 = totalSum - S1:
     * Difference = |S1 - (totalSum - S1)| = |2*S1 - totalSum|
     *
     * When S1 <= totalSum/2:
     *   2*S1 <= totalSum, so 2*S1 - totalSum <= 0
     *   Difference = totalSum - 2*S1 = (totalSum - S1) - S1 = S2 - S1
     *
     * TIME COMPLEXITY: O(n * totalSum)
     *   - DP table filling: O(n * totalSum)
     *   - Finding minimum: O(totalSum/2)
     *
     * SPACE COMPLEXITY: O(n * totalSum)
     *   - 2D DP table
     *
     * KEY INSIGHT:
     * ------------
     * This problem combines subset sum DP with a clever observation:
     * To minimize |S1 - S2|, we need S1 as close to totalSum/2 as possible.
     * The DP tells us ALL achievable sums, then we pick the best one!
     */

    public static int minDifference(int[] arr) {

        int n = arr.length;
        int totalSum = 0;
        for(int num : arr){
            totalSum += num;
        }

        int k = totalSum;

        Boolean[][] dp = new Boolean[n][k + 1];
        for(Boolean[] row : dp){
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

        int min = (int)1e9;

        for (int i = 0; i <= totalSum / 2; i++) {
            if(dp[n-1][i]){
                min = Math.min(min, (totalSum - i) - i);
            }
        }

        return min;
    }

    // Optimizing Space
    // Time: O(N*Target);
    // Space: O(Target);
    public static int minDifference1(int[] arr) {

        int n = arr.length;
        int totalSum = 0;
        for(int num : arr){
            totalSum += num;
        }

        int k = totalSum;

        Boolean[] dp = new Boolean[k + 1];

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

        int min = (int)1e9;

        for (int i = 0; i <= totalSum / 2; i++) {
            if(dp[i]){
                min = Math.min(min, (totalSum - i) - i);
            }
        }

        return min;
    }
}

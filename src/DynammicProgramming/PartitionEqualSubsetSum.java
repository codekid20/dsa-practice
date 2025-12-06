package DynammicProgramming;

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        System.out.println(canPartition2(nums));
    }

    /*
     * KEY INTUITION:
     * --------------
     * If we can partition array into two equal subsets, then:
     *   - Each subset must have sum = totalSum / 2
     *   - Total sum must be EVEN (otherwise impossible to split equally)
     *
     * So the problem reduces to:
     * "Can we find a subset with sum = totalSum / 2?"
     *
     * If we can find one subset with sum/2, the remaining elements automatically
     * form the other subset with sum/2!
     *
     * APPROACH:
     * ---------
     * 1. CALCULATE TOTAL SUM:
     *    - Add all elements in the array
     *
     * 2. EARLY TERMINATION CHECK:
     *    - If sum is ODD → Return FALSE immediately
     *    - (Odd sum can NEVER be split into two equal parts)
     *
     * 3. SUBSET SUM PROBLEM:
     *    - Target = sum / 2
     *    - Find if any subset sums to this target
     *    - Use memoization to avoid recomputing subproblems
     *
     * 4. RECURSIVE LOGIC (partition function):
     *    - Same as standard subset sum problem
     *
     *    BASE CASES:
     *    - If target == 0: Found valid partition! Return TRUE
     *    - If index == 0: Check if first element equals target
     *
     *    RECURSIVE CHOICES:
     *    a) NOT TAKE: Exclude current element, check remaining
     *    b) TAKE: Include current element (only if it doesn't exceed target)
     *             Reduce target by nums[index] and check remaining
     *
     *    Return TRUE if EITHER choice leads to solution
     *
     * EXAMPLE WALKTHROUGH:
     * --------------------
     * Array: [1, 5, 11, 5]
     * Total Sum = 22
     * Target = 22/2 = 11
     *
     * We need to find if subset with sum=11 exists:
     * Possible subsets: [11] ✓ or [1,5,5] ✓
     * Remaining: [1,5,5] or [11]
     * Both have sum=11, so return TRUE
     *
     * DP STATE:
     * ---------
     * dp[index][target] = Can we make 'target' sum using elements from index 0 to 'index'?
     *
     * TIME COMPLEXITY: O(n * sum/2) = O(n * sum)
     *   - n elements, sum/2 possible targets
     *   - Each state computed once due to memoization
     *
     * SPACE COMPLEXITY: O(n * sum/2) + O(n)
     *   - DP table: O(n * sum/2)
     *   - Recursion stack: O(n)
     *
     * KEY INSIGHT:
     * ------------
     * This problem elegantly transforms "partition into equal subsets" into
     * "find subset with sum = totalSum/2", which is a classic subset sum problem.
     * The beauty is that finding ONE subset automatically defines the OTHER!
     */

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        if(sum % 2 != 0) return false;
        Boolean[][] dp = new Boolean[nums.length][sum/2 + 1];
        return partition(nums.length - 1, sum / 2, nums,dp);
    }

    public static boolean partition(int index, int target, int[] nums, Boolean[][] dp){

        if(target == 0) return true;
        if(index == 0) return nums[0] == target;

        if(dp[index][target] != null) return dp[index][target];
        boolean notTake = partition(index - 1, target, nums, dp);
        boolean take = false;
        if(nums[index] <= target){
            take = partition(index - 1, target - nums[index], nums, dp);
        }

        return dp[index][target] = take || notTake;
    }


    // Tabulation

    public static boolean canPartition1(int[] nums) {

        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        if(sum % 2 != 0) return false;
        int k = sum/2;
        Boolean[][] dp = new Boolean[nums.length][k + 1];
        for(Boolean[] row : dp){
            Arrays.fill(row, false);
        }
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }

        if(nums[0] <= k){
            dp[0][nums[0]] = true;
        }

        for (int index = 1; index < nums.length; index++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[index - 1][target];
                boolean take = false;
                if(nums[index] <= target){
                    take = dp[index - 1][target - nums[index]];
                }

                dp[index][target] = take || notTake;
            }
        }


        return dp[nums.length - 1][k];
    }

    // Space Optimization
    public static boolean canPartition2(int[] nums) {

        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        if(sum % 2 != 0) return false;

        int k = sum/2;

        Boolean[] dp = new Boolean[k + 1];

        Arrays.fill(dp, false);

        dp[0] = true;

//        if(nums[0] <= k){
//            dp[nums[0]] = true;
//        }

        for (int index = 1; index < nums.length; index++) {
            Boolean[] curr = new Boolean[k + 1];
            Arrays.fill(curr, false);
            curr[0] = true;
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[target];
                boolean take = false;
                if(nums[index] <= target){
                    take = dp[target - nums[index]];
                }

                curr[target] = take || notTake;
            }

            dp = curr;
        }


        return dp[k];
    }
}

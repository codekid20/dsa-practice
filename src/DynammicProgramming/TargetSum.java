package DynammicProgramming;

import java.util.Arrays;

/*
Problem: Target Sum (Assign + or - signs to nums to reach target sum)

Approach:
- Convert to Subset Sum problem using formula:
    S1 - S2 = target
    S1 + S2 = totalSum
    => S1 = (totalSum - target) / 2
- Count subsets with sum = S1 using recursion + memoization.

Edge Cases:
- If (totalSum - target) is negative or odd => return 0
- Handle zeroes specially at index 0

Time: O(n * sum)
Space: O(n * sum)
*/

public class TargetSum {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;

        System.out.println(findTargetSumWays(nums, target));
    }

    public static int findTargetSumWays(int[] nums, int target) {

        return countPartitions(nums, target);
    }

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

package DynammicProgramming;

import java.util.Arrays;

public class CountSubsetsWithSumK {
    public static void main(String[] args) {
        int[] arr =  {5, 2, 6, 4};
        int target = 7;

        System.out.println(perfectSum(arr,target));
    }

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

//        for (int i = 0; i < n; i++) {
//            dp[i][0] = 1;
//        }
//
//        if(nums[0] <= target){
//            dp[0][nums[0]] = 1;
//        }

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

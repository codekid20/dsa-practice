package DynammicProgramming;

import java.util.Arrays;

public class PartitionsWithGivenDifference {
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 1, 1, 5, 3, 3, 1, 5, 2, 2, 5, 3};
        int d = 50;

        System.out.println(countPartitions(arr,d));
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

package DynammicProgramming;

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        System.out.println(canPartition2(nums));
    }

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

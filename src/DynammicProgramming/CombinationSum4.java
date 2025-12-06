package DynammicProgramming;

import java.util.Arrays;

public class CombinationSum4 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int target = 4;

        System.out.println(combinationSum4(nums, target));
    }

    public static int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        return count(nums, target, dp);
    }

    private static int count(int[] nums, int target, int[] dp) {

        if(target == 0) return 1;
        if(target < 0) return 0;

        if(dp[target] != -1) return dp[target];
        int ways = 0;

        for(int num : nums) {
            ways += count(nums, target - num, dp);
        }

        dp[target] = ways;

        return ways;
    }

//    public static int combinationSum4tabulation(int[] nums, int target) {
//
//
//    }
}

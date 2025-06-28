package DynammicProgramming;

import java.util.Arrays;

public class HouseRobber {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return helper(nums, n - 1, dp);
    }

    private static int helper(int[] nums, int n, int[] dp) {

        if(n == 0) return nums[0];
        if (n < 0) return 0;

        if(dp[n] != -1) return dp[n];

        int take = nums[n] + helper(nums,n - 2, dp);
        int nottake = helper(nums,n-1, dp);

        return dp[n] = Math.max(take, nottake);

    }


    public static int rob1(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            int take = nums[i];
            if(i > 1) take += dp[i - 2];

            int nottake = dp[i - 1];

            dp[i] = Math.max(take, nottake);
        }

        return dp[n - 1];
    }

    public static int rob2(int[] nums) {

        int n = nums.length;

        int prev = nums[0];
        int prev2 = 0;

        for (int i = 1; i < n; i++) {
            int take = nums[i];
            if(i > 1) take += prev2;

            int nottake = prev;

            int curr = Math.max(take, nottake);
            prev2 = prev;
            prev = curr;
        }

        return prev;
    }
}

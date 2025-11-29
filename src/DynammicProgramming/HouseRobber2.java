package DynammicProgramming;

import java.util.Arrays;

public class HouseRobber2 {
    public static void main(String[] args) {
        int[] nums = {2,3,2};
        System.out.println(rob1(nums));
    }

    // First and last cannot be together. So answer is Max of excluding either one of them.

    public static int rob(int[] nums) {

        int n = nums.length;
        if(n == 1) return nums[0];

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, -1);
        Arrays.fill(dp2, -1);

        return Math.max(helper(nums, 0,n - 2, dp1), helper(nums,1, n -1, dp2));
    }

    private static int helper(int[] nums, int start, int end, int[] dp) {


        if(end < start) return 0;

        if(dp[end] != -1) return dp[end];

        int take = nums[end] + helper(nums, start,end - 2, dp);
        int nottake = helper(nums,start, end - 1, dp);

        return dp[end] = Math.max(take, nottake);
    }

    // Tabulation:
    public static int rob1(int[] nums) {

        int n = nums.length;
        if(n == 1) return nums[0];
        int[] nums1 = new int[n - 1];
        int[] nums2 = new int[n - 1];

        for (int i = 0; i < n; i++) {
            if(i != 0){
                nums1[i-1] = nums[i];
            }

            if(i != n - 1){
                nums2[i] = nums[i];
            }
        }

//        return Math.max(tabulation(nums1), tabulation(nums2));
        return Math.max(spaceOptimized(nums1), spaceOptimized(nums2));
    }

    private static int spaceOptimized(int[] nums) {

        int prev = nums[0];
        int prev2 = 0;

        for (int i = 1; i < nums.length; i++) {
            int pick = nums[i];
            if(i > 1) pick += prev2;
            int skip = prev;

            int curr = Math.max(pick, skip);
            prev2 = prev;
            prev = curr;
        }

        return prev;
    }

    private static int tabulation(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int pick = nums[i];
            if(i > 1) {
                pick += dp[i - 2];
            }

            int skip = dp[i - 1];

            dp[i] = Math.max(pick, skip);
        }

        return dp[nums.length-1];
    }
}

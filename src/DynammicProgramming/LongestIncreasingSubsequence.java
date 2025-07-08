package DynammicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {

        int[] nums = {1};
        lengthOfLIS5(nums);
    }

    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return longest(0, -1, nums, dp);
    }

    private static int longest(int idx, int prev, int[] nums, int[][] dp) {

        if(idx == nums.length) return 0;

        if(dp[idx][prev + 1] != -1) return dp[idx][prev+1];

        int len;
        len = longest(idx + 1, prev, nums, dp);
        if(prev == -1 || nums[idx] > nums[prev]){
            len = Math.max(len, 1 + longest(idx + 1, idx, nums, dp));
        }
        return dp[idx][prev+1] = len;
    }

    // Tabulation
    public static int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n+1][n+1];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int prev = idx - 1; prev >= -1; prev--) {
                int len;
                len = dp[idx + 1][prev + 1];
                if(prev == -1 || nums[idx] > nums[prev]){
                    len = Math.max(len, 1 + dp[idx + 1][idx+1]);
                }

                dp[idx][prev+1] = len;
            }
        }

        return dp[0][0];
    }


    // Space Optimization
    public static int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];

        for (int idx = n - 1; idx >= 0; idx--) {
            int[] curr = new int[n+1];
            for (int prev = idx - 1; prev >= -1; prev--) {
                int len;
                len = dp[prev + 1];
                if(prev == -1 || nums[idx] > nums[prev]){
                    len = Math.max(len, 1 + dp[idx+1]);
                }

                curr[prev+1] = len;
            }

            dp = curr;
        }

        return dp[0];
    }


    // Tabulation Optimization
    public static int lengthOfLIS4(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int len = 1;
        for (int idx = 0; idx < n; idx++) {
            for (int prev = 0; prev < idx; prev++) {
                if(nums[prev] < nums[idx]){
                    dp[idx] = Math.max(1 + dp[prev], dp[idx]);
                }
            }
            len = Math.max(len, dp[idx]);
        }
        return len;
    }

    // Printing LIS
    public static void lengthOfLIS5(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);
        int len = 1;
        int lastIndex = 0;
        for (int idx = 0; idx < n; idx++) {
            hash[idx] = idx;
            for (int prev = 0; prev < idx; prev++) {
                if(nums[prev] < nums[idx] && dp[idx] < 1 + dp[prev]){
                    dp[idx] = 1 + dp[prev];
                    hash[idx] = prev;
                }
            }
            if(dp[idx] > len){
                len = dp[idx];
                lastIndex = idx;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(nums[lastIndex]);

        while (hash[lastIndex] != lastIndex){
            lastIndex = hash[lastIndex];
            ans.add(0,nums[lastIndex]);
        }

        System.out.println(ans);
    }
}

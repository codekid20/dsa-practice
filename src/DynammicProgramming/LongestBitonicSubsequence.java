package DynammicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestBitonicSubsequence {
    public static void main(String[] args) {

        int n = 5;
        int[] nums = {1, 2, 5, 3, 2};

        System.out.println(LongestBitonicSequence(n, nums));
    }

    public static int LongestBitonicSequence(int n, int[] nums) {
        int[] dp = new int[n];
        int[] dp1 = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(dp1, 1);
        int maxi = 0;
        for (int idx = 0; idx < n; idx++) {
            for (int prev = 0; prev < idx; prev++) {
                if(nums[prev] < nums[idx] && dp[idx] < 1 + dp[prev]){
                    dp[idx] = 1 + dp[prev];
                }
            }
        }
        for (int idx = n-1; idx >= 0; idx--) {
            for (int prev = n-1; prev > idx; prev--) {
                if(nums[prev] < nums[idx] && dp1[idx] < 1 + dp1[prev]){
                    dp1[idx] = 1 + dp1[prev];
                }
            }

            maxi = Math.max(maxi, dp[idx] + dp1[idx] - 1);
        }

        // Case 2: If only strinctly increasing and decresing are not allowed
//        int maxi = 0;
//        for (int i = 0; i < n; i++) {
//            maxi = Math.max(maxi, dp[i] + dp1[i] - 1);
//        }

        return maxi;

    }
}

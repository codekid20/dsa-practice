package DynammicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class NumberOfLongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = {2,2,2,2,2};

        System.out.println(findNumberOfLIS(nums));
    }

    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(cnt, 1);
        int len = 1;
        int ans = 0;
        for (int idx = 0; idx < n; idx++) {
            for (int prev = 0; prev < idx; prev++) {
                if(nums[prev] < nums[idx] && dp[idx] < 1 + dp[prev]){
                    dp[idx] = 1 + dp[prev];
                    cnt[idx] = cnt[prev];
                } else if (nums[prev] < nums[idx] && dp[idx] == 1 + dp[prev]) {
                    cnt[idx] += cnt[prev];
                }
            }
            if(dp[idx] > len){
                len = dp[idx];
            }
        }

        for (int i = 0; i < n; i++) {
            if(dp[i] == len) ans += cnt[i];
        }

        return ans;
    }
}

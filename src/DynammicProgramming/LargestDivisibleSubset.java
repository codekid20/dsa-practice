package DynammicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    public static void main(String[] args) {
        int[] nums = {1};

        System.out.println(largestDivisibleSubset(nums));
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);
        int len = 1;
        int lastIndex = 0;
        Arrays.sort(nums);
        for (int idx = 0; idx < n; idx++) {
            hash[idx] = idx;
            for (int prev = 0; prev < idx; prev++) {
                if(nums[idx] % nums[prev] == 0 && dp[idx] < 1 + dp[prev]){
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

        return ans;
    }
}

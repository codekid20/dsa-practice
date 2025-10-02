package GreedyAlgorithm;

import java.util.Arrays;

public class JumpGame2 {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};

        System.out.println(jump1(nums));
    }

    public static int jump(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(nums, 0, 0, dp);
    }

    private static int solve(int[] nums, int idx, int jumps, int[][] dp) {

        if(idx >= nums.length - 1) return jumps;

        if(dp[idx][jumps] != -1) return dp[idx][jumps];
        int mini = Integer.MAX_VALUE;

        for(int i = 1; i <= nums[idx]; i++){
            mini = Math.min(mini, solve(nums, idx + i, jumps + 1, dp));
        }

        return dp[idx][jumps] = mini;
    }


    // Approach 2:

    public static int jump1(int[] nums) {

        int jumps = 0;
        int l = 0; // left boundary of current jump range
        int r = 0; // right boundary of current jump range

        while (r < nums.length - 1) {
            int farthest = 0;

            // explore all indices in the current range [l, r]
            for (int idx = l; idx <= r; idx++) {
                farthest = Math.max(idx + nums[idx], farthest);
            }

            jumps++; // we used one jump to reach this range
            l = r + 1; // next range starts just after current r
            r = farthest; // extend r to the farthest we can go
        }

        return jumps;
    }


    // Approach 3:

    public static int jump2(int[] nums) {
        
        int jumps = 0;
        int end = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {

            farthest = Math.max(i + nums[i], farthest);

            if(i == end){
                jumps++;
                end = farthest;
            }
        }

        return jumps;
    }
}

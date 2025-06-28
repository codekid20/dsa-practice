package DynammicProgramming;

import java.util.Arrays;
import java.util.Map;

public class FrogJump {
    public static void main(String[] args) {
        int[] heights = {30, 20, 50, 10, 40};
        System.out.println(minCost2(heights));
    }

    // Memoization
    // Time: O(N)
    // Space: O(N) + 0(N)

    public static int minCost(int[] height) {

        int n = height.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return jumps(n-1, height, dp);

    }

    private static int jumps(int n, int[] height, int[] dp) {

        if(n == 0) return 0;

        if(dp[n] != -1){
            return dp[n];
        }
        int left = jumps(n - 1, height, dp) + Math.abs(height[n] - height[n - 1]);
        int right = Integer.MAX_VALUE;
        if(n > 1) {
            right = jumps(n - 2, height, dp) + Math.abs(height[n] - height[n - 2]);
        }

        return dp[n] = Math.min(left, right);
    }


    // Tabulation
    // Time: O(N)
    // Space: O(N)
    public static int minCost1(int[] height) {

        int n = height.length;

        int[] dp = new int[n];

        dp[0] = 0;

        for (int i = 1; i < n; i++) {

            int left = dp[i - 1] + Math.abs(height[i] - height[i - 1]);
            int right = Integer.MAX_VALUE;

            if(i > 1) {
                right = dp[i - 2] + Math.abs(height[i] - height[i - 2]);
            }

            dp[i] = Math.min(left, right);
        }

        return dp[n-1];

    }

    // Space Optimization
    // Time: O(N)
    // Space: O(1)
    public static int minCost2(int[] height) {

        int n = height.length;

        int prev = 0;
        int prev2 = 0;

        for (int i = 1; i < n; i++) {

            int left = prev + Math.abs(height[i] - height[i - 1]);
            int right = Integer.MAX_VALUE;

            if(i > 1) {
                right = prev2 + Math.abs(height[i] - height[i - 2]);
            }

            int curr = Math.min(left, right);
            prev2 = prev;
            prev = curr;
        }

        return prev;

    }
}

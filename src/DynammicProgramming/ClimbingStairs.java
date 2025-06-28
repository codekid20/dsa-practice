package DynammicProgramming;

import java.util.Arrays;

public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 40;
        System.out.println(climbStairs(n));
    }

    public static int climbStairs(int n) {

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return climbStair(n, dp);
    }

    private static int climbStair(int n, int[] dp) {

        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;

        if(dp[n] != -1){
            return dp[n];
        }

        return dp[n] = climbStair(n - 1, dp) + climbStair(n - 2, dp);
    }

    public static int climbStairs1(int n){

        int[] dp = new int[n + 1];
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;


        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static int climbStairs2(int n){
        int prev3 = 0;
        int prev2 = 1;
        int prev1 = 2;

        if(n == 0) return prev3;
        if(n == 1) return prev2;
        if(n == 2) return prev1;

        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}

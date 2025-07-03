package DynammicProgramming;

import java.util.Arrays;

public class DistinctSubsequences {
    public static void main(String[] args) {
        String s = "babgbag", t = "bag";

        System.out.println(numDistinct1(s,t));
    }


    public static int numDistinct(String s, String t) {

        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];
        for (int[] row : dp){
            Arrays.fill(row, -1);
        }

        return helper(n,m,s,t,dp);
    }

    private static int helper(int i, int j, String s, String t, int[][] dp) {
        if(j == 0) return 1;
        if(i == 0) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        if(s.charAt(i-1) == t.charAt(j-1)){
            return dp[i][j] = helper(i-1,j-1, s, t, dp) + helper(i-1, j, s, t, dp);
        } else {
            return dp[i][j] = helper(i-1, j, s, t, dp);
        }
    }


    // Tabulation

    public static int numDistinct1(String s, String t) {

        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][m];
    }
}

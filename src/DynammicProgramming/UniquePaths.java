package DynammicProgramming;

import java.util.Arrays;

public class UniquePaths {
    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println(uniquePaths2(m,n));
    }

    public static int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return totalPaths(m - 1, n - 1, dp);
    }

    private static int totalPaths(int m, int n, int[][] dp) {
        if(m == 0 && n == 0){
            return 1;
        }

        if(m < 0 || n < 0){
            return 0;
        }

        if(dp[m][n] != -1) return dp[m][n];
        int up = totalPaths(m-1, n, dp);
        int left = totalPaths(m, n - 1, dp);

        return dp[m][n] = up + left;
    }

    // Tabulation
    public static int uniquePaths1(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0) dp[i][j] = 1;
                else {
                    int up = 0;
                    int left = 0;

                    if(i > 0) up = dp[i-1][j];
                    if(j > 0) left = dp[i][j - 1];

                    dp[i][j] = up + left;
                }
            }
        }

        return dp[m - 1][n - 1];
    }


    public static int uniquePaths2(int m, int n) {

        int[] prev = new int[n];

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0) curr[j] = 1;
                else{
                    int up = 0;
                    int left = 0;

                    if(i>0) up = prev[j];
                    if(j>0) left =  curr[j-1];
                    curr[j] = up + left;
                }
            }

            prev = curr;
        }

        return prev[n-1];
    }
}

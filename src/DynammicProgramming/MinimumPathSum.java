package DynammicProgramming;

import java.util.Arrays;

public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = {{1,2,3},{4,5,6}};
        System.out.println(minPathSum2(grid));
    }
    public static int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return minSum(grid, m-1, n-1, dp);
    }

    private static int minSum(int[][] grid, int m, int n, int[][] dp) {

        if(m == 0 && n == 0) return grid[0][0];
        if(m < 0 || n < 0) return 100000;

        if(dp[m][n] != -1) return dp[m][n];
        int up = grid[m][n] + minSum(grid, m - 1, n, dp);
        int left = grid[m][n] + minSum(grid, m, n - 1,dp);

        return dp[m][n] = Math.min(up, left);
    }

    public static int minPathSum1(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i == 0 && j == 0) dp[i][j] = grid[i][j];
                else {
                    int up = 1000000;
                    int left = 1000000;
                    if(i > 0) up = grid[i][j] + dp[i-1][j];
                    if(j > 0) left = grid[i][j] + dp[i][j-1];

                    dp[i][j] = Math.min(up, left);
                }
            }
        }

        return dp[n-1][m-1];
    }


    public static int minPathSum2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dp = new int[m];

        for (int i = 0; i < n; i++) {
            int[] temp = new int[m];
            for (int j = 0; j < m; j++) {
                if(i == 0 && j == 0) temp[j] = grid[i][j];
                else {
                    int up = grid[i][j];
                    if(i > 0) up += dp[j];
                    else up += 1e9;
                    int left = grid[i][j];
                    if(j > 0) left += temp[j-1];
                    else left += 1e9;

                    temp[j] = Math.min(up, left);
                }
            }

            dp = temp;
        }

        return dp[m-1];
    }
}

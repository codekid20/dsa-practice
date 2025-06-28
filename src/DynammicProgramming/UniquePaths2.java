package DynammicProgramming;

import java.util.Arrays;

public class UniquePaths2 {
    public static void main(String[] args) {
        int[][] grid = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObstacles2(grid));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1) return 0;
        int[][] dp = new int[m][n];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return totalPaths(obstacleGrid,m - 1, n - 1, dp);
    }

    private static int totalPaths(int[][] mat, int m, int n, int[][] dp) {
        if(m == 0 && n == 0){
            return 1;
        }

        if(m < 0 || n < 0){
            return 0;
        }

        if(mat[m][n] == 1) {
            return 0;
        }

        if(dp[m][n] != -1) return dp[m][n];
        int up = totalPaths(mat,m-1, n, dp);
        int left = totalPaths(mat,m, n - 1, dp);

        return dp[m][n] = up + left;
    }


    public static int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1) return 0;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0) dp[i][j] = 1;
                else if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
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

    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1) return 0;
        int[] prev = new int[n];

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0) curr[j] = 1;
                else if (obstacleGrid[i][j] == 1) {
                    curr[j] = 0;
                } else{
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

package DynammicProgramming;

import java.util.Arrays;

public class MaximumPathSumInMatrix {
    public static void main(String[] args) {
        int[][] mat = {{3, 6, 1}, {2, 3, 4}, {5, 5, 1}};
        System.out.println(maximumPath2(mat));
    }

    public static int maximumPath(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        int max = (int)-1e8;
        int[][] dp = new int[n][m];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            max = Math.max(max, maxPath(mat, n - 1, i, dp));
        }
        return max;
    }

    private static int maxPath(int[][] mat, int i, int j, int[][] dp) {
        int n = mat.length;
        int m = mat[0].length;

        if(j < 0 || j >= m) return (int)-1e8;
        if(i==0) return mat[0][j];

        if(dp[i][j] != -1) return dp[i][j];

        int up = mat[i][j] + maxPath(mat, i - 1, j, dp);
        int ld = mat[i][j] + maxPath(mat, i - 1, j - 1, dp);
        int rd = mat[i][j] + maxPath(mat, i - 1, j + 1, dp);

        return dp[i][j] = Math.max(up, Math.max(ld, rd));
    }

    // Tabulation
    public static int maximumPath1(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        int[][] dp = new int[n][m];

        for (int j = 0; j < m; j++) {
            dp[0][j] = mat[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int up = mat[i][j] + dp[i - 1][j];
                int ld = mat[i][j];
                if(j > 0) ld += dp[i - 1][j -1];
                else ld += (int)-1e9;
                int rd = mat[i][j];
                if(j + 1 < m) rd += dp[i - 1][j + 1];
                else rd += (int)-1e9;

                dp[i][j] = Math.max(up, Math.max(ld, rd));
            }
        }

        int max = dp[n - 1][0];
        for (int i = 0; i < m; i++) {
            max = Math.max(max, dp[n - 1][i]);
        }

        return max;
    }


    public static int maximumPath2(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        int[] dp = new int[m];

        for (int j = 0; j < m; j++) {
            dp[j] = mat[0][j];
        }

        for (int i = 1; i < n; i++) {
            int[] curr = new int[m];
            for (int j = 0; j < m; j++) {
                int up = mat[i][j] + dp[j];
                int ld = mat[i][j];
                if(j > 0) ld += dp[j -1];
                else ld += (int)-1e9;
                int rd = mat[i][j];
                if(j + 1 < m) rd += dp[j + 1];
                else rd += (int)-1e9;

                curr[j] = Math.max(up, Math.max(ld, rd));
            }

            dp = curr;
        }

        int max = dp[0];
        for (int i = 0; i < m; i++) {
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}

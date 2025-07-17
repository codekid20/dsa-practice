package DynammicProgramming;

import java.util.Arrays;

public class MatrixChainMultiplication {
    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 4};

        System.out.println(matrixMultiplication(arr));
    }

    public static int matrixMultiplication(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int[] row : dp) Arrays.fill(row, -1);
        return minSteps(1, n-1, arr, dp);
    }

    private static int minSteps(int i, int j, int[] arr, int[][] dp) {
        if(i == j) return 0;

        int mini = (int) 1e9;

        if(dp[i][j] != -1) return dp[i][j];

        for (int k = i; k < j; k++) {

            int steps = arr[i - 1] * arr[k] * arr[j] + minSteps(i, k, arr, dp) + minSteps(k + 1, j, arr, dp);

            if(steps < mini) mini = steps;
        }

        return dp[i][j] = mini;
    }

    // Tabulation

    public static int matrixMultiplication1(int[] arr) {

        int n = arr.length;
        int[][] dp = new int[n][n];

        for (int i = 1; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int mini = (int) 1e9;
                for (int k = i; k < j; k++) {
                    int steps = arr[i - 1] * arr[k] * arr[j] + dp[i][k]  + dp[k + 1][j];

                    if(steps < mini) mini = steps;
                }

                dp[i][j] = mini;
            }
        }

        return dp[1][n-1];
    }
}

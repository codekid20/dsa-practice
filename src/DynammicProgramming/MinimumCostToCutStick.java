package DynammicProgramming;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MinimumCostToCutStick {
    public static void main(String[] args) {
        int n = 9;
        int[] cuts = {5,6,1,4,2};

        System.out.println(minCost1(n,cuts));
    }

    public static int minCost(int n, int[] cuts) {

        int c = cuts.length;
        ArrayList<Integer> arr = new ArrayList<>();
        Arrays.sort(cuts);
        for(int num : cuts){
            arr.add(num);
        }

        arr.add(n);
        arr.add(0,0);
        int[][] dp = new int[c+1][c+1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return minCuts(1, c, arr, dp);
    }

    private static int minCuts(int i, int j, ArrayList<Integer> arr, int[][] dp) {

        if(i > j) return 0;

        int mini = Integer.MAX_VALUE;
        if(dp[i][j] != -1) return dp[i][j];
        for (int idx = i; idx <= j; idx++) {
            int cost = arr.get(j + 1) - arr.get(i - 1) + minCuts(i, idx - 1, arr, dp) + minCuts(idx + 1, j, arr, dp);

            mini = Math.min(mini, cost);
        }

        return dp[i][j] = mini;
    }


    // Tabulation

    public static int minCost1(int n, int[] cuts) {

        int c = cuts.length;
        ArrayList<Integer> arr = new ArrayList<>();
        Arrays.sort(cuts);
        for(int num : cuts){
            arr.add(num);
        }
        arr.add(n);
        arr.add(0,0);
        int[][] dp = new int[c+2][c+2];
        for (int i = c; i >= 1; i--) {
            for (int j = i; j <= c; j++) {
                int mini = Integer.MAX_VALUE;
                for (int idx = i; idx <= j; idx++) {
                    int cost = arr.get(j + 1) - arr.get(i - 1) + dp[i][idx - 1] + dp[idx + 1][j];

                    mini = Math.min(mini, cost);
                }

                dp[i][j] = mini;
            }
        }
        return dp[1][c];
    }
}

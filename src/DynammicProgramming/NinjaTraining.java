package DynammicProgramming;

import java.util.Arrays;

public class NinjaTraining {
    public static void main(String[] args) {
        
    }

    // Time : O(N * 4) * 3
    // Space : O(N) + O(N * 4)
    public static int ninjaTraining(int n, int[][] points) {

        int[][] dp = new int[n][4];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return helper(n - 1, 3, points, dp);
    }

    private static int helper(int day, int last, int[][] points, int[][] dp) {

        if(day == 0){
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if(task != last){
                    maxi = Math.max(maxi, points[0][task]);
                }
            }

            return maxi;
        }

        if(dp[day][last] != -1){
            return dp[day][last];
        }

        int maxi = 0;

        for (int task = 0; task < 3; task++) {
            if(task != last){
                int point = points[day][task] + helper(day - 1, task, points, dp);
                maxi = Math.max(maxi, point);
            }
        }

        return dp[day][last] = maxi;
    }

    // Time:
    public static int ninjaTraining1(int n, int[][] points) {

        int[][] dp = new int[n][4];

        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;

                for (int task = 0; task < 3; task++) {
                    if(task != last){
                        int point = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], point);
                    }
                }
            }
        }

        return dp[n - 1][3];
    }

    public static int ninjaTraining2(int n, int[][] points) {

        int[] prev = new int[4];

        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            int[] temp = new int[4];
            for (int last = 0; last < 4; last++) {
                temp[last] = 0;

                for (int task = 0; task < 3; task++) {
                    if(task != last){
                        int point = points[day][task] + prev[task];
                        temp[last] = Math.max(temp[last], point);
                    }
                }
            }
            prev = temp;
        }

        return prev[3];
    }
}

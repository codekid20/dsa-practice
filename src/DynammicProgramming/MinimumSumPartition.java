package DynammicProgramming;

import java.util.Arrays;

public class MinimumSumPartition {
    public static void main(String[] args) {
        int[] arr = {9, 4, 2, 8};

        System.out.println(minDifference1(arr));
    }

    public static int minDifference(int[] arr) {

        int n = arr.length;
        int totalSum = 0;
        for(int num : arr){
            totalSum += num;
        }

        int k = totalSum;

        Boolean[][] dp = new Boolean[n][k + 1];
        for(Boolean[] row : dp){
            Arrays.fill(row, false);
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if(arr[0] <= k){
            dp[0][arr[0]] = true;
        }

        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[index - 1][target];
                boolean take = false;
                if(arr[index] <= target){
                    take = dp[index - 1][target - arr[index]];
                }

                dp[index][target] = take || notTake;
            }
        }

        int min = (int)1e9;

        for (int i = 0; i <= totalSum / 2; i++) {
            if(dp[n-1][i]){
                min = Math.min(min, (totalSum - i) - i);
            }
        }

        return min;
    }

    // Optimizing Space
    // Time: O(N*Target);
    // Space: O(Target);
    public static int minDifference1(int[] arr) {

        int n = arr.length;
        int totalSum = 0;
        for(int num : arr){
            totalSum += num;
        }

        int k = totalSum;

        Boolean[] dp = new Boolean[k + 1];

        Arrays.fill(dp, false);

        dp[0] = true;

        if(arr[0] <= k){
            dp[arr[0]] = true;
        }

        for (int index = 1; index < n; index++) {
            Boolean[] curr = new Boolean[k+1];
            Arrays.fill(curr, false);
            curr[0] = true;
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[target];
                boolean take = false;
                if(arr[index] <= target){
                    take = dp[target - arr[index]];
                }

                curr[target] = take || notTake;
            }
            dp = curr;
        }

        int min = (int)1e9;

        for (int i = 0; i <= totalSum / 2; i++) {
            if(dp[i]){
                min = Math.min(min, (totalSum - i) - i);
            }
        }

        return min;
    }
}

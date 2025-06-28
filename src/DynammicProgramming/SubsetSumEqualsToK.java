package DynammicProgramming;

import java.util.Arrays;

public class SubsetSumEqualsToK {
    public static void main(String[] args) {
        int[] arr = {4,3,2,1};
        int k = 5;

        System.out.println(subsetSumToK(arr.length, k, arr));
    }

    public static boolean subsetSumToK(int n, int k, int[] arr){

        Boolean[][] dp = new Boolean[n][k+1];

        return subsetSum(n-1,k,arr, dp);
    }

    public static boolean subsetSum(int index, int target, int[] arr, Boolean[][] dp){

        if(target == 0) return true;
        if(index == 0) return (arr[0] == target);

        if(dp[index][target] != null) return dp[index][target];
        boolean notTake = subsetSum(index - 1, target, arr, dp);
        boolean take = false;
        if(arr[index] <= target){
            take = subsetSum(index - 1, target - arr[index], arr, dp);
        }

        return dp[index][target] = take || notTake;
    }

    // Tabulation

    public static boolean subsetSumToK1(int n, int k, int[] arr){

        Boolean[][] dp = new Boolean[n][k+1];
        for (Boolean[] row : dp){
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

        return dp[n-1][k];
    }

    // Optimizing Space
    public static boolean subsetSumToK2(int n, int k, int[] arr){

        Boolean[] dp = new Boolean[k+1];

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

        return dp[k];
    }
}

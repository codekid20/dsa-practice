package DynammicProgramming;
import java.util.*;
public class MaximumSumOfNonAdjacentElements {
    public static void main(String[] args) {

    }

    // Memoization
    public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {

        int n = nums.size();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return maxSum(nums, n - 1, dp);
    }

    private static int maxSum(ArrayList<Integer> nums, int n, int[] dp) {

        if(n == 0) return nums.get(0);
        if(n < 0) return 0;

        if(dp[n] != -1){
            return dp[n];
        }

        int pick = nums.get(n) + maxSum(nums, n - 2, dp);
        int notPick = maxSum(nums, n - 1, dp); // 0 + maxSum(nums, n - 1, dp)

        return dp[n] = Math.max(pick, notPick);
    }


    public static int maximumNonAdjacentSum1(ArrayList<Integer> nums) {

        int[] dp = new int[nums.size()];
        dp[0] = nums.get(0);
        int neg = 0;

        for (int i = 1; i < nums.size(); i++) {
            int take = nums.get(i);
            if(i > 1) {
                take += dp[i-2];
            }

            int nontake = dp[i-1];

            dp[i] = Math.max(take, nontake);
        }

        return dp[nums.size() - 1];

    }

    public static int maximumNonAdjacentSum2(ArrayList<Integer> nums) {


        int prev = nums.get(0);
        int prev2 = 0;

        for (int i = 1; i < nums.size(); i++) {
            int take = nums.get(i);
            if(i > 1) {
                take += prev2;
            }

            int nontake = prev;

            int curr = Math.max(take, nontake);
            prev2 = prev;
            prev = curr;
        }

        return prev;

    }
}

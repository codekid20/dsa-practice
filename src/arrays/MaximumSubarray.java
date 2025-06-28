package arrays;

public class MaximumSubarray {
    public static void main(String[] args) {
        int[] arr = {1};
        System.out.println(maxSubArray(arr));
    }

    // KADANE ALGORITHM
    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if(currSum < 0){
                currSum = 0;
            }
            currSum += nums[i];
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }
}

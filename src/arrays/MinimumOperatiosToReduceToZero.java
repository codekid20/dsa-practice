package arrays;

public class MinimumOperatiosToReduceToZero {
    public static void main(String[] args) {

    }

    // This problem can also be seen as finding a longest subarray whose sum is equal to some target.

    // Intuition:
    // 1. I need to find a subarray whose sum is equal to the target.
    // 2. My target is TotalSum - x
    // 3. I will keep maintaining a running sum.
    // 4. The movement my running sum exceeds my target, i will shrink the window size.
    public static int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        int currentSum = 0;
        int maxLen = -1; // Taking maxLen as -1 initially is important instead of 0. Because it could be possible i have to remove all arrat elements to reduce z x to zero.

        for (int l = 0, r = 0; r < nums.length; r++){
            currentSum += nums[r];
            while (l <= r && totalSum - x < currentSum){
                currentSum -= nums[l++];
            }

            if(totalSum - x == currentSum){
                maxLen = Math.max(maxLen, r - l + 1);
            }
        }

        return maxLen == 0 ? -1 : nums.length - maxLen;
    }
}

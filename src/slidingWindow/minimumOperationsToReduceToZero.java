package slidingWindow;

public class minimumOperationsToReduceToZero{
    public static void main(String[] args) {
        int[] nums = {1,1,4,2,3};
        int x = 5;
        System.out.println(minOperations(nums,x));
    }

    public static int minOperations(int[] nums, int x) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        int currSum = 0;
        int maxLen = -1;
        for(int r = 0,l=0 ; r < nums.length; r++){
            currSum += nums[r];
            while (l <= r && sum-currSum < x){
                currSum -= nums[l++];
            }

            if(sum - currSum == x){
                maxLen = Math.max(maxLen, r - l + 1);
            }
        }

        return  maxLen == -1 ? -1 : nums.length - maxLen;
    }
}

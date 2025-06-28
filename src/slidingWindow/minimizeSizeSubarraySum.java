package slidingWindow;

public class minimizeSizeSubarraySum {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1,1,1,1};
        int target = 11;
        System.out.println(minSubArrayLen(target,nums));
    }
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while(sum >= target){
                ans = Math.min(ans, i - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}

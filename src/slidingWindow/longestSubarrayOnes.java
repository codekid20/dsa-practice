package slidingWindow;

public class longestSubarrayOnes {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k = 3;
        System.out.println(longestSubarray(nums, k));
    }

    private static int longestSubarray(int[] nums, int k) {
        int zeroCount = 0;
        int ans =0;
        int start = 0;

        for (int i = 0; i < nums.length; i++) {
            zeroCount += (nums[i] == 0 ? 1 : 0);

            while(zeroCount > k) {
                zeroCount -= (nums[start] == 0 ? 1 : 0);
                start++;
            }

            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }
}

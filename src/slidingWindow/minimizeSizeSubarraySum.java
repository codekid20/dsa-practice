package slidingWindow;

public class minimizeSizeSubarraySum {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1,1,1,1};
        int target = 11;
        System.out.println(minSubArrayLen(target,nums));
    }

    // Sliding Window Approach (variable size)
    // Goal: Find the smallest length of a subarray whose sum >= target
    //
    // 1. We keep a running sum while moving the right pointer (i).
    // 2. Each time we add nums[i] into the sum, we check if the condition (sum >= target) is satisfied.
    // 3. If it is, we try to shrink the window from the left side
    //    - because a smaller window that still satisfies the condition is better.
    // 4. While sum >= target:
    //      - update the answer with the current window length (i - left + 1).
    //      - remove nums[left] from sum (shrink the window).
    //      - move left pointer forward.
    // 5. If no such subarray exists, return 0 (answer remains Integer.MAX_VALUE).
    //
    // Key Idea: Expand the window to meet the target, shrink it to minimize size.


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

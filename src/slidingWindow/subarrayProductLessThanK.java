package slidingWindow;

public class subarrayProductLessThanK {
    public static void main(String[] args) {
        int[] nums = {10,5,2,6};
        int k = 100;
        System.out.println(numSubarrayProductLessThanK(nums, k));
    }

    /**
     * Logic:
     * - Use a sliding window (two pointers: left and right).
     * - Maintain the running product of elements inside the window.
     * - Expand the window by moving the right pointer.
     * - If the product becomes >= k, shrink the window from the left
     *   until the product < k again.
     * - At each step, the number of valid subarrays ending at 'right'
     *   is (right - left + 1), because every subarray between left and right
     *   will have product < k.
     * - Accumulate this count for the final result.
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;
        int prod = 1;
        int left = 0;
        for(int right = 0; right < nums.length; right++){
            prod *= nums[right];
            while(prod >= k){
                prod /= nums[left++];
            }

            ans += right - left + 1;
        }

        return ans;
    }
}

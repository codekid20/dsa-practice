package slidingWindow;

public class subarrayProductLessThanK {
    public static void main(String[] args) {
        int[] nums = {10,5,2,6};
        int k = 100;
        System.out.println(numSubarrayProductLessThanK(nums, k));
    }

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

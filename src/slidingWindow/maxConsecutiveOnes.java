package slidingWindow;

public class maxConsecutiveOnes {
    public static void main(String[] args) {
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        System.out.println(longestOnes(nums, k));
    }

    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int zeroes = 0;
        int maxLen = 0;
        while(right < nums.length){
            if(nums[right] == 0){
                zeroes++;
            }
            if(zeroes > k){
                if(nums[left] == 0){
                    zeroes--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}

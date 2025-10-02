package slidingWindow;

public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};

        System.out.println(maxProduct(nums));
    }

    // CASE 1: All nums are +ve. {answer is Product of entire array}
    // 2. Even -ve rest all +ve. {answer is Product of entire array }
    // 3. odd -ve rest all +ve. {answer is either in prefix part or suffix part of the array}
    // 4. it has zeroes. {answer still lies in product of prefix or suffix parts. Only thing is after seeing 0 reset
    // product to 1}


    public static int maxProduct(int[] nums) {

        int prefix = 1;
        int suffix = 1;
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {

            if(prefix == 0) prefix = 1;
            if(suffix == 0) suffix = 1;

            prefix *= nums[i];
            suffix *= nums[nums.length - i - 1];

            ans = Math.max(ans, Math.max(prefix, suffix));
        }

        return ans;

    }
}

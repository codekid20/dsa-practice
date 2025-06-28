package twoPointers;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate2(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    // Approach 1: Not optimal
    // Gives TLE
    public static void rotate(int[] nums, int k) {

        while(k > 0){
            int temp = nums[nums.length - 1];
            for (int i = nums.length - 1; i > 0; i--) {
                nums[i] = nums[i-1];
            }
            nums[0] = temp;
            k--;
        }
    }


    public static void rotate2(int[] nums, int k) {
        k = k % nums.length;

        reverse(nums, 0, nums.length - 1);

        reverse(nums, 0, k - 1);

        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int i, int j) {

        while (i < j){
            int temp = nums[j];

            nums[j] = nums[i];

            nums[i] = temp;

            i++;

            j--;
        }
    }
}

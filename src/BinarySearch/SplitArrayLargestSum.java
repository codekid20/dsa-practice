package BinarySearch;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SplitArrayLargestSum {
    public static void main(String[] args) {
        int[] nums = {7,2,5,10,8};
        int k = 2;
        System.out.println(splitArray(nums, k));
    }

    public static int splitArray(int[] nums, int k) {

        int low = Arrays.stream(nums).max().getAsInt();
        int high = Arrays.stream(nums).sum();

        while (low <= high){

            int mid = low + (high - low) / 2;

            int length = valid(nums, mid);
            if(length > k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    private static int valid(int[] nums, int k) {

        int count = 1;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] <= k){
                sum += nums[i];
            } else {
                count++;
                sum = nums[i];
            }
        }

        return count;
    }
}

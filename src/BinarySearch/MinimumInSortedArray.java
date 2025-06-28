package BinarySearch;

public class MinimumInSortedArray {
    public static void main(String[] args) {

    }

    public static int findMin(int[] nums) {
        int ans = Integer.MAX_VALUE;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high){

            int mid = low + (high - low) / 2;

            // search space is already sorted
            // then always arr[low] will be smaller
            // in that search space;

            if(nums[low] <= nums[high]){
                ans = Math.min(ans, nums[low]);
                break;
            }

            if (nums[low] <= nums[mid]){
                ans = Math.min(ans, nums[low]);
                low = mid + 1;
            } else {
                ans = Math.min(ans, nums[mid]);
                high = mid - 1;
            }
        }

        return ans;
    }
}

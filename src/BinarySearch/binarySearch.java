package BinarySearch;

public class binarySearch {
    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 9;

        System.out.println(search(nums,target));
    }

    public static int search(int[] nums, int target) {

        return bs(nums, target, 0, nums.length - 1);
    }
    private static int bs(int[] nums, int target, int left, int right) {

        while (left <= right) {
            int mid = right - (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

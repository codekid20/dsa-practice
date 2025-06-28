package BinarySearch;

public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 7;

        System.out.println(searchInsert(nums, target));
    }
    public static int searchInsert(int[] nums, int target) {

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
        return left;
    }
}

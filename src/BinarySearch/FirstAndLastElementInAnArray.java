package BinarySearch;

public class FirstAndLastElementInAnArray {
    public static void main(String[] args) {

    }

    public int[] searchRange(int[] nums, int target) {

        int[] ans = {-1, -1};

        int first = search(nums, target, true);
        int last = search(nums, target, false);

        ans[0] = first;
        ans[1] = last;

        return ans;
    }

    private int search(int[] nums, int target, boolean b) {

        int ans = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(target < nums[mid]){
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                ans = mid;
                if(b){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return ans;
    }
}

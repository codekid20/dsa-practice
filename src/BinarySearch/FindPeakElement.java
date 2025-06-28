package BinarySearch;

public class FindPeakElement {
    public static void main(String[] args) {

    }

    public static int findPeakElement(int[] nums) {

        int n = nums.length;

        if(n == 1){
            return 0;
        }

        // Border conditions checking end elements.
        if(nums[0] > nums[1]){
            return 0;
        }

        if(nums[n-1] > nums[n-2]){
            return (n - 1);
        }

        int low = 1;
        int high = n - 2;

        while (low <= high){

            int mid = low + (high - low) / 2;

            if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid + 1]){
                return mid;
            }
            // if mid lies on increasing slop. then possibiltity of finding element on right.
            else if (nums[mid] > nums[mid - 1]) {
                low = mid + 1;
            }
            // if mid lies on decreasing slope. this condition will also help if it doesn;t lies on any slope by is the lowest point in graph.
            else {
                high = mid - 1;
            }
        }

        return -1;
    }
}

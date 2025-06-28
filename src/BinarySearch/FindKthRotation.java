package BinarySearch;
import java.util.*;
public class FindKthRotation {
    public static void main(String[] args) {

    }

    public int findKRotation(List<Integer> nums) {
        int ans = Integer.MAX_VALUE;
        int index = -1;
        int low = 0;
        int high = nums.size() - 1;

        while (low <= high){

            int mid = low + (high - low) / 2;

            // search space is already sorted
            // then always arr[low] will be smaller
            // in that search space;

            if(nums.get(low) <= nums.get(high)){

                if(nums.get(low) < ans){
                    index = low;
                }
                break;
            }

            if (nums.get(low) <= nums.get(mid)){
                if(nums.get(low) < ans){
                    index = low;
                }
                low = mid + 1;

            } else {
                if(nums.get(mid) < ans){
                    index = mid;
                }
                high = mid - 1;
            }
        }

        return index;
    }
}

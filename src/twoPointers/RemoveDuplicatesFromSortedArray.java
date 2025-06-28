package twoPointers;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {

    }

    // Algorithm / Intuition
    // 1. 1st Unique will be at 1st position and 2nd unique will be at 2nd position and so on.
    // 2. Keep left at 1st index which will keep count of uniques and also where to place every unique element.
    public static int removeDuplicates(int[] nums) {
        int left = 1;
        for (int right = 1; right < nums.length; right++) {
            if(nums[right] != nums[right - 1]){
                nums[left] = nums[right];
                left++;
            }
        }

        return left;
    }
}

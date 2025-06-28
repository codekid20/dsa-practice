package arrays;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {

    }

    public static int removeDuplicates(int[] nums) {
        int left = 1;
        for(int right = 1; right < nums.length; right++){
            if(nums[right] != nums[right - 1]){
                nums[left] = nums[right];
                left++;
            }
        }

        return left;
    }
}

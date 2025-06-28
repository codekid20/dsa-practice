package sorting;

import java.util.Arrays;

public class SetMismatch {
    public static void main(String[] args) {
        int[] nums = {1,1};
        System.out.println(Arrays.toString(findErrorNums(nums)));
    }

    public static int[] findErrorNums(int[] nums) {
        int i = 0;
        while (i < nums.length){
            int correct = nums[i] - 1;
            if (nums[i] != nums[correct]){
                swap(nums, i, correct);
            } else {
                i++;
            }
        }

        int[] ans = new int[2];
        for (int j = 0; j < nums.length; j++) {
            if(nums[j] != j + 1){
                ans[0] = nums[j];
                ans[1] = j + 1;
            }
        }
        return ans;
    }

    public static void swap(int[] nums, int first, int second){
        int temp = nums[second];
        nums[second] = nums[first];
        nums[first] = temp;
    }
}

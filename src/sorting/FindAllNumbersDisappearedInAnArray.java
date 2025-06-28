package sorting;
import java.util.*;
public class FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        int[] nums = {2,2,4,7,8,3,3,1};
        System.out.println(findDisappearedNumbers(nums));

    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i < nums.length){
            int correct = nums[i] - 1;
            if(nums[i] != nums[correct]){
                swap(nums,i,correct);
            } else {
                i++;
            }
        }

        for (int index = 0; index < nums.length; index++) {
            if(nums[index] != index + 1){
                ans.add(index + 1);
            }
        }

        return ans;
    }

    public static void swap(int[] arr, int first, int second){
        int temp = arr[second];
        arr[second] = arr[first];
        arr[first] = temp;
    }
}

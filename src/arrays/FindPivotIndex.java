package arrays;

public class FindPivotIndex {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        System.out.println(pivotIndex(arr));
    }

    // Intuition:
    // 1. For any array sum of element to the right of any index is equal to the sum of total element - sum of left - that index (nums[i])


    // Approach 1:
    public static int pivotIndex(int[] nums) {
        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            if(sum * 2 == totalSum + nums[i]){
                return i;
            }

        }

        return -1;
    }


    // Approach 2:
    public static int pivotIndex2(int[] nums) {
        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {

            if(sum * 2 == totalSum - nums[i]){
                return i;
            }

            sum += nums[i];

        }

        return -1;
    }

}

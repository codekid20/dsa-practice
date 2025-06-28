package arrays;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class ConcatenationOfArray {
    public static void main(String[] args) {
        int[] arr = {1,2,1};
        System.out.println(Arrays.toString(getConcatenation2(arr)));
    }

    // Approach 1: Two pass

    public static int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];
        for(int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
        }

        for (int i = nums.length; i < nums.length * 2; i++) {
            ans[i] = nums[i - nums.length];
        }

        return ans;
    }

    // Approach 2: 1 pass
    public static int[] getConcatenation2(int[] nums) {
        int[] ans = new int[nums.length * 2];
        for(int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[i + nums.length] = nums[i];
        }

        return ans;
    }
}

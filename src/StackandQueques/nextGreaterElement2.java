package StackandQueques;

import java.util.Arrays;
import java.util.Stack;

public class nextGreaterElement2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3};
        System.out.println(Arrays.toString(nextGreaterElements(arr)));
    }

    public static int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < 2 * nums.length; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i % nums.length]) {
                ans[stack.pop()] = nums[i % nums.length];
            }

            stack.push(i % nums.length);
        }

        return ans;
    }
}

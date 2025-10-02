package StackandQueques;

import java.util.Arrays;
import java.util.Stack;

public class nextGreaterElement2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3};
        System.out.println(Arrays.toString(nextGreaterElements2(arr)));
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

    // Approach 2

    public static int[] nextGreaterElements2(int[] nums) {

        int[] nge = new int[nums.length];
        Stack<Integer> st = new Stack<>();

        for (int i = 2 * nums.length - 1; i >= 0; i--) {

            while (!st.isEmpty() && st.peek() <= nums[i % nums.length]){
                st.pop();
            }

            if(i < nums.length){
                nge[i] = st.isEmpty() ? -1 : st.peek();
            }

            st.push(nums[i % nums.length]);
        }

        return nge;
    }
}

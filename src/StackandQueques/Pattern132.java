package StackandQueques;

import java.util.Stack;

public class Pattern132 {
    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2};
        System.out.println(find132pattern(nums));
    }

    public static boolean find132pattern(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int temp = Integer.MIN_VALUE;

        for (int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] < temp){
                return true;
            }
            while(!st.isEmpty() && st.peek() < nums[i]){
                temp = st.pop();
            }

            st.push(nums[i]);
        }

        return false;
    }
}

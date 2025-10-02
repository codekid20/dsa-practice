package StackandQueques;

import java.util.Stack;

/*
 This method checks whether a given array contains a "132 pattern":
 A 132 pattern is a subsequence of three integers `nums[i]`, `nums[j]`, `nums[k]` such that:
     i < j < k and nums[i] < nums[k] < nums[j]

 The algorithm uses a **monotonic decreasing stack** and reverse traversal for an efficient O(n) solution.

 Here's how it works:

 - Initialize a variable `temp` (representing nums[k]) to Integer.MIN_VALUE.
 - Traverse the array from right to left (i = n-1 to 0):
     • If the current number `nums[i]` is less than `temp`, we found a valid 132 pattern:
         nums[i] < temp (nums[k]) and there exists a nums[j] > temp already in stack → return true.
     • While the stack is not empty and top of the stack is less than `nums[i]`:
         - Pop from the stack and assign it to `temp`. This ensures that `temp` is the largest
           value smaller than `nums[i]` seen so far, i.e., a potential candidate for nums[k].
     • Push the current number onto the stack. This will serve as a future candidate for nums[j].

 - If no pattern is found after full traversal, return false.

 The stack maintains possible nums[j] values, and `temp` keeps track of the best candidate for nums[k].
*/


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

package StackandQueques;

import java.util.Arrays;
import java.util.Stack;

/*
 This method calculates how many days you have to wait until a warmer temperature for each day.
 It uses a monotonic decreasing stack to keep track of indices where we haven’t found a warmer day yet.

 Step-by-step explanation:

 - Initialize an empty stack to store indices of the temperature array.
 - Initialize a result array `ans` of the same length as the input, initialized to 0.

 - Loop through each index `i` in the temperatures array:
     • While the stack is not empty and the current temperature is greater than the temperature
       at the index on top of the stack:
         - Pop the index from the stack.
         - The current index `i` is the next warmer day for the popped index.
         - Store the difference `i - popped_index` in the result array at the popped index.
     • Push the current index `i` onto the stack.

 - After the loop, all remaining indices in the stack do not have a warmer day ahead,
   so their corresponding result values remain 0.

 This is a classic monotonic stack problem and runs in O(n) time.
*/

public class dailyTemperatures {
    public static void main(String[] args) {
//        int[] temperature = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] temperature = {30, 40, 50, 60};
        System.out.println(Arrays.toString(dailyTemperature(temperature)));
    }

    public static int[] dailyTemperature(int[] temperatures) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!st.isEmpty() && temperatures[st.peek()] < temperatures[i]){
                int index = st.pop();
                ans[index] = i - index;
            }

            st.push(i);
        }

        return ans;
    }
}

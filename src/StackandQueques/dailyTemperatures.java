package StackandQueques;

import java.util.Arrays;
import java.util.Stack;

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

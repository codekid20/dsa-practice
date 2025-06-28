package StackandQueques;

import java.util.Stack;

public class largestRectangleInHistogram {
    public static void main(String[] args) {
        int[] height = {2,4};
        System.out.println(largestRectangleArea(height));

    }

    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < heights.length - 1; i++) {
            while (!st.isEmpty() && heights[st.peek()] > heights[i]){
                int element = st.pop();
                int nse  = i;
                int pse = st.isEmpty() ? -1 : st.peek();

                maxArea = Math.max(maxArea, heights[element] * (nse - pse - 1));
            }

            st.push(i);
        }

        while(!st.isEmpty()){
            int nse = heights.length;
            int element = st.pop();
            int pse = st.isEmpty() ? -1 : st.peek();

            maxArea = Math.max(maxArea, heights[element] * (nse - pse - 1));
        }

        return maxArea;
    }
}

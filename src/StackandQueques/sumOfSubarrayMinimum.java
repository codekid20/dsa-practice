package StackandQueques;

import java.util.Stack;

public class sumOfSubarrayMinimum {
    public static void main(String[] args) {
        int[] arr = {3,1,2,4};
        System.out.println(sumSubarrayMins(arr));
    }

    public static int sumSubarrayMins(int[] arr) {
        int[] nse = findNSE(arr);
        int[] pse = findPSE(arr);
        int total = 0;
        int mod = (int)(1e9 + 7);

        for(int i = 0; i < arr.length; i++){
            int left = i - pse[i];
            int right = nse[i] - i;

            total = total + right * left * arr[i];
        }

        return total;
    }

    private static int[] findPSE(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]){
                st.pop();
            }

            ans[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        return ans;
    }

    private static int[] findNSE(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();

        for(int i = arr.length - 1; i >= 0; i--){
            while(!st.isEmpty() && arr[st.peek()] > arr[i]){
                st.pop();
            }

            ans[i] = st.isEmpty() ? arr.length : st.peek();

            st.push(i);
        }

        return ans;
    }
}

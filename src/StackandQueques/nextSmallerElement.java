package StackandQueques;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.*;

/*
 This method finds the **next smaller element to the right** for each element in the array.
 If there is no smaller element on the right, the answer remains -1 (default value).

 Here's how it works:

 - Initialize an `ans` array of the same size as `arr`, filled with -1.
 - Create an empty stack to store **indices** of elements.

 - Traverse the array from left to right:
     • While the stack is not empty and the current element is smaller than the element at the top index of the stack:
         - Pop the index from the stack.
         - For that index, set the current element as the next smaller element to its right.
     • Push the current index onto the stack.

 - At the end, indices still in the stack have no smaller element on their right, so those remain -1.

 This uses a monotonic **increasing stack** and runs in O(n) time.
*/


public class nextSmallerElement {
    public static void main(String[] args) {
        int[] arr = {4, 8, 5, 2, 25};
        System.out.println(Arrays.toString(nextSmallerElementLeft(arr)));
    }

    public static int[] nextSmallerElementLeft(int[] arr){
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < arr.length; i++){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int index = stack.pop();
                ans[index] = arr[i];
            }
            stack.push(i);
        }
        return ans;
    }

    public static ArrayList<Integer> nextSmallerElement1(ArrayList<Integer> arr, int n){

        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(n, -1));
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < arr.size(); i++) {

            while(!st.isEmpty() && arr.get(st.peek()) > arr.get(i)){

                ans.set(st.pop(), arr.get(i));
            }

            st.push(i);
        }

        return ans;
    }
}

package StackandQueques;
import java.util.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/*
 This method finds the next greater element for each number in nums1 based on their positions in nums2.
 Here's the step-by-step explanation:

 - Initialize a HashMap to store the next greater element for each number in nums2.
 - Initialize a stack to help find the next greater elements in a **monotonic decreasing fashion**.

 - Iterate through each element in nums2:
     • While the current number is greater than the top of the stack, pop elements from the stack.
       For each popped element, the current number is the "next greater" element. Store this in the map.
     • Push the current number onto the stack.

 - At this point, the map contains the next greater element for all numbers in nums2 that have one.

 - For each number in nums1:
     • Replace it with its next greater value from the map, or -1 if it doesn't exist.

 - Return the modified nums1 array, which now contains the next greater elements.

 This approach uses a stack to efficiently compute the next greater elements in O(n) time.
*/

public class NextGreaterElement1 {
    public static void main(String[] args) {

        int[] arr = {1, 3, 2, 4};

        System.out.println(nextLargerElement(arr));
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> st = new Stack<>();

        for (int num : nums2){
            while(!st.isEmpty() && st.peek() < num){
                map.put(st.pop(), num);
            }

            st.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {

            nums1[i] = map.getOrDefault(nums1[i], -1);
        }

        return nums1;
    }


    // Approach 2:

    public static ArrayList<Integer> nextLargerElement(int[] arr) {

        int[] nge = new int[arr.length];

        Stack<Integer> st = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= arr[i]){
                st.pop();
            }

            if(st.isEmpty()) nge[i] = -1;
            else nge[i] = st.peek();

            st.push(arr[i]);
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int num : nge) ans.add(num);

        return ans;
    }
}

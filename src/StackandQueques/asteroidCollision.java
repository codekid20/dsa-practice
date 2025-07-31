package StackandQueques;

import java.util.Arrays;
import java.util.Stack;

/*
 This method simulates asteroid collisions using a stack. Here's how it works, step by step:

 - Initialize an empty stack to keep track of right-moving asteroids.
 - Iterate through each asteroid in the input array:
     • If the asteroid is positive (moving right), push it onto the stack.
     • If the asteroid is negative (moving left):
         - While the stack is not empty, and the top of the stack is a smaller right-moving asteroid,
           pop it (as it gets destroyed in the collision).
         - If the top of the stack is equal in magnitude but opposite in direction, pop it (both explode).
         - If the stack is empty or the top is a left-moving asteroid, push the current asteroid (it survives).
 - After processing all asteroids, the stack contains only the surviving ones.
 - Build the result array by popping from the stack in reverse order to maintain the correct sequence.

 This approach models collisions using LIFO behavior and ensures only the non-collided asteroids are returned.
*/



public class asteroidCollision {
    public static void main(String[] args) {
        int[] asteroid = {8, -8};
        System.out.println(Arrays.toString(asteroidCollisions(asteroid)));
//        Stack<Integer, Integer> st = new Stack<>();

    }

    public static int[] asteroidCollisions(int[] asteroids) {

        Stack<Integer> st = new Stack<>();

        for(int asteroid : asteroids) {
            if (asteroid > 0) {
                st.push(asteroid);
            } else {
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(asteroid)) {
                    st.pop();
                }

                if (!st.isEmpty() && st.peek() == Math.abs(asteroid)) {
                    st.pop();
                } else if (st.isEmpty() || st.peek() < 0) {
                    st.push(asteroid);
                }
            }
        }
        int[] ans = new int[st.size()];
        for (int i = st.size() - 1; i >= 0 && !st.isEmpty(); i--){
            ans[i] = st.pop();
        }

        return ans;
    }
}

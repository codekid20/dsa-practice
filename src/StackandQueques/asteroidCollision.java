package StackandQueques;

import java.util.Arrays;
import java.util.Stack;

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

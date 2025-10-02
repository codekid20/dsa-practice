package GreedyAlgorithm;

public class JumpGame {
    public static void main(String[] args) {

        int[] nums = {3,2,1,0,4};
        System.out.println(canJump1(nums));
    }

    // Approach 1: Start from front and check the maximum you can jump.
    // And if at some point your earlier maximum is less than your current position, that means you can never reach your current position.
    public static boolean canJump(int[] nums) {

        int maxIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if(i > maxIndex) return false;

            maxIndex = Math.max(maxIndex, i + nums[i]);
        }


        return true;
    }

    // Approach 2:

    public static boolean canJump1(int[] nums) {

        int lastIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {

            if(i + nums[i] >= lastIndex) {
                lastIndex = i;
            }
        }

        return lastIndex == 0;
    }
}

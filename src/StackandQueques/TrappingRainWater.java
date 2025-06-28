package StackandQueques;

public class TrappingRainWater {
    public static void main(String[] args) {
//        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] heights = {4,2,0,3,2,5};
        System.out.println(trap(heights));
    }

    public static int trap(int[] height) {
        int total = 0;
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length - 1;

        while(left < right){
            if(height[left] < height[right]){
                if(leftMax > height[left]){
                    total += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                left += 1;
            } else{
                if(rightMax > height[right]){
                    total += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }

                right -= 1;
            }
        }

        return total;
    }
}

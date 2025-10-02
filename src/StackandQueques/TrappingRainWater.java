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

    public static int trap1(int[] height) {

        int ans = 0;

        int[] prefix = new int[height.length];
        int[] suffix = new int[height.length];

        prefix[0] = height[0];
        suffix[height.length - 1] = height[height.length - 1];

        for (int i = 1; i < height.length; i++) {
            prefix[i] = Math.max(prefix[i - 1], height[i]);
        }

        for (int i = height.length - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }

        for (int i = 0; i < height.length - 1; i++) {
            if(height[i] < prefix[i] && height[i] < suffix[i]){
                ans += Math.min(prefix[i], suffix[i]) - height[i];
            }
        }

        return ans;
    }
}

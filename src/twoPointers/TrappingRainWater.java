package twoPointers;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap2(height));
    }

    public static int trap(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length - 1;
        int ans = 0;

        while (left < right){
            if(height[left] < height[right]){
                if(leftMax > height[left]){
                    ans += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }

                left++;
            } else{
                if(rightMax > height[right]){
                    ans += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }

        return ans;
    }


    // Approach 2:
    public static int trap2(int[] height) {

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

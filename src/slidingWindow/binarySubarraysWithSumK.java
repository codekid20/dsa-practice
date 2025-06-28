package slidingWindow;

import java.util.HashMap;

public class binarySubarraysWithSumK {
    public static void main(String[] args) {
        int[] nums = {0,0,0,0,0};
        int goal = 0;
        System.out.println(numSubarraysWithSum(nums, goal));
    }
    public static int numSubarraysWithSum(int[] nums, int goal) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0,1);
        for(int i = 0; i < nums.length; i++){
            sum = sum + nums[i];
            int rem = sum - goal;
            if(map.containsKey(rem)){
                count = count + map.get(rem);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}

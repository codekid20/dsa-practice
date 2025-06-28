package slidingWindow;

import java.util.HashMap;

public class subArraySumsEqualsK {
    public static void main(String[] args) {
        int[] nums = {0,0,0,0,0,0,0,0,0,0};
        int k = 0;
        System.out.println(subarraySum(nums, k));
    }

    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0,1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rem = sum - k;
            if(map.containsKey(rem)){
                count = count + map.get(rem);
            }
            map.put(sum, map.getOrDefault(sum,0) + 1);
        }

        return count;
    }
}

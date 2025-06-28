package arrays;

import java.util.HashMap;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int k = 3;

        System.out.println(subarraySum(arr, k));
    }

    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(0,1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            int rem = sum - k;
            if(map.containsKey(rem)){
                count += map.get(rem);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}

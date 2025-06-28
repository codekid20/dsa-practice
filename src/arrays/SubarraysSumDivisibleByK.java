package arrays;

import java.util.HashMap;

public class SubarraysSumDivisibleByK {
    public static void main(String[] args) {
        int[] nums = {4,5,0,-2,-3,1};
        int k = 5;

        System.out.println(subarraysDivByK(nums, k));
    }

    public static int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rem = ((sum % k) + k) % k;
            if(map.containsKey(rem)){
                count += map.get(rem);
            }
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }
        return count;
    }

    // Approach 2:
    public static int subarraysDivByK2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int mod = sum % 2;
            if(mod < 0){
                mod +=k;
            }
            if(map.containsKey(mod)){
                count += map.get(mod);
            }
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        return count;
    }

    // Approach 3
    public static int subarraysDivByK3(int[] nums, int k) {
        int[] map = new int[k];
        map[0] = 1;
        int count = 0;
        int sum = 0;
        for(int num : nums){
            sum += num;
            int mod = sum % k;
            if(mod < 0){
                mod += k;
            }

            count += map[mod];
            map[mod]++;
        }
        return count;
    }
}

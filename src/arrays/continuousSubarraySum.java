package arrays;

import java.util.HashMap;

public class continuousSubarraySum {
    public static void main(String[] args) {
        int[] arr = {23,2,6,4,7};
        int k = 13;
        System.out.println(checkSubarraySum(arr,k));
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0,-1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            int mod = sum % k;
            if(map.containsKey(mod)){
                if(i - map.get(mod) > 1){
                    return true;
                }
            }

            if(!map.containsKey(mod)){
                map.put(mod, i);
            }

        }

        return false;
    }

    // Approach 2:
    public static boolean checkSubarraySum2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0,-1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            int mod = sum % k;
            if(map.containsKey(mod)){
                if(i - map.get(mod) > 1){
                    return true;
                }
            } else {
                map.put(mod, i);
            }
        }

        return false;
    }
}

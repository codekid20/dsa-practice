package arrays;

import java.util.HashMap;
import java.util.*;

public class MajorityElement2 {
    public static void main(String[] args) {
        int[] nums = {1,2};
        System.out.println(majorityElement(nums));
    }

    // Approach 1: O(N) time and 0(N) space
    public static List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num,0) + 1);
        }

        for(int key : map.keySet()){
            if(map.get(key) > n / 3){
                ans.add(key);
            }
        }

        return ans;
    }

    // Approach 2:
//    public static List<Integer> majorityElement2(int[] nums){
//
//    }
}

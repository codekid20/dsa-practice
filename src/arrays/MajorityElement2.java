package arrays;

import java.util.HashMap;
import java.util.*;

public class MajorityElement2 {
    public static void main(String[] args) {
        int[] nums = {1,2};
        System.out.println(majorityElement2(nums));
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

//     Approach 2:
    public static List<Integer> majorityElement2(int[] nums){

        int cnt1 = 0;
        int cnt2 = 0;
        List<Integer> ans = new ArrayList<>();
        int el1 = Integer.MIN_VALUE;
        int el2 = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if(cnt1 == 0 && el2 != nums[i]){
                cnt1 = 1;
                el1 = nums[i];
            } else if (cnt2 == 0 && el1 != nums[i]) {
                cnt2 = 1;
                el2 = nums[i];
            } else if (nums[i] == el1) {
                cnt1++;
            } else if (nums[i] == el2) {
                cnt2++;
            } else {
                cnt1--;
                cnt2--;
            }
        }

        cnt1 = 0;
        cnt2 = 0;

        for (int num : nums) {
            if(num == el1) {
                cnt1++;
            } else if (num == el2) {
                cnt2++;
            }
        }

        if(cnt1 > nums.length / 3) ans.add(el1);
        if(cnt2 > nums.length / 3) ans.add(el2);

        return ans;
    }
}

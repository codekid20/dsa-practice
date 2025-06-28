package arrays;

import java.util.*;

public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        int n = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        for(int num : nums){
            map.put(num, map.getOrDefault(num,0) + 1);
        }

        for(int key : map.keySet()){
            if(map.get(key) > n / 2){
                ans = key;
            }
        }

        return ans;
    }


    // Approach 2: SORTING
//    Intuition:
//    The intuition behind this approach is that if an element occurs more than n/2 times in the array (where n is the size of the array), it will always occupy the middle position when the array is sorted. Therefore, we can sort the array and return the element at index n/2.

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n/2];
    }


    // Approach 3:
    // MOORE VOTING ALGIRITHM

    public static int majorityElement3(int[] nums) {
        int count = 0;
        int candidate = 0;

        for(int num : nums){
            if(count == 0){
                candidate = num;
            }

            if(num == candidate){
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}

package arrays;

import java.util.HashMap;
import java.util.HashSet;

public class FindMissingPositive {
    public static void main(String[] args) {
        int[] nums = {7,8,9,11,12};
        System.out.println(firstMissingPositive(nums));

    }

    public static int firstMissingPositive(int[] nums) {
        int ans = 1;
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums){
            set.add(num);
        }
        int idx = 1;
        while (true){
            if(!set.contains(idx)){
                ans = idx;
                break;
            }
            idx++;
        }

        return ans;
    }

    // Approach 2: O(n) and O(1)

//    public static int firstMissingPositive2(int[] nums) {
//
//    }

}

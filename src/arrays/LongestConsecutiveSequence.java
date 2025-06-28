package arrays;

import java.util.HashSet;
import java.util.Map;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums = {1,0,1,2};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        int longest = 0;

        for(int num : set){
            if(!set.contains(num - 1)){
                int count = 1;
                while(set.contains(num + 1)){
                    count += 1;
                    num += 1;
                }
                longest = Math.max(longest, count);
            }
        }
        return longest;
    }
}

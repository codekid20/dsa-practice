package slidingWindow;

import java.util.HashMap;

public class subarraysWithKDifferentIntegers {
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,4};
        int k = 3;
        int ans = subarraysWithKDistinct(nums, k) - subarraysWithKDistinct(nums, k - 1);
        System.out.println(ans);
    }

    public static int subarraysWithKDistinct(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int left = 0;
        int right = 0;

        while(right < nums.length){
            map.put(nums[right],map.getOrDefault(nums[right],0) + 1);

            while(map.size() > k){
                map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);

                if (map.get(nums[left]) == 0){
                    map.remove(nums[left]);
                }
                left++;
            }

            count = count + (right - left + 1);
            right++;
        }

        return count;
    }
}

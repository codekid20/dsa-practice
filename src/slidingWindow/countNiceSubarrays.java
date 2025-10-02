package slidingWindow;

import java.util.HashMap;

public class countNiceSubarrays {
    public static void main(String[] args) {
        int[] nums = {2,2,2,1,2,2,1,2,2,2};
        int k = 2;
        System.out.println(numberOfSubarrays(nums, k));
    }

    // This problem is equivalent to "count subarrays with sum = k".
    // Since only odd numbers contribute to the count of "nice subarrays",
    // we can convert the array into 0s (for even numbers) and 1s (for odd numbers).
    // Thus, the problem reduces to finding the number of subarrays with sum = k in a binary array.

    public static int numberOfSubarrays(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0,1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] % 2;
            int rem = sum - k;
            if(map.containsKey(rem)){
                count = count + map.get(rem);
            }

            map.put(sum, map.getOrDefault(sum,0) + 1);
        }
        return count;
    }
}

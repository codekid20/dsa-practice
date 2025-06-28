package slidingWindow;

import java.util.HashMap;

public class longestSubarrayWithSumK {
    public static void main(String[] args) {
        int[] arr = {2, 2, 4, 1, 2};
        int k = 2;
        System.out.println(longestSubarray(arr, k));
    }

    // Approach 1: Optimal for array having negatives and positives
    public static int longestSubarray(int[] arr, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;
        int sum = 0;

        for(int i = 0; i < arr.length; i++) {
           sum += arr[i];
           if(sum == k){
               maxLen = Math.max(maxLen, i + 1);
           }
           int rem = sum - k;
           if(map.containsKey(rem)){
               int len = i - map.get(rem);
               maxLen = Math.max(maxLen, len);
           }

           if(!map.containsKey(sum)){
               map.put(sum, i);
           }

        }
        return maxLen;
    }

    // Approach 2: Optimal for array with positives only: Two Pointer
    public static int longestSubarray2(int[] arr, int k){
        int left = 0;
        int right = 0;
        int longest = 0;
        int sum = arr[0];
        int n = arr.length;
        while (right < n){
            while(left <= right && sum > k){
                sum -= arr[left++];
            }

            if(sum == k){
                longest = Math.max(longest, right - left + 1);;
            }

            right++;

            if(right < n) {
                sum += arr[right];
            }
        }

        return longest;
    }
}

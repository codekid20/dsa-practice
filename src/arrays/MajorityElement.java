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

//        for(int key : map.keySet()){
//            if(map.get(key) > n / 2){
//                ans = key;
//            }
//        }

        n = n / 2;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > n) {
                ans = entry.getKey();
            }
        }

        return ans;
    }


    // Approach 2: SORTING
//    Intuition:
//    The intuition behind this approach is that if an element occurs more than n/2 times in the array (where n is the size of the array),
//    it will always occupy the middle position when the array is sorted. Therefore, we can sort the array and return the element at index n/2.

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n/2];
    }


    // Approach 3:
    // MOORE VOTING ALGIRITHM

//    The algorithm starts by assuming the first element as the majority candidate and sets the count to 1.
//    As it iterates through the array, it compares each element with the candidate:
//    a. If the current element matches the candidate, it suggests that it reinforces the majority element because it appears again. Therefore, the count is incremented by 1.
//    b. If the current element is different from the candidate, it suggests that there might be an equal number of occurrences of the majority element and other elements. Therefore, the count is decremented by 1.
//
//    Note that decrementing the count doesn't change the fact that the majority element occurs more than n/2 times.
//
//    If the count becomes 0, it means that the current candidate is no longer a potential majority element. In this case, a new candidate is chosen from the remaining elements.
//    The algorithm continues this process until it has traversed the entire array.
//    The final value of the candidate variable will hold the majority element

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

package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumSubsequenceScore {
    public static void main(String[] args) {
//        int[] arr = {1, 4, 7, 9 , 2};
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        int ans = 0;
//        int sum = 0;
//        for(int num : arr){ // FINDING THREE NUMBER FROM AN ARRAY WHOSE SUM IS MAXIMUM
//            pq.add(num);
//            sum += num;
//            if(pq.size() > 3){
//                sum -= pq.poll();
//            }
//            if(pq.size() == 3){
//                ans = Math.max(ans, sum);
//            }
//        }
//        System.out.println(ans);

        int[] nums1 = {1,3,3,2};
        int[] nums2 = {2,1,3,4};
        int k = 3;

        System.out.println(maxScore(nums1, nums2, k));
    }

    public static long maxScore(int[] nums1, int[] nums2, int k) {

        int[][] pairs = new int[nums1.length][2];

        for (int i = 0; i < nums1.length; i++) {
            pairs[i] = new int[]{nums2[i],nums1[i]};
        }

        Arrays.sort(pairs, (a,b) -> b[0] - a[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        long ans = 0;
        long sum = 0;

        for(int[] pair : pairs){
            pq.offer(pair[1]);

            sum += pair[1];


            if(pq.size() > k){
                sum -= pq.poll();
            }

            if(pq.size() == k){
                ans = Math.max(ans, sum * pair[0]);
            }
        }

        return ans;
    }
}

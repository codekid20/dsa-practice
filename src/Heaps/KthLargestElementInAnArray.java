package Heaps;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        System.out.println(findKthLargest(nums, k));
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int num: nums){
            minHeap.offer(num);

            if (minHeap.size() > k){
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }
}

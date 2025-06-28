package Heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class kthLargestElementInAStream {
    public static void main(String[] args) {
        int[] arr = {4,5,8,2};
        int k = 3;
        kthLargestElementInAStream l = new kthLargestElementInAStream(k, arr);
        System.out.println(l.add(3));
        System.out.println(l.add(5));
        System.out.println(l.add(10));
        System.out.println(l.add(9));
        System.out.println(l.add(4));



    }

    private PriorityQueue<Integer> minHeap;
    private int k;
    public kthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();

        for(int num : nums){
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val);

        if(minHeap.size() > k){
            minHeap.poll();
        }

        return minHeap.peek();
    }
}

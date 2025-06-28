package Heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class priorityQueue {
    public static void main(String[] args) {

        // MIN HEAP USING PRIORITY QUEUE
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//
//        minHeap.offer(10);
//        minHeap.offer(5);
//        minHeap.offer(2);
//
//        while (!minHeap.isEmpty()){
//            System.out.println(minHeap.poll());
//        }


        // MAX HEAP

//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
//
//        maxHeap.offer(10);
//        maxHeap.offer(5);
//        maxHeap.offer(2);
//
//        while (!maxHeap.isEmpty()){
//            System.out.println(maxHeap.poll());
//        }


//        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
//        int[] arr = {1,2,3,5,5};
//
//        for(int num : arr){
//            pq.add(num);
//        }

//        System.out.println(pq.size());
//        int n = pq.poll() - pq.poll();
//        System.out.println(pq.size());
//        pq.offer(n);
//        System.out.println(pq.size());

//        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));
//        int[][] points = {{3,3}, {5,-1},{-2,4}};
//        int k = 2;
//
//        for(int[] point : points){
//            maxHeap.add(point);
//
//            if(maxHeap.size() > k){
//                maxHeap.remove();
//            }
//        }
//
//        int[][] ans = new int[k][2];
//
//        int i = 0;
//        while (!maxHeap.isEmpty()){
//            ans[i] = maxHeap.poll();
//            i++;
//        }
//
////        System.out.println(Arrays.toString(maxHeap.peek()));
//        System.out.println(Arrays.deepToString(ans));


//        String s = "1";
//        int i = Integer.parseInt(s);
//        String ss = Integer.toString(i);
//        System.out.println();


        String s = "2";
        String  ss  = "22";

        System.out.println(s.compareTo(ss));


    }
}

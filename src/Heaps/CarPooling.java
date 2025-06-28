package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CarPooling {
    public static void main(String[] args) {
        int[][] arr = {{2,1,5},{3,3,7}};

//        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
//        for (int[] a : arr){
//            pq.add(a);
//        }
//
//        while (!pq.isEmpty()){
//            System.out.println(Arrays.toString(pq.poll()));
//        }

        int cap = 5;

        System.out.println(carPooling2(arr, cap));
    }

    public static boolean carPooling(int[][] trips, int capacity) {
        int[] preFixSum = new int[1001];

        for (int[] trip : trips){
            preFixSum[trip[1]] += trip[0];
            preFixSum[trip[2]] -= trip[0];
        }

//        System.out.println(Arrays.toString(preFixSum));
        int cap = 0;
        for (int i = 0; i < 1001; i++) {
            cap = cap + preFixSum[i];

            if(cap > capacity){
                return false;
            }
        }

        return true;
    }


    public static boolean carPooling2(int[][] trips, int capacity){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for (int[] trip : trips){
            pq.offer(new int[]{trip[2], -trip[0]});
            pq.offer(new int[]{trip[1], trip[0]});
        }

//        while (!pq.isEmpty()){
//            System.out.println(Arrays.toString(pq.poll()));
//        }

        int cap = 0;
        while (!pq.isEmpty()){
            cap += pq.poll()[1];

            if(cap > capacity){
                return false;
            }
        }

        return true;
    }
}

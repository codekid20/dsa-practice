package Heaps;

import java.util.PriorityQueue;

public class MinimumCostOfRopes {
    public static void main(String[] args) {
        int[] arr = {4,3,2,6};
        System.out.println(minCost(arr));
    }

    public static int minCost(int[] arr) {
        int cost = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : arr){
            pq.add(num);
        }

        while (pq.size() > 1){
            int first = pq.poll();
            int second = pq.poll();

            cost += first + second;
            pq.add(first + second);
        }


        return cost;
    }
}

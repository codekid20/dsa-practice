package Heaps;

import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {

    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        for(int stone : stones){
            pq.offer(stone);
        }

        while (pq.size() > 1){
            int first = pq.poll();
            int second = pq.poll();

            if(first != second){
                pq.offer(first - second);
            }
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }
}

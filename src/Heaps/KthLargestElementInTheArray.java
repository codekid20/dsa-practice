package Heaps;

import java.util.PriorityQueue;

public class KthLargestElementInTheArray {
    public static void main(String[] args) {

    }

    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            if(a.length() != b.length()){
                return a.length() - b.length();
            }

            return a.compareTo(b);
        });

        for (String str : nums){
            pq.offer(str);

            if(pq.size() > k){
                pq.poll();
            }
        }

        return pq.peek();
    }
}

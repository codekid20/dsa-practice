package Heaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class DefiningHeaps {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> b.compareTo(a));

        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());
    }
}

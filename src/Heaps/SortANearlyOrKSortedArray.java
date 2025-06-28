package Heaps;

import java.util.PriorityQueue;

public class SortANearlyOrKSortedArray {
    public static void main(String[] args) {

    }

    // Intitution: For every index i the element that will come at that position is either k distance to the left
    // or k distance to the right. So if we store k+1 elements in heap and pop the smallest element from it
    // its position with me at 0 index(because sorting in ascending order).
    public static int[] nearlySorted(int[] array, int n, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < k; i++){
            pq.offer(array[i]);
        }

        int i;
        for (i = k; i < array.length; i++) {
            pq.offer(array[i]);

            array[i-k] = pq.poll();
        }

        while (!pq.isEmpty()){
            array[i-k] = pq.poll();
            i++;
        }

        return array;
    }
}

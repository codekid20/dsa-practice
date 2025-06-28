package Heaps;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Heap<Integer> heap = new Heap<>();

        heap.insert(34);
        heap.insert(24);
        heap.insert(50);
        heap.insert(4);
        heap.insert(21);

        ArrayList<Integer> list = heap.heapSort();
        System.out.println(list);
    }
}

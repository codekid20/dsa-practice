package Heaps;

import java.util.Arrays;

public class HeapSortInDecreasingOrderUsingMinHeap {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int n = arr.length;

        heapSort(arr, n);

        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr, int n) {

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for(int i = n - 1; i >= 0; i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i,0);
        }
    }

    private static void heapify(int[] arr, int size, int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if(left < size && arr[left] < arr[smallest]){
            smallest = left;
        }

        if(right < size && arr[right] < arr[smallest]){
            smallest = right;
        }

        if(smallest != index){
            int temp = arr[index];
            arr[index] = arr[smallest];
            arr[smallest] = temp;

            heapify(arr, size, smallest);
        }
    }
}

package Heaps;

public class MinHeapToMaxHeap {
    static void convertMinToMaxHeap(int N, int arr[]) {
        for (int i = (N - 2) / 2; i >= 0 ; i--) {
            downheap(arr, i);
        }
    }

    private static int left(int index){
        return (index * 2)  + 1;
    }

    private static int right(int index){
        return (index * 2)  + 2;
    }
    private static void swap(int[] arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private static void downheap(int[] arr, int index){
        int largest = index;
        int left = left(index);
        int right = right(index);

        if(left < arr.length && arr[left] > arr[index]){
            largest = left;
        }

        if(right < arr.length && arr[right] > arr[largest]){
            largest = right;
        }

        if(largest != index){
            swap(arr, largest, index);
            downheap(arr,largest);
        }
    }
}

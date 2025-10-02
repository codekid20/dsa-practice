package sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {1, 4, 5, 2, 3};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public static void sort(int[] nums, int start, int end){

        if(start >= end){
            return;
        }

        int l = start;
        int h = end;
        int pivot = nums[start];

        while(l <= h){

            while(nums[l] < pivot && l <= end){
                l++;
            }

            while (nums[h] > pivot && h >= start){
                h--;
            }

            if(l <= h){
                int temp = nums[l];
                nums[l] = nums[h];
                nums[h] = temp;
                l++;
                h--;
            }

        }
        sort(nums, start, h);
        sort(nums, l, end);
    }


    public static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(nums, start, end); // <-- pivot is placed correctly here
            quickSort(nums, start, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, end);
        }
    }

    // Lomuto partition scheme
    private static int partition(int[] nums, int start, int end) {
        int pivot = nums[end];  // choose last element as pivot
        int i = start - 1;      // place for smaller elements

        for (int j = start; j < end; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }

        // Place pivot in its correct position
        swap(nums, i + 1, end);
        return i + 1;  // pivot index (now fixed)
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

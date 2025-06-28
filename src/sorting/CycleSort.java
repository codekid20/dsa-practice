package sorting;

import java.util.Arrays;

public class CycleSort {
    public static void main(String[] args) {
        int[] nums = {3,4,1,2,5};
        cyclicSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void cyclicSort(int[] arr){
        int i = 0;
        while (i < arr.length){
            int correct = arr[i] - 1;
            if(arr[i] != arr[correct]){
                swap(arr, i, correct);
            } else {
                i++;
            }
        }
    }

    public static void swap(int[] arr, int first, int second){
        int temp = arr[second];
        arr[second] = arr[first];
        arr[first] = temp;
    }
}

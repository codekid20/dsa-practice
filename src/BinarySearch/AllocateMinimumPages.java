package BinarySearch;

import java.util.Arrays;

public class AllocateMinimumPages {
    public static void main(String[] args) {
        int[] arr = {12, 34, 67, 90};
        int k = 2;

        System.out.println(findPages(arr, k));
    }

    public static int findPages(int[] arr, int k) {

        if(arr.length < k){
            return -1;
        }

        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();

        while (low <= high){

            int mid = low + (high - low) / 2;

            int students = countStudents(arr, mid);
            if(students > k){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    private static int countStudents(int[] arr, int pages) {

        int students = 1;
        int pagesCount = 0;

        for (int i = 0; i < arr.length; i++) {
            if(pagesCount + arr[i] <= pages){
                pagesCount += arr[i];
            } else {
                students++;
                pagesCount = arr[i];
            }
        }

        return students;
    }
}

package twoPointers;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
        int [] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;
        merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }

    // Algorithm:
    // Since The arrays are sorted, the either of the last elements of both arrays would be the biggest of all.
    // Identify that biggest and place it in the end.
    // Keep doing this till you process all elements and array is sorted

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (j >= 0){
            if(i >= 0 && nums1[i] > nums2[j]){
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }
}

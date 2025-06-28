package BinarySearch;

public class MedianOfSortedArrays {
    public static void main(String[] args) {

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int i = 0;
        int j = 0;

        int n = (n1 + n2);
        int ind2 = n / 2;
        int ind1 = ind2 - 1;
        int cnt = 0;
        int ind1e1 = -1;
        int ind2e2 = -1;

        while (i < n1 && j < n2){
            if(nums1[i] < nums2[j]){
                if(cnt == ind1) ind1e1 = nums1[i];
                if(cnt == ind2) ind2e2 = nums1[i];
                cnt++;
                i++;
            } else {
                if(cnt == ind1) ind1e1 = nums2[j];
                if(cnt == ind2) ind2e2 = nums2[j];
                cnt++;
                j++;
            }
        }

        while (i < n1){
            if(cnt == ind1) ind1e1 = nums1[i];
            if(cnt == ind2) ind2e2 = nums1[i];
            cnt++;
            i++;
        }


        while (j < n2){
            if(cnt == ind1) ind1e1 = nums2[j];
            if(cnt == ind2) ind2e2 = nums2[j];
            cnt++;
            j++;
        }

        if(n % 2 == 1){
            return ind2e2;
        }

        return (double) (ind1e1 + ind2e2) / 2;
    }


    // Approach 2: Binary Search
}

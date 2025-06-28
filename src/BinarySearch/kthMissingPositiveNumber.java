package BinarySearch;

public class kthMissingPositiveNumber {
    public static void main(String[] args) {

    }

    // Approach 1:
    public static int findKthPositive(int[] arr, int k) {

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] <= k){
                k++;
            } else {
                break;
            }
        }

        return k;
    }
}

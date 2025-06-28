package BinarySearch;
import java.util.*;
public class CapacityToShipPackagesWithinDays {
    public static void main(String[] args) {

    }

    public static int shipWithinDays(int[] weights, int days) {

//        int low = Arrays.stream(weights).max().getAsInt();
//        int high = Arrays.stream(weights).sum();

        int low = findMax(weights);
        int high = findSum(weights);

        while (low <= high){

            int mid = low + (high - low) / 2;

            int totalDays = findDays(weights, mid);
            if(totalDays <= days){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static int findDays(int[] weights, int cap) {
        int day = 1;
        int load = 0;

        for (int i = 0; i < weights.length; i++) {
            if(load + weights[i] > cap){
                day += 1;
                load = weights[i]; // movement it exceeds that weight will be taken next day.
            } else {
                load += weights[i];
            }
        }

        return day;
    }

    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // Function to calculate sum of int array
    public static int findSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }
}

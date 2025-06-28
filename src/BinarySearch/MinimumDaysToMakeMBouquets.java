package BinarySearch;
import java.util.*;
public class MinimumDaysToMakeMBouquets {
    public static void main(String[] args) {
        int[] bloomDay = {7,7,7,7,12,7,7};
        int m = 2, k = 3;

        System.out.println(minDays(bloomDay, m, k));
    }

    public static int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        long val = (long) m * (long) k; // might overflow, so converted to val.
        if(val > n){
            return -1;
        }

        int low = Arrays.stream(bloomDay).min().getAsInt();
        int high = Arrays.stream(bloomDay).max().getAsInt();

        while (low <= high){

            int mid = low + (high - low) / 2;

            if(minBouquet(bloomDay, mid, m, k)){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static boolean minBouquet(int[] bloomDay, int day, int m, int k) {

        int cnt = 0;

        int numberOfBouquet = 0;

        for (int i = 0; i < bloomDay.length; i++) {
            if(bloomDay[i] <= day){
                cnt++;
            } else {
                numberOfBouquet += cnt / k;
                cnt = 0;
            }
        }

        numberOfBouquet += cnt / k;

        return numberOfBouquet >= m;
    }
}

package BinarySearch;

import java.util.Arrays;

public class AggressiveCows {
    public static void main(String[] args) {

    }

    public static int aggressiveCows(int[] stalls, int k) {

        Arrays.sort(stalls);
        int n = stalls.length;
        int low = 1;
        int high = stalls[n - 1] - stalls[0];

        while (low <= high){

            int mid = low + (high - low) / 2;
            if(canWePlace(stalls, mid, k)){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    private static boolean canWePlace(int[] stalls, int mid, int cows) {

        int cntCows = 1;
        int lastCow = stalls[0];

        for (int i = 0; i < stalls.length; i++) {
            if(stalls[i] - lastCow >= mid){
                cntCows++;
                lastCow = stalls[i];
            }

            if(cntCows >= cows){
                return true;
            }
        }

        return false;
    }
}

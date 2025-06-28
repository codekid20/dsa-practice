package BinarySearch;

public class KokoEatingBananas {
    public static void main(String[] args) {

    }

    public static int minEatingSpeed(int[] piles, int h) {

        int left = 1;
        int right = 1000000000;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if(canEat(piles, mid, h)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean canEat(int[] piles, int k, int h) {

        int hours = 0;

        for (int i = 0; i < piles.length; i++) {
            hours += Math.ceil((double) piles[i] / (double) k);
        }

        return hours <= h;
    }

    // Approach 2:

    public static int minEatingSpeed2(int[] piles, int h) {

        int left = 1;
        int right = findMax(piles);

        while (left <= right){
            int mid = left + (right - left) / 2;

            if(canEat2(piles, mid, h)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static int findMax(int[] piles) {
        int max = 0;

        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }

        return max;
    }

    private static boolean canEat2(int[] piles, int k, int h) {

        int hours = 0;

        for (int i = 0; i < piles.length; i++) {
            hours += Math.ceil((double) piles[i] / (double) k);
        }

        return hours <= h;
    }
}

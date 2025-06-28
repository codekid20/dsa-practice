package BinarySearch;

public class HigherOrLower {
    public static void main(String[] args) {

    }

    public static int guessNumber(int n) {
        int left = 1;
        int right = n;

        while (left <= right){

            int mid = left + (right - left) / 2;

            if(guess(mid) == -1){
                right = mid - 1;
            } else if (guess(mid) == 1) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    private static int guess(int mid) {
        return -1;
    }
}

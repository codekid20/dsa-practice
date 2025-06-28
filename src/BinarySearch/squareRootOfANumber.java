package BinarySearch;

public class squareRootOfANumber {
    public static void main(String[] args) {
        int x = 25;
        System.out.println(mySqrt(x));
    }

    public static int mySqrt(int x) {

        long left = 1;
        long right = x;

        while (left <= right){

            long mid = left + (right - left) / 2;
            long val = mid * mid;
            if(val > x){
                right = mid -1;
            } else if (val < x) {
                left = mid + 1;
            } else {
                return (int) mid;
            }
        }

        return (int) right;
    }
}

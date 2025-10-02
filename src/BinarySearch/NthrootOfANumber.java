package BinarySearch;

public class NthrootOfANumber {
    public static void main(String[] args) {

    }

    public int nthRoot(int n, int m) {

        int low = 1;
        int high = m;

        while (low <= high){

            int mid = (low + high) / 2;

            int val = power(mid, n, m);

            if(val == 1){
                return mid;
            } else if (val == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    private int power(int mid, int n, int m) {

        long ans = 1;

        for (int i = 1; i <= n; i++) {
            ans = ans * mid;
            if(ans > m) return 2;
        }

        if(ans == m) return 1;
        return 0;
    }
}

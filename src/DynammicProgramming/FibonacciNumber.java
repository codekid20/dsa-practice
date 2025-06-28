package DynammicProgramming;
import java.util.*;
public class FibonacciNumber {
    public static void main(String[] args) {
        int n = 48;
        System.out.println(fib(n));
    }

    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public static int fib(int n) {

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return fibo(n, dp);
    }

    private static int fibo(int n, int[] dp) {
        if(n <= 1){
            return n;
        }

        if(dp[n] != -1){
            return dp[n];
        }

        return dp[n] = fibo(n - 1, dp) + fibo(n - 2, dp);
    }

    public static int fib1(int n) {
        int prev2 = 0;
        int prev1 = 1;

        if(n == 0){
            return prev2;
        }

        for (int i = 2; i <= n; i++) {
            int current = prev2 + prev1;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}

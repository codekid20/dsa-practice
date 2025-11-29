package DynammicProgramming;

import java.util.Arrays;

public class DecodeWays {
    public static void main(String[] args) {

    }

    public static int numDecodings(String s) {

        if(s == null || s.length() == 0) return 0;
        return recHelper(s, 0);
    }

    private static int recHelper(String s, int idx) {

        int n = s.length();
        if(idx == n) return 1;

        if(s.charAt(idx) == '0') return 0;

        // single digit
        int ways = recHelper(s, idx + 1);

        // try two digits if within bounds and forms number between 10 and 26

        if(idx + 1 < n) {

            int two = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
            if(two >= 10 && two <= 26) {
                ways += recHelper(s, idx + 2);
            }
        }

        return ways;
    }

    // Memoization
    public static int numDecodings1(String s) {

        if(s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return memoHelper(s, 0, dp);
    }

    private static int memoHelper(String s, int idx, int[] dp) {

        int n = s.length();
        if(idx == n) return 1;

        if(s.charAt(idx) == '0') return 0;

        if(dp[idx] != -1) return dp[idx];

        // single digit
        int ways = memoHelper(s, idx + 1, dp);

        // try two digits if within bounds and forms number between 10 and 26

        if(idx + 1 < n) {

            int two = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
            if(two >= 10 && two <= 26) {
                ways += memoHelper(s, idx + 2, dp);
            }
        }

        dp[idx] = ways;
        return ways;
    }


    // Tabulation
    public static int numDecodings2(String s) {

        if(s == null || s.length() == 0) return 0;
        int n = s.length();
        if(s.charAt(0) == '0') return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {

            if(s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            int two = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if(two >= 10 && two <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}

package DynammicProgramming;

public class MinimumInsertionsToMakeStringPalindrome {
    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(minInsertions(s));
    }

    public static int minInsertions(String s) {

        return s.length() - longestPalindromeSubseq(s);
    }

    public static int longestPalindromeSubseq(String s) {

        String reverse = new StringBuilder(s).reverse().toString();

        return longestCommonSubsequence(s, reverse);
    }

    public static int longestCommonSubsequence(String text1, String text2) {

        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        for (int pos1 = 1; pos1 <= n; pos1++) {
            for (int pos2 = 1; pos2 <=  m; pos2++) {
                if(text1.charAt(pos1 - 1) == text2.charAt(pos2 - 1)){
                    dp[pos1][pos2] = 1 + dp[pos1 - 1][ pos2 - 1];
                } else {
                    dp[pos1][pos2] = Math.max(dp[pos1 - 1][ pos2], dp[pos1] [pos2 - 1]);
                }
            }
        }

        return dp[n][m];

    }
}

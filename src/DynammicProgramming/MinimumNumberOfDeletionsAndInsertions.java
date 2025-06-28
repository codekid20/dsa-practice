package DynammicProgramming;

public class MinimumNumberOfDeletionsAndInsertions {
    public static void main(String[] args) {
        String s1 = "heap", s2 = "pea";

        System.out.println(minOperations(s1,s2));
    }

    public static int minOperations(String s1, String s2) {

        return s1.length() + s2.length() - 2 * longestCommonSubsequence1(s1, s2);
    }

    public static int longestCommonSubsequence1(String text1, String text2) {

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

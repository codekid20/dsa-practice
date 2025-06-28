package DynammicProgramming;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        String s1 = "ABCDGH", s2 = "ACDGHR";

        System.out.println(longestCommonSubstr(s1,s2));
    }

    public static int longestCommonSubstr(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }
        int ans = 0;
        for (int pos1 = 1; pos1 <= n; pos1++) {
            for (int pos2 = 1; pos2 <=  m; pos2++) {
                if(s1.charAt(pos1 - 1) == s2.charAt(pos2 - 1)){
                    dp[pos1][pos2] = 1 + dp[pos1 - 1][ pos2 - 1];
                    ans = Math.max(ans, dp[pos1][pos2]);
                } else {
                    dp[pos1][pos2] = 0;
                }
            }
        }
        return ans;
    }
}

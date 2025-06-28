package DynammicProgramming;

import java.util.Arrays;
/*
Problem: Longest Common Subsequence (LCS)
Given two strings, find the length of their longest subsequence
that appears in both strings in the same relative order.

Reasoning:
- A subsequence does not require contiguity, only order.
- The problem boils down to finding all matching characters in order,
  while skipping the mismatches optimally.
- If characters match, it contributes to LCS; if not, skip one character
  from either string and take the best result.

Approach:
We implement 3 standard DP methods:

1. Top-down (Recursion + Memoization):
   - Recurse over both strings from the end.
   - If characters match: include this character in the count: 1 + lcs(i-1, j-1)
   - Else: skip one character either from text1 or text2 and take max result.
   - Memoize results in dp[i][j] to avoid recomputation.
   - Good for learning and understanding the recursive structure.

2. Bottom-up (Tabulation):
   - Build a dp table from smaller subproblems.
   - dp[i][j] means LCS of text1[0..i-1] and text2[0..j-1]
   - Initialize first row/col with 0 (empty string case).
   - This method avoids recursion overhead and is iterative.

3. Space Optimized:
   - Each row of the DP table depends only on the previous row.
   - Use two 1D arrays (`dp` for previous row and `curr` for current).
   - Saves memory from O(n*m) to O(m), especially helpful for large inputs.

Key Points:
- Recurrence:
    if (text1[i-1] == text2[j-1]) → dp[i][j] = 1 + dp[i-1][j-1]
    else → dp[i][j] = max(dp[i-1][j], dp[i][j-1])
- This problem is foundational for string DP problems like:
  Longest Palindromic Subsequence, Shortest Common Supersequence, etc.

Time: O(n * m)
Space:
- Top-down: O(n * m) + recursion stack
- Tabulation: O(n * m)
- Space optimized: O(m)
*/

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String text1 = "abc", text2 = "def";

        System.out.println(longestCommonSubsequence1(text1, text2));
    }

    public static int longestCommonSubsequence(String text1, String text2) {

        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n][m];
        for (int[] row : dp){
            Arrays.fill(row, -1);
        }

        return lcs(n-1,m-1,text1,text2, dp);
    }

    private static int lcs(int pos1, int pos2, String text1, String text2, int[][] dp) {

        if(pos1 < 0 || pos2 < 0){
            return 0;
        }

        if(dp[pos1][pos2] != -1) return dp[pos1][pos2];

        if(text1.charAt(pos1) == text2.charAt(pos2)){
            return dp[pos1][pos2] = 1 + lcs(pos1 - 1, pos2 - 1, text1, text2, dp);
        }

        return dp[pos1][pos2] = Math.max(lcs(pos1 - 1, pos2, text1,text2,dp), lcs(pos1, pos2 - 1, text1, text2, dp));
    }


    // Tabulation

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


    // Space Optimization

    public static int longestCommonSubsequence2(String text1, String text2) {

        int n = text1.length();
        int m = text2.length();

        int[] dp = new int[m+1];


        for (int j = 0; j <= m; j++) {
            dp[j] = 0;
        }

        for (int pos1 = 1; pos1 <= n; pos1++) {
            int[] curr = new int[m+1];
            for (int pos2 = 1; pos2 <=  m; pos2++) {
                if(text1.charAt(pos1 - 1) == text2.charAt(pos2 - 1)){
                    curr[pos2] = 1 + dp[ pos2 - 1];
                } else {
                    curr[pos2] = Math.max(dp[pos2], curr[pos2 - 1]);
                }
            }

            dp = curr;
        }

        return dp[m];
    }

}

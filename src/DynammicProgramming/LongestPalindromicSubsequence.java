package DynammicProgramming;

import Trees.SumRootToLeadNumbers;

/*
Problem: Longest Palindromic Subsequence (LPS)
Given a string `s`, return the length of the longest subsequence that is also a palindrome.

Key Insight:
- A palindrome reads the same forward and backward.
- The LPS of a string is the Longest Common Subsequence (LCS) between the string and its reverse.

Approach:
1. Reverse the string.
2. Apply the standard LCS algorithm between the original string and its reversed version.
   - If characters match: add 1 to the LCS of remaining parts.
   - Else: take max between skipping one character from either string.
3. Return the length of the LCS → this is the length of the Longest Palindromic Subsequence.

Time Complexity: O(n * n)
Space Complexity: O(n * n)

Note:
- This approach does not find the actual subsequence, just its length.
- Useful building block for related problems like:
    - Minimum insertions/deletions to make a string palindrome
    - Longest Palindromic Substring (different problem!)
*/

public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(longestPalindromeSubseq(s));
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

package DynammicProgramming;

import java.util.Arrays;

/*
Problem: Edit Distance (Leetcode 72)
Given two strings `word1` and `word2`, return the minimum number of operations
required to convert `word1` to `word2`. Allowed operations:
- Insert a character
- Delete a character
- Replace a character

Approach: Dynamic Programming (Recursion + Memoization)
- Let i and j be the current indices in word1 and word2 (from the end).
- If characters match, move both pointers.
- If not, try all 3 operations and take the minimum:
   1. Insert → i stays, j--
   2. Delete → i--, j stays
   3. Replace → i--, j--
- Base cases:
   - If i < 0, we need to insert all remaining characters from word2 → j+1 ops
   - If j < 0, we need to delete all remaining characters from word1 → i+1 ops

Memoization:
- Use a 2D dp[i][j] array to store results of subproblems.

Time: O(n * m)
Space: O(n * m) for dp table + recursion stack

Note:
- Can be converted to tabulation or space-optimized versions.
- Important classic DP problem for string transformation tasks.
*/


public class EditDistance {
    public static void main(String[] args) {
        String word1 = "intention", word2 = "execution";

        System.out.println(minDistance2(word1, word2));
    }

    public static int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return minOperations(n, m, word1, word2, dp);
    }

    private static int minOperations(int i, int j, String word1, String word2, int[][] dp) {

        if(i == 0) return j;
        if(j == 0) return i;

        if(dp[i][j] != -1) return dp[i][j];

        if(word1.charAt(i-1) == word2.charAt(j-1)){
            return dp[i][j] = minOperations(i-1,j-1,word1,word2, dp);
        } else {
            return dp[i][j] = Math.min(1 + minOperations(i,j-1,word1,word2, dp), Math.min(1+ minOperations(i-1,j,word1,word2, dp), 1 + minOperations(i-1,j-1,word1,word2,dp)));
        }
    }


    // Tabulation

    public static int minDistance1(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(1 + dp[i][j-1], Math.min(1 + dp[i-1][j], 1 + dp[i-1][j-1]));
                }
            }
        }

        return dp[n][m];
    }

    // Space Optimization
    public static int minDistance2(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();
        int[] dp = new int[m+1];

//        for (int i = 0; i <= n; i++) {
//            dp[i][0] = i;
//        }

        for (int j = 0; j <= m; j++) {
            dp[j] = j;
        }


        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m+1];
            curr[0] = i;
            for (int j = 1; j <= m; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    curr[j] = dp[j-1];
                } else {
                    curr[j] = Math.min(1 + curr[j-1], Math.min(1 + dp[j], 1 + dp[j-1]));
                }
            }

            dp = curr;
        }

        return dp[m];
    }
}

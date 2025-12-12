package DynammicProgramming;

public class MinimumInsertionsToMakeStringPalindrome {
    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(minInsertions(s));
    }

    /*
     * MINIMUM INSERTIONS TO MAKE STRING PALINDROME
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given a string, find the MINIMUM number of characters that need to be
     * INSERTED to make the string a palindrome.
     *
     * Example:
     *   s = "abcde"
     *   → Insert "dcba" at end → "abcdedcba" (palindrome)
     *   → Minimum insertions = 4
     *
     *   s = "mbadm"
     *   → Insert "a" → "mabadm" or "mbadma"
     *   → OR insert "b" → "mbdabm" or "mbdbadm"
     *   → Minimum insertions = 2
     *
     * BRILLIANT INSIGHT - THE KEY OBSERVATION:
     * ----------------------------------------
     * To minimize insertions, we should MAXIMIZE the characters that are
     * already forming a palindrome pattern!
     *
     * Characters that form the LONGEST PALINDROMIC SUBSEQUENCE don't need
     * any insertions - they're already in palindromic order!
     *
     * Only the REMAINING characters need their "mirror" counterparts inserted.
     *
     * FORMULA:
     * --------
     * Minimum Insertions = Total Length - Longest Palindromic Subsequence Length
     *
     * Why?
     *   - LPS characters are already palindromic → need 0 insertions
     *   - Remaining (n - LPS) characters → need (n - LPS) mirror insertions
     *
     * PROBLEM TRANSFORMATION:
     * -----------------------
     * 1. Find Longest Palindromic Subsequence (LPS)
     * 2. Subtract LPS length from total length
     *
     * But how to find LPS?
     * → Another brilliant insight: LPS = LCS(string, reverse(string))!
     *
     * WHY LPS = LCS(s, reverse(s))?
     * ------------------------------
     * A palindrome reads the same forwards and backwards.
     *
     * If a subsequence appears in BOTH:
     *   - Original string (left to right)
     *   - Reversed string (right to left)
     *
     * Then that subsequence must be palindromic!
     *
     * Example: s = "bbbab"
     *   reverse = "babbb"
     *   LCS = "bbbb" or "bbb" (length 4)
     *   This is also the longest palindromic subsequence!
     *
     * CHAIN OF TRANSFORMATIONS:
     * -------------------------
     * Original Problem: Minimum Insertions to Make Palindrome
     *       ↓
     * Transform to: n - Longest Palindromic Subsequence
     *       ↓
     * Transform to: n - LCS(string, reverse(string))
     *       ↓
     * Solve using: Standard LCS (Longest Common Subsequence) algorithm
     *
     * ==================== LONGEST COMMON SUBSEQUENCE (LCS) ====================
     */

    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        // Create DP table
        // dp[i][j] = Length of LCS of text1[0...i-1] and text2[0...j-1]
        int[][] dp = new int[n+1][m+1];

        // Base case: LCS with empty string is 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        // Fill DP table
        for (int pos1 = 1; pos1 <= n; pos1++) {
            for (int pos2 = 1; pos2 <= m; pos2++) {

                // If characters match
                if(text1.charAt(pos1 - 1) == text2.charAt(pos2 - 1)){
                    // Include this character in LCS
                    dp[pos1][pos2] = 1 + dp[pos1 - 1][pos2 - 1];
                }
                // If characters don't match
                else {
                    // Take maximum by either:
                    // - Excluding current char from text1: dp[pos1-1][pos2]
                    // - Excluding current char from text2: dp[pos1][pos2-1]
                    dp[pos1][pos2] = Math.max(dp[pos1 - 1][pos2], dp[pos1][pos2 - 1]);
                }
            }
        }

        // LCS length is at bottom-right cell
        return dp[n][m];
    }

    /*
     * LCS LOGIC RECAP:
     * ----------------
     * - If characters match: Include it, add 1 to diagonal value
     * - If characters don't match: Take max from left or top
     *   (try excluding from either string)
     *
     * KEY DIFFERENCE from Longest Common Substring:
     * - Substring: Reset to 0 on mismatch (contiguous required)
     * - Subsequence: Take max from neighbors (can skip characters)
     *
     * ==================== LONGEST PALINDROMIC SUBSEQUENCE ====================
     */

    public static int longestPalindromeSubseq(String s) {
        // Create reversed string
        String reverse = new StringBuilder(s).reverse().toString();

        // LPS = LCS of string and its reverse
        // Characters that appear in same relative order in both
        // directions form a palindrome!
        return longestCommonSubsequence(s, reverse);
    }

    /*
     * WHY THIS WORKS:
     * ---------------
     * Example: s = "bbbab"
     * reverse = "babbb"
     *
     * LCS matching:
     *   b-b-b-a-b (original)
     *   b-a-b-b-b (reverse)
     *   └─┴─┴─┴─┘
     *   Common: "bbbb" (or "bbb")
     *
     * This subsequence "bbbb" is palindromic because it appears in the
     * same order when reading forward (original) and backward (reverse)!
     *
     * ==================== MINIMUM INSERTIONS ====================
     */

    public static int minInsertions(String s) {
        // Formula: Total length - LPS length
        // Characters not in LPS need their mirrors inserted
        return s.length() - longestPalindromeSubseq(s);
    }

    /*
     * COMPLETE EXAMPLE WALKTHROUGH:
     * ------------------------------
     * s = "mbadm"
     *
     * Step 1: Find reverse
     *   reverse = "mdabm"
     *
     * Step 2: Find LCS(s, reverse)
     *
     *   DP Table for LCS:
     *         ''  m  d  a  b  m
     *     ''   0  0  0  0  0  0
     *     m    0  1  1  1  1  1  ← 'm' matches
     *     b    0  1  1  1  2  2  ← 'b' matches
     *     a    0  1  1  2  2  2  ← 'a' matches
     *     d    0  1  2  2  2  2  ← 'd' matches
     *     m    0  1  2  2  2  3  ← 'm' matches again
     *
     *   LCS = "mdm" or "mbm" (length 3)
     *   This is the longest palindromic subsequence!
     *
     * Step 3: Calculate minimum insertions
     *   Total length = 5
     *   LPS length = 3
     *   Min insertions = 5 - 3 = 2
     *
     * Verification:
     *   Original: m-b-a-d-m
     *   LPS:      m---d-m (or m-b---m)
     *
     *   Need to insert mirrors for: 'b' and 'a' (or 'a' and 'd')
     *   Possible palindromes: "mbdabm", "mdabadm", "mabddbam", etc.
     *   All require 2 insertions!
     *
     * ANOTHER EXAMPLE:
     * ----------------
     * s = "leetcode"
     * reverse = "edocteel"
     * LCS = "ete" or "eco" (length 3)
     * Min insertions = 8 - 3 = 5
     *
     * Result: "leetcodocteel" (inserted "docto")
     * Or: "leedtcodeectl" (inserted various chars)
     *
     * INTUITIVE UNDERSTANDING:
     * ------------------------
     * Think of it this way:
     *
     * 1. Find the "skeleton" of palindrome already in the string (LPS)
     * 2. For each character NOT in this skeleton, we need to insert its
     *    mirror on the opposite side
     * 3. Total insertions = characters not in skeleton = n - LPS
     *
     * Visual Example: s = "abc"
     *   LPS = "a" or "b" or "c" (length 1)
     *
     *   To make palindrome from "a":
     *     Insert "cb" → "cbabc" or "abcba"
     *     Insertions = 2
     *
     *   Formula: 3 - 1 = 2 ✓
     *
     * TIME COMPLEXITY: O(n²)
     *   - Creating reverse: O(n)
     *   - LCS computation: O(n × n) = O(n²)
     *   - Total: O(n²)
     *
     * SPACE COMPLEXITY: O(n²)
     *   - DP table for LCS: O(n²)
     *   - Can be optimized to O(n) using space-optimized LCS
     *
     * KEY INSIGHTS:
     * -------------
     * 1. PROBLEM CHAINING: Complex problem → simpler subproblems
     *    (Min Insertions → LPS → LCS)
     *
     * 2. PALINDROME PROPERTY: Reading same forward/backward means
     *    characters appear in LCS of string and its reverse
     *
     * 3. COMPLEMENTARY THINKING: Instead of thinking "what to add",
     *    think "what's already good" (LPS) and add rest
     *
     * 4. This showcases how understanding relationships between problems
     *    (LPS = LCS with reverse) leads to elegant solutions!
     *
     * RELATED PROBLEMS:
     * -----------------
     * - Minimum Deletions to Make Palindrome: Same formula! (n - LPS)
     * - Shortest Palindrome by Prepending: Different approach needed
     * - Palindrome Partitioning: Uses different DP strategy
     */
}

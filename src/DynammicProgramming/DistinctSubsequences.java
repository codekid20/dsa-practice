package DynammicProgramming;

import java.util.Arrays;

public class DistinctSubsequences {
    public static void main(String[] args) {
        String s = "babgbag", t = "bag";

        System.out.println(numDistinct1(s,t));
    }


    /*
     * DISTINCT SUBSEQUENCES
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given two strings s and t, count the number of DISTINCT subsequences of s
     * that equal t.
     *
     * Example:
     *   s = "rabbbit"
     *   t = "rabbit"
     *
     *   Distinct ways to form "rabbit" from "rabbbit":
     *   1. r-a-b-b-b-i-t (use 1st 'b', 2nd 'b', 3rd 'b')
     *   2. r-a-b-b-b-i-t (use 1st 'b', 2nd 'b', skip 3rd)
     *   3. r-a-b-b-b-i-t (use 1st 'b', skip 2nd, use 3rd)
     *
     *   Answer = 3 distinct ways
     *
     * KEY INSIGHT - CHOICE AT EACH MATCH:
     * -----------------------------------
     * When we find a matching character, we have TWO OPTIONS:
     *   1. USE this match: Continue matching rest of t with rest of s
     *   2. SKIP this match: Look for another occurrence in s
     *
     * This is different from standard LCS where we only count length!
     * Here we count ALL POSSIBLE WAYS to form the subsequence.
     *
     * INTUITION:
     * ----------
     * Think of it as a "character consumption" problem:
     *   - We're trying to consume all characters of t using characters from s
     *   - When we find a match, we can either:
     *     a) Consume it (match complete for this char, move to next in both)
     *     b) Save it for later (skip it in s, maybe use another match ahead)
     *
     * WHY COUNT BOTH OPTIONS?
     * -----------------------
     * Because different choices lead to DISTINCT subsequences!
     *
     * Example: s = "abb", t = "ab"
     *   Match 1: Use 1st 'b' → "a-b-b" → forms "ab"
     *   Match 2: Use 2nd 'b' → "a-b-b" → forms "ab"
     *   Count = 2 (both are distinct ways)
     *
     * ==================== MEMOIZATION APPROACH ====================
     *
     * DP STATE:
     * ---------
     * dp[i][j] = Number of distinct subsequences of s[0...i-1] that equal t[0...j-1]
     *
     * i = current position in s (source string)
     * j = current position in t (target string)
     */

    public static int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        // Initialize DP table with -1 (uncomputed state)
        int[][] dp = new int[n+1][m+1];
        for (int[] row : dp){
            Arrays.fill(row, -1);
        }

        return helper(n, m, s, t, dp);
    }

    private static int helper(int i, int j, String s, String t, int[][] dp) {
        /*
         * BASE CASES:
         * -----------
         *
         * BASE CASE 1: j == 0 (target string t is empty)
         *   → We've successfully matched all of t!
         *   → Empty string is a subsequence of any string
         *   → Return 1 (one way: match complete)
         *
         * Why 1 and not 0?
         *   Because finding 0 characters (empty t) in any string is
         *   ALWAYS possible in exactly ONE way: select nothing!
         */
        if(j == 0) return 1;

        /*
         * BASE CASE 2: i == 0 (source string s is empty, but t is not)
         *   → s is empty but we still need to match characters in t
         *   → IMPOSSIBLE!
         *   → Return 0 (no way to match)
         */
        if(i == 0) return 0;

        // Return memoized result if already computed
        if(dp[i][j] != -1) return dp[i][j];

        /*
         * RECURSIVE CASES:
         * ----------------
         *
         * CASE 1: Characters MATCH (s[i-1] == t[j-1])
         *   We have TWO choices, and we COUNT BOTH:
         *
         *   Choice A: USE this match
         *     → Move both pointers back: helper(i-1, j-1)
         *     → We've matched t[j-1] with s[i-1], continue with rest
         *
         *   Choice B: SKIP this match (don't use s[i-1] for t[j-1])
         *     → Move only s pointer back: helper(i-1, j)
         *     → Maybe we'll find another s[k] that matches t[j-1]
         *
         *   Total ways = Choice A + Choice B
         */
        if(s.charAt(i-1) == t.charAt(j-1)){
            return dp[i][j] = helper(i-1, j-1, s, t, dp) + helper(i-1, j, s, t, dp);
        }
        /*
         * CASE 2: Characters DON'T match (s[i-1] != t[j-1])
         *   Only ONE choice:
         *
         *   SKIP s[i-1]: It's useless for matching t[j-1]
         *     → Move only s pointer back: helper(i-1, j)
         *     → Look for t[j-1] in earlier positions of s
         */
        else {
            return dp[i][j] = helper(i-1, j, s, t, dp);
        }
    }

    /*
     * EXAMPLE WALKTHROUGH (Memoization):
     * -----------------------------------
     * s = "babgbag"
     * t = "bag"
     *
     * We want to find how many ways we can form "bag" from "babgbag"
     *
     * Visual matching:
     *   b-a-b-g-b-a-g
     *   1.  b-a-g (use 1st 'b', 1st 'a', 1st 'g')
     *   2.  b-a-g (use 1st 'b', 1st 'a', 2nd 'g')
     *   3.  b-a-g (use 1st 'b', 2nd 'a', 1st 'g')
     *   4.  b-a-g (use 1st 'b', 2nd 'a', 2nd 'g')
     *   5.  b-a-g (use 2nd 'b', 2nd 'a', 1st 'g')
     *
     * Answer = 5 distinct ways
     *
     * ==================== TABULATION APPROACH ====================
     */

    public static int numDistinct1(String s, String t) {
        int n = s.length();
        int m = t.length();

        // Create DP table
        int[][] dp = new int[n+1][m+1];

        /*
         * BASE CASE INITIALIZATION:
         * -------------------------
         *
         * 1. First row dp[0][i] = 0 for i > 0
         *    → s is empty, t is not empty → 0 ways
         */
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        /*
         * 2. First column dp[i][0] = 1 for all i
         *    → t is empty (matching nothing) → always 1 way
         *    → Empty string is subsequence of any string in exactly 1 way
         */
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        /*
         * FILL THE DP TABLE:
         * ------------------
         * Process bottom-up, building solutions for larger subproblems
         */
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                // If characters match
                if(s.charAt(i-1) == t.charAt(j-1)){
                    /*
                     * TWO CHOICES (add both):
                     *
                     * 1. USE this match: dp[i-1][j-1]
                     *    → Matched current chars, count ways for remaining
                     *
                     * 2. SKIP this match: dp[i-1][j]
                     *    → Don't use this s[i-1], look for other matches
                     */
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }
                // If characters don't match
                else {
                    /*
                     * ONE CHOICE:
                     *
                     * SKIP s[i-1]: dp[i-1][j]
                     *    → Current s char is useless, try previous positions
                     */
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        // Answer is at bottom-right: ways to form full t from full s
        return dp[n][m];
    }

    /*
     * DP TABLE VISUALIZATION:
     * ------------------------
     * s = "rabbbit"
     * t = "rabbit"
     *
     *         ''  r  a  b  b  i  t
     *     ''   1  0  0  0  0  0  0  ← Base: t empty = 1 way
     *     r    1  1  0  0  0  0  0  ← Found 'r'
     *     a    1  1  1  0  0  0  0  ← Found 'a'
     *     b    1  1  1  1  0  0  0  ← Found 1st 'b'
     *     b    1  1  1  2  1  0  0  ← Found 2nd 'b' (2 ways for "rab")
     *     b    1  1  1  3  3  0  0  ← Found 3rd 'b' (3 ways for "rabb")
     *     i    1  1  1  3  3  3  0  ← Found 'i'
     *     t    1  1  1  3  3  3  3  ← Found 't' (3 distinct ways!)
     *
     * At dp[4][4] (matching "rabb" from "rabb"):
     *   s[3]='b' matches t[3]='b'
     *   dp[4][4] = dp[3][3] + dp[3][4]
     *            = 1 (use this 'b') + 2 (skip this 'b', use earlier ones)
     *            = 3
     *
     * KEY OBSERVATION IN TABLE:
     * -------------------------
     * When we have multiple same characters in s, the count accumulates!
     * Each additional matching character provides MORE ways to form t.
     *
     * RECURRENCE RELATION SUMMARY:
     * ----------------------------
     *
     *                 ┌─ 1,                           if j == 0
     *                 │
     * dp[i][j] =      ┤─ 0,                           if i == 0 && j > 0
     *                 │
     *                 ├─ dp[i-1][j-1] + dp[i-1][j],  if s[i-1] == t[j-1]
     *                 │
     *                 └─ dp[i-1][j],                 if s[i-1] != t[j-1]
     *
     * INTUITIVE UNDERSTANDING:
     * ------------------------
     * Think of it as "path counting" in a decision tree:
     *   - Each match gives us a FORK: use it or skip it
     *   - Each path from root to "t fully matched" is a distinct subsequence
     *   - We're counting ALL such paths
     *
     * Example: s="aaa", t="aa"
     *   Paths:
     *     1. Use 1st 'a', use 2nd 'a' → "aa"
     *     2. Use 1st 'a', use 3rd 'a' → "aa"
     *     3. Use 2nd 'a', use 3rd 'a' → "aa"
     *   Count = 3
     *
     * TIME COMPLEXITY: O(n × m)
     *   - Fill table of size (n+1) × (m+1)
     *   - Each cell computed in O(1)
     *
     * SPACE COMPLEXITY:
     *   - Memoization: O(n × m) + O(n + m) recursion stack
     *   - Tabulation: O(n × m) only
     *   - Can be optimized to O(m) using rolling array technique
     *
     * EDGE CASES:
     * -----------
     * 1. t is empty → Always return 1 (one way: select nothing)
     * 2. s is empty but t is not → Return 0 (impossible)
     * 3. t longer than s → Return 0 (impossible to form)
     * 4. s = t → Return 1 (exact match, one way)
     * 5. All characters same: s="aaa", t="aa" → Return C(3,2) = 3
     *
     * KEY INSIGHTS:
     * -------------
     * 1. This is a COUNTING problem, not optimization (max/min)
     *    → We ADD possibilities instead of taking MAX
     *
     * 2. When characters match, we explore BOTH branches:
     *    → Use the match (move both pointers)
     *    → Skip the match (move only s pointer)
     *
     * 3. The "+  instead of "max" changes everything:
     *    → From "find best subsequence" to "count all subsequences"
     *
     * 4. This demonstrates combinatorics in DP:
     *    → Each match point is a decision point
     *    → Total ways = product of choices at each decision
     *
     * RELATED PROBLEMS:
     * -----------------
     * - Edit Distance: Similar structure, different operations
     * - Longest Common Subsequence: Find length, not count
     * - Regular Expression Matching: Similar choice-making logic
     * - Wildcard Matching: Pattern matching with counting
     */
}

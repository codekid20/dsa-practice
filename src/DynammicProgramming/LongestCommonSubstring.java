package DynammicProgramming;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        String s1 = "ABCDGH", s2 = "ACDGHR";

        System.out.println(longestCommonSubstr(s1,s2));
    }

    /*
     * LONGEST COMMON SUBSTRING
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given two strings, find the length of the LONGEST COMMON SUBSTRING.
     *
     * IMPORTANT: SUBSTRING vs SUBSEQUENCE
     * ------------------------------------
     * - SUBSTRING: Characters must be CONTIGUOUS (consecutive)
     * - SUBSEQUENCE: Characters can be NON-CONTIGUOUS (can skip characters)
     *
     * Example:
     *   s1 = "abcde"
     *   s2 = "abfce"
     *
     *   Common Substrings: "ab" (length 2), "c" (length 1), "e" (length 1)
     *   Longest Common Substring = "ab" → Length = 2
     *
     *   (Note: Common Subsequence "abce" has length 4, but NOT a substring!)
     *
     * KEY INTUITION:
     * --------------
     * Unlike subsequence where we can skip characters, substring requires
     * CONSECUTIVE matches. If characters don't match, we RESET the count to 0
     * instead of taking max from previous states.
     *
     * When characters match:
     *   - Extend the current substring by 1
     *   - Current length = 1 + length of substring ending at previous positions
     *
     * When characters DON'T match:
     *   - BREAK the substring (reset to 0)
     *   - Cannot skip characters like in subsequence problem
     *
     * DP STATE MEANING:
     * -----------------
     * dp[i][j] = Length of longest common substring ENDING at s1[i-1] and s2[j-1]
     *
     * CRITICAL: "ENDING AT" is key!
     * - dp[i][j] specifically tracks substrings that END at these positions
     * - If characters don't match, there's NO substring ending here → 0
     * - We track global maximum separately in 'ans' variable
     *
     * APPROACH:
     * ---------
     * 1. CREATE DP TABLE:
     *    - Size: (n+1) × (m+1) to handle empty string cases
     *    - dp[i][j] represents substring ending at s1[i-1] and s2[j-1]
     *
     * 2. INITIALIZATION:
     *    - dp[i][0] = 0 for all i (no substring with empty string)
     *    - dp[0][j] = 0 for all j (no substring with empty string)
     *
     * 3. FILL DP TABLE:
     *    For each position (i, j):
     *
     *    a) If s1[i-1] == s2[j-1] (characters MATCH):
     *       - Extend previous substring: dp[i][j] = 1 + dp[i-1][j-1]
     *       - Update global maximum: ans = max(ans, dp[i][j])
     *
     *    b) If s1[i-1] != s2[j-1] (characters DON'T match):
     *       - RESET: dp[i][j] = 0
     *       - No substring can end at this position with mismatched chars
     *
     * 4. TRACK MAXIMUM:
     *    - Unlike LCS (subsequence) where answer is dp[n][m]
     *    - Here we track maximum value seen ANYWHERE in table
     *    - Because longest substring can end at ANY position, not just last
     *
     * EXAMPLE WALKTHROUGH:
     * --------------------
     * s1 = "abcd"
     * s2 = "abzd"
     *
     * DP Table:
     *         ''  a  b  z  d
     *     ''   0  0  0  0  0
     *     a    0  1  0  0  0  ← 'a' matches: 1+dp[0][0]=1
     *     b    0  0  2  0  0  ← 'b' matches: 1+dp[1][1]=2 (substring "ab")
     *     c    0  0  0  0  0  ← 'c' doesn't match 'z': RESET to 0
     *     d    0  0  0  0  1  ← 'd' matches: 1+dp[2][2]=1 (new substring "d")
     *
     * Maximum value in table = 2 (substring "ab")
     *
     * WHY RESET TO 0?
     * ---------------
     * Consider: s1 = "abc", s2 = "def"
     *
     * At any position, if characters don't match, there's NO substring
     * ending at that position. We cannot "carry forward" a previous
     * substring length because substring requires CONSECUTIVE characters.
     *
     * This is the KEY difference from Longest Common Subsequence!
     *
     * COMPARISON WITH LCS (Longest Common Subsequence):
     * --------------------------------------------------
     * LCS (Subsequence):
     *   if match: dp[i][j] = 1 + dp[i-1][j-1]
     *   if no match: dp[i][j] = max(dp[i-1][j], dp[i][j-1])  ← Can skip!
     *   Answer: dp[n][m]
     *
     * LCS (Substring):
     *   if match: dp[i][j] = 1 + dp[i-1][j-1]
     *   if no match: dp[i][j] = 0  ← MUST reset!
     *   Answer: max value in entire table
     *
     * ANOTHER EXAMPLE:
     * ----------------
     * s1 = "GeeksforGeeks"
     * s2 = "GeeksQuiz"
     *
     * Common substrings: "Geeks" (length 5), "G" (length 1)
     * Longest = "Geeks" → Length = 5
     *
     * When we hit 'f' vs 'Q' after matching "Geeks":
     *   - Reset dp value to 0
     *   - Cannot extend "Geeks" because next characters don't match
     *
     * DP STATE TRANSITION:
     * --------------------
     *                    ┌─────────────────────┐
     *                    │  s1[i-1] == s2[j-1] │
     *                    └──────────┬──────────┘
     *                               │
     *                  ┌────────────┴────────────┐
     *                  │                         │
     *              YES │                         │ NO
     *                  │                         │
     *         ┌────────▼────────┐       ┌───────▼────────┐
     *         │ 1 + dp[i-1][j-1]│       │  dp[i][j] = 0  │
     *         │  (extend substr) │       │  (reset/break) │
     *         └─────────────────┘       └────────────────┘
     *
     * TIME COMPLEXITY: O(n × m)
     *   - Two nested loops iterating through both strings
     *
     * SPACE COMPLEXITY: O(n × m)
     *   - 2D DP table
     *   - Can be optimized to O(m) using two 1D arrays (prev, curr)
     *
     * SPACE OPTIMIZATION HINT:
     * ------------------------
     * Since we only need dp[i-1][j-1] (diagonal element), we can use
     * two 1D arrays or even single array with careful iteration.
     *
     * KEY INSIGHTS:
     * -------------
     * 1. "ENDING AT" interpretation is crucial - dp[i][j] tracks substring
     *    that MUST end at positions i and j
     *
     * 2. RESET to 0 on mismatch is what differentiates substring from subsequence
     *
     * 3. Global maximum tracking is necessary because longest substring
     *    can end ANYWHERE, not necessarily at the last position
     *
     * 4. This problem shows how small changes in recurrence relation
     *    (reset vs max) fundamentally change the problem being solved!
     */

    public static int longestCommonSubstr(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Create DP table with size (n+1) × (m+1)
        int[][] dp = new int[n+1][m+1];

        // Base case: Initialize first row (empty s1)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // Base case: Initialize first column (empty s2)
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        // Variable to track global maximum (longest substring length)
        int ans = 0;

        // Fill the DP table
        for (int pos1 = 1; pos1 <= n; pos1++) {
            for (int pos2 = 1; pos2 <= m; pos2++) {

                // If characters match at current positions
                if(s1.charAt(pos1 - 1) == s2.charAt(pos2 - 1)){
                    // Extend the substring ending at previous diagonal position
                    dp[pos1][pos2] = 1 + dp[pos1 - 1][pos2 - 1];

                    // Update global maximum if current substring is longer
                    ans = Math.max(ans, dp[pos1][pos2]);
                }
                // If characters don't match
                else {
                    // RESET: No substring can end here with mismatched characters
                    dp[pos1][pos2] = 0;
                }
            }
        }

        // Return the maximum substring length found
        return ans;
    }
}

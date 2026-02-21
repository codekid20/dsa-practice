package DynammicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {

        int[] nums = {1};
        lengthOfLIS5(nums);
    }

    /*
     * LONGEST INCREASING SUBSEQUENCE (LIS)
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given an array of integers, find the length of the LONGEST STRICTLY INCREASING
     * SUBSEQUENCE.
     *
     * A subsequence:
     *   - Maintains relative order of elements (can skip elements)
     *   - Does NOT need to be contiguous
     *   - Strictly increasing: each element > previous element (not >=)
     *
     * Example:
     *   nums = [10, 9, 2, 5, 3, 7, 101, 18]
     *
     *   Some increasing subsequences:
     *     [2, 5, 7, 101] → length 4
     *     [2, 3, 7, 18] → length 4
     *     [2, 5, 7, 18] → length 4
     *     [9, 101] → length 2
     *
     *   Longest = 4 (multiple valid answers)
     *
     * Example 2:
     *   nums = [0, 1, 0, 3, 2, 3]
     *   LIS: [0, 1, 2, 3] → length 4
     *
     * KEY INSIGHT - CHOICE AT EACH ELEMENT:
     * --------------------------------------
     * At each index, we have a DECISION to make:
     *   1. TAKE: Include current element in subsequence (if valid)
     *   2. SKIP: Don't include current element
     *
     * We can only TAKE if:
     *   - This is the first element (prev == -1), OR
     *   - Current element > previous element (nums[idx] > nums[prev])
     *
     * DP STATE MEANING:
     * -----------------
     * dp[idx][prev] = Length of longest increasing subsequence starting from
     *                 index 'idx', where 'prev' is the index of the last
     *                 element we included in the subsequence
     *
     * - idx: Current index we're considering (0 to n-1)
     * - prev: Index of previous element we took (-1 if none taken yet)
     *
     * TRICKY PART - HANDLING prev = -1:
     * ----------------------------------
     * prev can be -1 (no previous element) or 0 to n-1 (valid indices)
     *
     * Problem: Can't use dp[idx][-1] → negative index!
     *
     * Solution: COORDINATE SHIFT
     *   - Store prev+1 in DP table
     *   - When prev=-1: store at dp[idx][0]
     *   - When prev=0: store at dp[idx][1]
     *   - When prev=k: store at dp[idx][k+1]
     *
     * This is why:
     *   - DP size: dp[n][n+1] (extra column for prev=-1)
     *   - Access: dp[idx][prev+1] (shift by 1)
     *
     * ==================== MEMOIZATION APPROACH ====================
     */

    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;

        // dp[idx][prev+1]
        //   idx: current index (0 to n-1)
        //   prev+1: previous index shifted by 1 (0 to n)
        //     prev=-1 → store at column 0
        //     prev=0  → store at column 1
        //     prev=k  → store at column k+1
        int[][] dp = new int[n][n+1];

        for(int[] row : dp){
            Arrays.fill(row, -1);  // -1 means uncomputed
        }

        // Start: index 0, no previous element (prev = -1)
        return longest(0, -1, nums, dp);
    }

    private static int longest(int idx, int prev, int[] nums, int[][] dp) {
        /*
         * BASE CASE: Reached end of array (idx == nums.length)
         * -----------------------------------------------------
         * No more elements to consider → subsequence length = 0
         */
        if(idx == nums.length) return 0;

        /*
         * MEMOIZATION CHECK:
         * ------------------
         * Use prev+1 to shift index (handle prev=-1)
         */
        if(dp[idx][prev + 1] != -1) return dp[idx][prev+1];

        int len;

        /*
         * CHOICE 1: SKIP current element
         * -------------------------------
         * Don't include nums[idx] in subsequence
         * - Move to next index
         * - Previous element remains same
         * - This choice is ALWAYS available
         */
        len = longest(idx + 1, prev, nums, dp);

        /*
         * CHOICE 2: TAKE current element (if valid)
         * ------------------------------------------
         * Include nums[idx] in subsequence
         *
         * CONDITION: Can only take if:
         *   1. prev == -1: First element (no constraint)
         *   2. nums[idx] > nums[prev]: Maintains increasing property
         */
        if(prev == -1 || nums[idx] > nums[prev]){
            // Take current element:
            //   - Add 1 to length (included this element)
            //   - Move to next index (idx+1)
            //   - Update prev to current idx (this becomes new prev)
            len = Math.max(len, 1 + longest(idx + 1, idx, nums, dp));
        }

        /*
         * Store result with coordinate shift (prev+1) and return
         */
        return dp[idx][prev+1] = len;
    }

    /*
     * DETAILED EXAMPLE WALKTHROUGH:
     * ------------------------------
     * nums = [10, 9, 2, 5, 3, 7]
     *
     * Call: longest(0, -1) - Start at index 0, no previous
     *
     *   ├─ SKIP 10: longest(1, -1)
     *   │    ├─ SKIP 9: longest(2, -1)
     *   │    │    ├─ SKIP 2: longest(3, -1)
     *   │    │    │    └─ ...
     *   │    │    └─ TAKE 2: 1 + longest(3, 2)
     *   │    │         ├─ SKIP 5: longest(4, 2)
     *   │    │         └─ TAKE 5: 1 + longest(4, 3) [5 > 2 ✓]
     *   │    │              ├─ SKIP 3: longest(5, 3)
     *   │    │              └─ TAKE 3: Can't! [3 < 5 ✗]
     *   │    │                   But can take 7:
     *   │    │                   SKIP 3, TAKE 7: 1 + longest(6, 5) [7 > 5 ✓]
     *   │    │                                    = 1 + 0 = 1
     *   │    │         Path: [2, 5, 7] = length 3
     *   │    │
     *   │    └─ TAKE 9: 1 + longest(3, 1)
     *   │         └─ Can't take 2 [2 < 9], skip...
     *   │              Eventually finds shorter subsequence
     *   │
     *   └─ TAKE 10: 1 + longest(1, 0)
     *        └─ Can't take 9 [9 < 10], can't take 2 [2 < 10]
     *             Eventually finds shorter subsequence
     *
     * Best path found: [2, 5, 7] or [2, 3, 7] → length 4
     *
     * Wait, let me recount: [2, 5, 7] is length 3, not 4.
     * Actually for [10, 9, 2, 5, 3, 7]:
     *   Best is [2, 5, 7] = 3 or [2, 3, 7] = 3
     *
     * For [10, 9, 2, 5, 3, 7, 101, 18]:
     *   Best is [2, 5, 7, 101] = 4 or [2, 5, 7, 18] = 4
     *
     * WHY prev+1 IN DP TABLE?
     * -----------------------
     *
     * Without shift (WRONG):
     *   dp[idx][-1] → Array index error! ✗
     *
     * With shift (CORRECT):
     *   prev = -1 → dp[idx][0]   ✓
     *   prev = 0  → dp[idx][1]   ✓
     *   prev = 1  → dp[idx][2]   ✓
     *   prev = k  → dp[idx][k+1] ✓
     *
     * This is a common technique: COORDINATE TRANSFORMATION
     * to handle edge cases like -1 indices.
     *
     * RECURRENCE RELATION:
     * --------------------
     *
     * Base case:
     *   dp[n][*] = 0  (no more elements)
     *
     * Recurrence:
     *   dp[idx][prev] = max(
     *     longest(idx+1, prev),                    // SKIP
     *     1 + longest(idx+1, idx) if can take      // TAKE
     *   )
     *
     * Can take if:
     *   prev == -1  OR  nums[idx] > nums[prev]
     *
     * DP TABLE STRUCTURE:
     * -------------------
     *
     * For nums = [10, 9, 2, 5, 3, 7]:
     *
     *         prev→  -1   0   1   2   3   4   5
     *               (none)(10)(9)(2)(5)(3)(7)
     *    idx↓
     *     0 (10)     ?    -   -   -   -   -   -
     *     1 (9)      ?    ?   -   -   -   -   -
     *     2 (2)      ?    ?   ?   -   -   -   -
     *     3 (5)      ?    ?   ?   ?   -   -   -
     *     4 (3)      ?    ?   ?   ?   ?   -   -
     *     5 (7)      ?    ?   ?   ?   ?   ?   -
     *     6 (end)    0    0   0   0   0   0   0
     *
     * Each cell represents: "LIS length from idx with prev"
     *
     * TIME COMPLEXITY: O(n²)
     *   - Total states: n × n = O(n²)
     *   - Each state computed once: O(1) work per state
     *   - Total: O(n²)
     *
     * SPACE COMPLEXITY: O(n²)
     *   - DP table: O(n × n)
     *   - Recursion stack: O(n)
     *   - Total: O(n²)
     *
     * ALTERNATIVE APPROACHES:
     * -----------------------
     *
     * 1. TABULATION (1D DP) - O(n²):
     *
     * public static int lengthOfLIS(int[] nums) {
     *     int n = nums.length;
     *     int[] dp = new int[n];  // dp[i] = LIS ending at index i
     *     Arrays.fill(dp, 1);     // Each element is subsequence of length 1
     *
     *     int maxLen = 1;
     *
     *     for (int i = 1; i < n; i++) {
     *         for (int j = 0; j < i; j++) {
     *             if (nums[i] > nums[j]) {
     *                 dp[i] = Math.max(dp[i], dp[j] + 1);
     *             }
     *         }
     *         maxLen = Math.max(maxLen, dp[i]);
     *     }
     *
     *     return maxLen;
     * }
     *
     * This is simpler and more common!
     * dp[i] = Length of LIS ENDING at index i
     *
     *
     * 2. BINARY SEARCH (OPTIMAL) - O(n log n):
     *
     * public static int lengthOfLIS(int[] nums) {
     *     List<Integer> tails = new ArrayList<>();
     *
     *     for (int num : nums) {
     *         int pos = Collections.binarySearch(tails, num);
     *         if (pos < 0) pos = -(pos + 1);  // Insert position
     *
     *         if (pos == tails.size()) {
     *             tails.add(num);  // Extend sequence
     *         } else {
     *             tails.set(pos, num);  // Replace to keep smallest tail
     *         }
     *     }
     *
     *     return tails.size();
     * }
     *
     * Uses "patience sorting" technique
     * Maintains array of smallest tails for each length
     *
     * COMPARISON OF APPROACHES:
     * -------------------------
     *
     * 2D DP (Current approach):
     *   Time: O(n²), Space: O(n²)
     *   Pros: Clear state representation
     *   Cons: More space, less common
     *
     * 1D DP (Simpler):
     *   Time: O(n²), Space: O(n)
     *   Pros: Standard approach, easier to understand
     *   Cons: Still quadratic time
     *
     * Binary Search (Optimal):
     *   Time: O(n log n), Space: O(n)
     *   Pros: Fastest, optimal complexity
     *   Cons: Harder to understand, less intuitive
     *
     * KEY INSIGHTS:
     * -------------
     *
     * 1. COORDINATE SHIFT: prev+1 handles negative index elegantly
     *    → Common technique for boundary values
     *
     * 2. TWO CHOICES: SKIP (always) or TAKE (conditional)
     *    → Take only if maintains increasing property
     *
     * 3. PREV TRACKING: Need to track previous element
     *    → Ensures we can validate increasing constraint
     *
     * 4. MULTIPLE APPROACHES: Same problem, different DP formulations
     *    → 2D approach tracks (idx, prev)
     *    → 1D approach tracks "LIS ending at i"
     *    → Both valid, different perspectives!
     *
     * 5. OPTIMAL EXISTS: Binary search gives O(n log n)
     *    → But DP approaches are more intuitive for learning
     *
     * EDGE CASES:
     * -----------
     *
     * 1. Single element: [5]
     *    → LIS = [5], length = 1
     *
     * 2. All decreasing: [5, 4, 3, 2, 1]
     *    → LIS = any single element, length = 1
     *
     * 3. All increasing: [1, 2, 3, 4, 5]
     *    → LIS = entire array, length = 5
     *
     * 4. All same: [5, 5, 5, 5]
     *    → LIS = any single element, length = 1
     *    → (strictly increasing, so can't include duplicates)
     *
     * 5. Empty array: []
     *    → LIS = [], length = 0 (base case handles this)
     *
     * RELATED PROBLEMS:
     * -----------------
     *
     * - Longest Increasing Subsequence II (with constraints)
     * - Number of Longest Increasing Subsequences
     * - Maximum Length of Pair Chain
     * - Russian Doll Envelopes (2D LIS)
     * - Longest Divisible Subset
     *
     * MENTAL MODEL:
     * -------------
     * Think of building a sequence character by character:
     *   - At each position, ask: "Can I add this to my sequence?"
     *   - If yes: Consider both adding and skipping
     *   - If no: Must skip
     *   - Track what we previously added (prev) to validate
     *
     * The prev parameter is like "memory" of our sequence so far,
     * allowing us to enforce the increasing constraint!
     *
     * This is a CLASSIC DP problem and foundational for understanding
     * subsequence problems with constraints!
     */

    /*
     * LONGEST INCREASING SUBSEQUENCE (LIS) - ALL APPROACHES
     *
     * We've seen the MEMOIZATION approach. Now let's explore:
     * 1. Tabulation (2D DP)
     * 2. Space Optimization (1D DP from 2D)
     * 3. Better Tabulation (Different DP definition - MOST COMMON!)
     *
     * ==================== APPROACH 1: TABULATION (2D DP) ====================
     */

    public static int lengthOfLIS1(int[] nums) {
        int n = nums.length;

        // dp[idx][prev+1] - same as memoization
        // Size: (n+1) × (n+1)
        int[][] dp = new int[n+1][n+1];

        /*
         * BASE CASE:
         * ----------
         * dp[n][*] = 0 (no more elements, LIS length = 0)
         * Java initializes to 0 automatically!
         */

        /*
         * FILL DP TABLE (Bottom-up):
         * ---------------------------
         * Process in REVERSE order:
         *   - idx: n-1 down to 0 (process elements right to left)
         *   - prev: idx-1 down to -1 (only valid prev values)
         *
         * WHY prev from idx-1 to -1?
         *   - prev must be BEFORE current idx
         *   - prev can be any element from 0 to idx-1, or -1 (none)
         *   - No point considering prev >= idx (future elements)
         */
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int prev = idx - 1; prev >= -1; prev--) {
                int len;

                /*
                 * CHOICE 1: SKIP current element
                 * -------------------------------
                 * Don't include nums[idx]
                 * - Move to next index: idx+1
                 * - Keep same prev
                 */
                len = dp[idx + 1][prev + 1];

                /*
                 * CHOICE 2: TAKE current element (if valid)
                 * ------------------------------------------
                 * Include nums[idx] if:
                 *   - prev == -1 (first element), OR
                 *   - nums[idx] > nums[prev] (maintains increasing)
                 */
                if(prev == -1 || nums[idx] > nums[prev]){
                    // Take: add 1, update prev to idx
                    len = Math.max(len, 1 + dp[idx + 1][idx + 1]);
                }

                // Store result with coordinate shift (prev+1)
                dp[idx][prev + 1] = len;
            }
        }

        // Answer: Start at index 0, no previous element (prev=-1 → column 0)
        return dp[0][0];
    }

    /*
     * DP TABLE VISUALIZATION:
     * ------------------------
     * nums = [10, 9, 2, 5]
     *
     *         prev→  -1   0   1   2   3
     *               (none)(10)(9)(2)(5)
     *    idx↓
     *     0 (10)     2    -   -   -   -   ← Can take 10, then find LIS from rest
     *     1 (9)      2    2   -   -   -   ← Can take 9 or skip
     *     2 (2)      2    2   2   -   -   ← 2 < 10, 2 < 9, can take 2
     *     3 (5)      1    1   1   1   -   ← Just 5 by itself
     *     4 (end)    0    0   0   0   0   ← Base case
     *
     * Reading dp[0][0] (idx=0, prev=-1):
     *   Best LIS starting from index 0 with no previous = 2
     *   Example: [2, 5] or [9, 10]... wait that doesn't work.
     *
     * Actually for [10, 9, 2, 5]:
     *   Best LIS: [2, 5] = length 2
     *
     * TIME COMPLEXITY: O(n²)
     *   - Outer loop: n iterations
     *   - Inner loop: up to n iterations (prev from idx-1 to -1)
     *   - Total: O(n²)
     *
     * SPACE COMPLEXITY: O(n²)
     *   - 2D DP table: (n+1) × (n+1)
     *
     * ==================== APPROACH 2: SPACE OPTIMIZATION ====================
     *
     * OBSERVATION:
     * ------------
     * dp[idx] only depends on dp[idx+1]
     * → We only need to store "next row" values
     * → Can reduce from 2D to 1D array
     */

    public static int lengthOfLIS2(int[] nums) {
        int n = nums.length;

        // Store "next row" values: dp[prev+1]
        // Size: n+1 (for prev from -1 to n-1)
        int[] dp = new int[n+1];

        // Base case: dp already initialized to 0

        /*
         * ITERATE BACKWARDS:
         * ------------------
         * For each idx, compute current row based on "next row" (dp)
         */
        for (int idx = n - 1; idx >= 0; idx--) {
            // Current row values
            int[] curr = new int[n+1];

            for (int prev = idx - 1; prev >= -1; prev--) {
                int len;

                // SKIP: Use next row value
                len = dp[prev + 1];

                // TAKE: If valid, use next row with updated prev
                if(prev == -1 || nums[idx] > nums[prev]){
                    len = Math.max(len, 1 + dp[idx + 1]);
                }

                curr[prev + 1] = len;
            }

            // Move current to next (for next iteration)
            dp = curr;
        }

        // After all iterations, dp contains row 0 values
        // Answer: dp[0] (prev=-1 shifted to index 0)
        return dp[0];
    }

    /*
     * SPACE OPTIMIZATION VISUALIZATION:
     * ----------------------------------
     *
     * Traditional 2D DP:
     *   dp[n+1][n+1] → Space = O(n²)
     *
     * Space Optimized:
     *   Only store "next row" → dp[n+1] → Space = O(n)
     *
     * How it works:
     *   idx=n-1: Compute curr based on dp (row n values)
     *            dp = curr (shift: curr becomes next)
     *   idx=n-2: Compute curr based on dp (row n-1 values)
     *            dp = curr
     *   ...
     *   idx=0:   Compute curr based on dp (row 1 values)
     *            dp = curr → Contains row 0 values (answer!)
     *
     * TIME COMPLEXITY: O(n²) - same as before
     * SPACE COMPLEXITY: O(n) - only two 1D arrays
     *
     * ==================== APPROACH 3: BETTER TABULATION (1D DP) ====================
     *
     * DIFFERENT DP DEFINITION (Most Common Approach):
     * ------------------------------------------------
     * Instead of tracking (idx, prev), we use:
     *
     * dp[i] = Length of longest increasing subsequence ENDING AT index i
     *
     * KEY INSIGHT:
     * ------------
     * For each position i, check ALL previous positions j < i:
     *   - If nums[j] < nums[i]: We can extend the LIS ending at j
     *   - dp[i] = max(dp[i], dp[j] + 1)
     *
     * This is MUCH simpler and more intuitive!
     */

    public static int lengthOfLIS4(int[] nums) {
        int n = nums.length;

        /*
         * DP DEFINITION:
         * --------------
         * dp[i] = Length of LIS ENDING at index i
         *
         * Base case: Each element by itself is LIS of length 1
         */
        int[] dp = new int[n];
        Arrays.fill(dp, 1);  // Every element is a subsequence of length 1

        // Track global maximum LIS length
        int len = 1;

        /*
         * BUILD DP TABLE:
         * ---------------
         * For each position i (left to right):
         */
        for (int idx = 0; idx < n; idx++) {
            /*
             * Check all previous positions:
             * -----------------------------
             * For each j < idx, if we can extend the LIS ending at j:
             */
            for (int prev = 0; prev < idx; prev++) {
                /*
                 * Can extend if nums[prev] < nums[idx]
                 * (maintaining strictly increasing property)
                 */
                if(nums[prev] < nums[idx]){
                    // Option: Extend LIS ending at prev
                    // New length: dp[prev] + 1 (add current element)
                    dp[idx] = Math.max(dp[idx], 1 + dp[prev]);
                }
            }

            // Update global maximum
            len = Math.max(len, dp[idx]);
        }

        return len;
    }

    /*
     * DETAILED EXAMPLE WALKTHROUGH (Better Approach):
     * ------------------------------------------------
     * nums = [10, 9, 2, 5, 3, 7, 101, 18]
     *
     * Initial: dp = [1, 1, 1, 1, 1, 1, 1, 1]
     *          (each element is LIS of length 1)
     *
     * idx=0 (10): No previous elements
     *   dp[0] = 1
     *
     * idx=1 (9): Check j=0 (10)
     *   9 < 10? No, can't extend
     *   dp[1] = 1
     *
     * idx=2 (2): Check j=0,1
     *   2 < 10? Yes! dp[2] = max(1, 1+1) = 2... wait no
     *   2 < 10? No (we need nums[prev] < nums[idx], not >)
     *   dp[2] = 1
     *
     * idx=3 (5): Check j=0,1,2
     *   5 < 10? No
     *   5 < 9? No
     *   5 > 2? Yes! dp[3] = max(1, 1+1) = 2
     *   dp[3] = 2  [subsequence: 2,5]
     *
     * idx=4 (3): Check j=0,1,2,3
     *   3 < 10? No
     *   3 < 9? No
     *   3 > 2? Yes! dp[4] = max(1, 1+1) = 2
     *   3 < 5? No (can't extend dp[3])
     *   dp[4] = 2  [subsequence: 2,3]
     *
     * idx=5 (7): Check j=0,1,2,3,4
     *   7 < 10? No
     *   7 < 9? No
     *   7 > 2? Yes! dp[5] = max(1, 1+1) = 2
     *   7 > 5? Yes! dp[5] = max(2, 2+1) = 3
     *   7 > 3? Yes! dp[5] = max(3, 2+1) = 3
     *   dp[5] = 3  [subsequence: 2,5,7 or 2,3,7]
     *
     * idx=6 (101): Check all previous
     *   101 > 7? Yes! dp[6] = max(1, 3+1) = 4
     *   (Can extend any previous subsequence)
     *   dp[6] = 4  [subsequence: 2,5,7,101]
     *
     * idx=7 (18): Check all previous
     *   18 > 7? Yes! dp[7] = max(1, 3+1) = 4
     *   18 < 101? Yes but we check nums[prev] < nums[idx]
     *   dp[7] = 4  [subsequence: 2,5,7,18]
     *
     * Final dp: [1, 1, 1, 2, 2, 3, 4, 4]
     * Maximum = 4
     *
     * DP ARRAY VISUALIZATION:
     * ------------------------
     * nums: [10, 9, 2, 5, 3, 7, 101, 18]
     * dp:   [ 1, 1, 1, 2, 2, 3,  4,   4]
     *         ↑  ↑  ↑  ↑  ↑  ↑   ↑    ↑
     *        [10][9][2][2,5][2,3][2,5,7][2,5,7,101][2,5,7,18]
     *
     * Each dp[i] represents the length of LIS ending at that position.
     *
     * TIME COMPLEXITY: O(n²)
     *   - Outer loop: n iterations
     *   - Inner loop: up to n iterations
     *   - Total: O(n²)
     *
     * SPACE COMPLEXITY: O(n)
     *   - 1D DP array: O(n)
     *   - Much better than 2D approaches!
     *
     * WHY THIS APPROACH IS BETTER:
     * -----------------------------
     *
     * 2D Approaches (prev tracking):
     *   ✓ Shows decision process clearly
     *   ✗ More complex state
     *   ✗ O(n²) space (or O(n) with optimization)
     *   ✗ Harder to understand
     *
     * 1D Approach (ending at i):
     *   ✓ Simpler DP definition
     *   ✓ More intuitive
     *   ✓ O(n) space
     *   ✓ Standard interview approach
     *   ✓ Easier to code
     *
     * COMPARISON OF ALL APPROACHES:
     * ------------------------------
     *
     * | Approach          | Time    | Space | Complexity | Common? |
     * |-------------------|---------|-------|------------|---------|
     * | 2D Memoization    | O(n²)   | O(n²) | Medium     | Rare    |
     * | 2D Tabulation     | O(n²)   | O(n²) | Medium     | Rare    |
     * | Space Optimized   | O(n²)   | O(n)  | Medium     | Rare    |
     * | 1D Tabulation     | O(n²)   | O(n)  | Simple     | Common! |
     * | Binary Search     | O(nlogn)| O(n)  | Hard       | Optimal |
     *
     * RECOVERING THE ACTUAL SUBSEQUENCE:
     * -----------------------------------
     *
     * The 1D DP gives us the LENGTH. To get actual subsequence:
     *
     * public static List<Integer> getLIS(int[] nums) {
     *     int n = nums.length;
     *     int[] dp = new int[n];
     *     int[] parent = new int[n];  // Track previous element
     *     Arrays.fill(dp, 1);
     *     Arrays.fill(parent, -1);
     *
     *     int maxLen = 1, maxIdx = 0;
     *
     *     for (int i = 0; i < n; i++) {
     *         for (int j = 0; j < i; j++) {
     *             if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
     *                 dp[i] = dp[j] + 1;
     *                 parent[i] = j;  // Track where we came from
     *             }
     *         }
     *         if (dp[i] > maxLen) {
     *             maxLen = dp[i];
     *             maxIdx = i;  // End of longest LIS
     *         }
     *     }
     *
     *     // Reconstruct LIS by following parent pointers
     *     List<Integer> lis = new ArrayList<>();
     *     while (maxIdx != -1) {
     *         lis.add(nums[maxIdx]);
     *         maxIdx = parent[maxIdx];
     *     }
     *     Collections.reverse(lis);
     *
     *     return lis;
     * }
     *
     * KEY INSIGHTS:
     * -------------
     *
     * 1. MULTIPLE DP FORMULATIONS: Same problem, different states
     *    → (idx, prev) vs "ending at i"
     *    → Both correct, different perspectives
     *
     * 2. SIMPLER IS BETTER: 1D approach is standard for a reason
     *    → Easier to understand and implement
     *    → Less space, same time complexity
     *
     * 3. DP DEFINITION MATTERS: "Ending at i" is more natural
     *    → Subproblem: "Best LIS using elements up to i"
     *    → Builds solution incrementally
     *
     * 4. SPACE OPTIMIZATION: Can reduce O(n²) → O(n)
     *    → When current state depends only on previous row
     *    → Common technique in DP
     *
     * 5. OPTIMAL EXISTS: Binary search achieves O(n log n)
     *    → But O(n²) DP is more intuitive for learning
     *    → Know both for interviews!
     *
     * FOR INTERVIEWS:
     * ---------------
     * - Start with 1D DP approach (lengthOfLIS4)
     * - Explain clearly: "dp[i] = LIS ending at i"
     * - Mention O(n log n) solution exists if asked
     * - Can reconstruct actual sequence if needed
     *
     * This is one of the most FUNDAMENTAL DP problems!
     * Master this, and many other subsequence problems become easier!
     */

    // Printing LIS
    public static void lengthOfLIS5(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);
        int len = 1;
        int lastIndex = 0;
        for (int idx = 0; idx < n; idx++) {
            hash[idx] = idx;
            for (int prev = 0; prev < idx; prev++) {
                if(nums[prev] < nums[idx] && dp[idx] < 1 + dp[prev]){
                    dp[idx] = 1 + dp[prev];
                    hash[idx] = prev;
                }
            }
            if(dp[idx] > len){
                len = dp[idx];
                lastIndex = idx;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(nums[lastIndex]);

        while (hash[lastIndex] != lastIndex){
            lastIndex = hash[lastIndex];
            ans.add(0,nums[lastIndex]);
        }

        System.out.println(ans);
    }
}

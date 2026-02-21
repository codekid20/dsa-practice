package DynammicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    public static void main(String[] args) {
        int[] nums = {1};

        System.out.println(largestDivisibleSubset(nums));
    }

    /*
     * LARGEST DIVISIBLE SUBSET
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given a set of DISTINCT positive integers, find the LARGEST subset such that
     * every pair of elements (Si, Sj) in the subset satisfies:
     *   - Si % Sj == 0, OR
     *   - Sj % Si == 0
     *
     * In other words, one element must be divisible by the other.
     *
     * Example:
     *   nums = [1, 2, 3]
     *   Output: [1, 2] or [1, 3]
     *
     *   Why [1,2]? 2 % 1 == 0 ✓
     *   Why [1,3]? 3 % 1 == 0 ✓
     *   Why not [2,3]? 3 % 2 != 0 and 2 % 3 != 0 ✗
     *   Why not [1,2,3]? 3 % 2 != 0 ✗
     *
     * Example 2:
     *   nums = [1, 2, 4, 8]
     *   Output: [1, 2, 4, 8]
     *
     *   Valid because: 2%1==0, 4%2==0, 8%4==0
     *   Forms a divisibility chain!
     *
     * KEY INSIGHT - SORTING + DIVISIBILITY PROPERTY:
     * -----------------------------------------------
     *
     * CRUCIAL OBSERVATION:
     * If we SORT the array, and if a < b < c, then:
     *   - If c % b == 0 AND b % a == 0
     *   - Then c % a == 0 (transitivity!)
     *
     * Proof:
     *   b = k₁ × a  (b is divisible by a)
     *   c = k₂ × b  (c is divisible by b)
     *   → c = k₂ × (k₁ × a) = (k₂ × k₁) × a
     *   → c % a == 0 ✓
     *
     * This means: We only need to check if nums[idx] is divisible by nums[prev]!
     *   - Don't need to check ALL pairs in the subset
     *   - Just check consecutive pairs in sorted order
     *
     * TRANSFORMATION TO LIS:
     * ----------------------
     * This problem becomes EXACTLY like LIS, but with a different condition:
     *
     * LIS: nums[idx] > nums[prev]  (increasing)
     * LDS: nums[idx] % nums[prev] == 0  (divisible)
     *
     * Same DP structure, just different validity check!
     *
     * DP DEFINITION:
     * --------------
     * dp[i] = Length of largest divisible subset ENDING at index i
     *
     * - After sorting, we build subsets left to right
     * - For each position i, check all j < i
     * - If nums[i] % nums[j] == 0, we can extend subset ending at j
     *
     * RECONSTRUCTION:
     * ---------------
     * Unlike LIS where we only need LENGTH, here we need the ACTUAL subset.
     *
     * We use a HASH/PARENT array:
     *   hash[i] = Index of previous element in the subset
     *
     * This allows us to BACKTRACK from the end to reconstruct the subset!
     *
     * ==================== SOLUTION WITH RECONSTRUCTION ====================
     */

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;

        /*
         * DP ARRAYS:
         * ----------
         * dp[i]: Length of largest divisible subset ending at index i
         * hash[i]: Index of previous element in the subset (for reconstruction)
         */
        int[] dp = new int[n];
        int[] hash = new int[n];

        // Initialize: Each element forms subset of length 1
        Arrays.fill(dp, 1);

        // Track global maximum
        int len = 1;        // Maximum subset length found
        int lastIndex = 0;  // Index where maximum subset ends

        /*
         * CRITICAL: SORT THE ARRAY
         * -------------------------
         * Sorting ensures divisibility transitivity:
         *   If sorted: a < b < c
         *   And c%b==0, b%a==0
         *   Then c%a==0 automatically!
         *
         * This allows us to only check consecutive pairs!
         */
        Arrays.sort(nums);

        /*
         * BUILD DP TABLE:
         * ---------------
         * For each position idx (left to right in sorted array):
         */
        for (int idx = 0; idx < n; idx++) {
            // Initially, subset ending at idx contains only itself
            hash[idx] = idx;  // Points to itself (no previous element)

            /*
             * CHECK ALL PREVIOUS POSITIONS:
             * -----------------------------
             * For each prev < idx, check if we can extend subset ending at prev
             */
            for (int prev = 0; prev < idx; prev++) {
                /*
                 * DIVISIBILITY CHECK:
                 * -------------------
                 * Can extend subset ending at prev if:
                 *   1. nums[idx] % nums[prev] == 0 (divisible)
                 *   2. Extending gives longer subset: 1 + dp[prev] > dp[idx]
                 */
                if(nums[idx] % nums[prev] == 0 && dp[idx] < 1 + dp[prev]){
                    // Extend subset: new length = dp[prev] + 1
                    dp[idx] = 1 + dp[prev];

                    // Track previous element for reconstruction
                    hash[idx] = prev;
                }
            }

            /*
             * UPDATE GLOBAL MAXIMUM:
             * ----------------------
             * If current subset is longest found so far, update tracking
             */
            if(dp[idx] > len){
                len = dp[idx];
                lastIndex = idx;  // Remember where longest subset ends
            }
        }

        /*
         * RECONSTRUCT THE SUBSET:
         * -----------------------
         * Start from lastIndex and follow hash pointers backwards
         */
        ArrayList<Integer> ans = new ArrayList<>();

        // Add the last element of the subset
        ans.add(nums[lastIndex]);

        /*
         * BACKTRACK USING HASH ARRAY:
         * ----------------------------
         * hash[i] points to the previous element in the subset
         * When hash[i] == i, we've reached the start of the subset
         */
        while (hash[lastIndex] != lastIndex){
            lastIndex = hash[lastIndex];  // Move to previous element
            ans.add(0, nums[lastIndex]);   // Add to front of list
        }

        return ans;
    }

    /*
     * DETAILED EXAMPLE WALKTHROUGH:
     * ------------------------------
     * nums = [1, 2, 4, 8, 16]
     *
     * After sorting: [1, 2, 4, 8, 16] (already sorted)
     *
     * Initial state:
     *   dp   = [1, 1, 1, 1, 1]
     *   hash = [0, 1, 2, 3, 4] (each points to itself)
     *
     * idx=0 (nums[0]=1):
     *   No previous elements
     *   dp[0] = 1, hash[0] = 0
     *
     * idx=1 (nums[1]=2):
     *   Check prev=0 (nums[0]=1):
     *     2 % 1 == 0 ✓ AND 1 + dp[0] > dp[1] (1+1 > 1) ✓
     *     dp[1] = 2, hash[1] = 0
     *
     *   Current: dp   = [1, 2, 1, 1, 1]
     *            hash = [0, 0, 2, 3, 4]
     *   len = 2, lastIndex = 1
     *
     * idx=2 (nums[2]=4):
     *   Check prev=0 (nums[0]=1):
     *     4 % 1 == 0 ✓ AND 1 + 1 > 1 ✓
     *     dp[2] = 2, hash[2] = 0
     *
     *   Check prev=1 (nums[1]=2):
     *     4 % 2 == 0 ✓ AND 1 + dp[1] > dp[2] (1+2 > 2) ✓
     *     dp[2] = 3, hash[2] = 1  ← Better! Extend from 2
     *
     *   Current: dp   = [1, 2, 3, 1, 1]
     *            hash = [0, 0, 1, 3, 4]
     *   len = 3, lastIndex = 2
     *
     * idx=3 (nums[3]=8):
     *   Check prev=0: 8%1==0, dp[3] = 2, hash[3] = 0
     *   Check prev=1: 8%2==0, 1+2 > 2, dp[3] = 3, hash[3] = 1
     *   Check prev=2: 8%4==0, 1+3 > 3, dp[3] = 4, hash[3] = 2 ← Best!
     *
     *   Current: dp   = [1, 2, 3, 4, 1]
     *            hash = [0, 0, 1, 2, 4]
     *   len = 4, lastIndex = 3
     *
     * idx=4 (nums[4]=16):
     *   Check all previous:
     *     16%1==0, 16%2==0, 16%4==0, 16%8==0
     *     Best: extend from idx=3 (length 4)
     *     dp[4] = 5, hash[4] = 3
     *
     *   Final: dp   = [1, 2, 3, 4, 5]
     *          hash = [0, 0, 1, 2, 3]
     *   len = 5, lastIndex = 4
     *
     * RECONSTRUCTION:
     * ---------------
     * Start at lastIndex = 4 (nums[4] = 16)
     *   ans = [16]
     *   hash[4] = 3 (3 != 4, continue)
     *
     * Move to lastIndex = 3 (nums[3] = 8)
     *   ans = [8, 16]
     *   hash[3] = 2 (2 != 3, continue)
     *
     * Move to lastIndex = 2 (nums[2] = 4)
     *   ans = [4, 8, 16]
     *   hash[2] = 1 (1 != 2, continue)
     *
     * Move to lastIndex = 1 (nums[1] = 2)
     *   ans = [2, 4, 8, 16]
     *   hash[1] = 0 (0 != 1, continue)
     *
     * Move to lastIndex = 0 (nums[0] = 1)
     *   ans = [1, 2, 4, 8, 16]
     *   hash[0] = 0 (0 == 0, STOP!)
     *
     * Final Answer: [1, 2, 4, 8, 16]
     *
     * VISUAL REPRESENTATION:
     * ----------------------
     *
     * nums:  [1,  2,  4,  8,  16]
     * dp:    [1,  2,  3,  4,  5]  ← Length of subset ending here
     * hash:  [0,  0,  1,  2,  3]  ← Previous element index
     *         ↑   ↑   ↑   ↑   ↑
     *         └───┘───┘───┘───┘
     *         Chain: 0→1→2→3→4
     *
     * WHY SORTING IS CRITICAL:
     * ------------------------
     *
     * WITHOUT sorting:
     *   nums = [4, 8, 2, 16]
     *
     *   At idx=3 (16): We'd check if 16%4, 16%8, 16%2
     *   We'd miss the optimal chain because elements are out of order!
     *
     * WITH sorting:
     *   nums = [2, 4, 8, 16]
     *
     *   At idx=3 (16): We check 16%2, 16%4, 16%8
     *   We find the optimal chain: 2→4→8→16
     *
     * Sorting ensures we build chains in increasing order,
     * leveraging transitivity of divisibility!
     *
     * TIME COMPLEXITY: O(n² + n log n)
     *   - Sorting: O(n log n)
     *   - DP: O(n²) - two nested loops
     *   - Reconstruction: O(n)
     *   - Total: O(n²)
     *
     * SPACE COMPLEXITY: O(n)
     *   - dp array: O(n)
     *   - hash array: O(n)
     *   - Result list: O(n)
     *   - Sorting: O(log n) stack space
     *   - Total: O(n)
     *
     * EDGE CASES:
     * -----------
     *
     * 1. Single element: [5]
     *    → Output: [5]
     *    → Every element forms subset of length 1
     *
     * 2. No divisibility: [2, 3, 5, 7]  (all primes)
     *    → Output: any single element, e.g., [2]
     *    → No pair is divisible
     *
     * 3. All divisible: [1, 2, 4, 8, 16]
     *    → Output: [1, 2, 4, 8, 16]
     *    → Perfect divisibility chain
     *
     * 4. Multiple chains: [1, 2, 3, 6]
     *    → Chains: [1,2,6] or [1,3,6]
     *    → Both have length 3
     *    → Either is valid answer
     *
     * 5. With 1: [1, 4, 5, 20]
     *    → Output: [1, 4, 20] or [1, 5, 20]
     *    → 1 divides everything, so it's always in longest subset
     *
     * COMPARISON WITH LIS:
     * --------------------
     *
     * Longest Increasing Subsequence:
     *   Condition: nums[idx] > nums[prev]
     *   Meaning: Strictly increasing
     *   Example: [1, 2, 5, 7]
     *
     * Largest Divisible Subset:
     *   Condition: nums[idx] % nums[prev] == 0
     *   Meaning: Divisibility
     *   Example: [1, 2, 4, 8]
     *
     * SAME DP STRUCTURE, DIFFERENT CONSTRAINT!
     *
     * KEY INSIGHTS:
     * -------------
     *
     * 1. SORTING ENABLES TRANSITIVITY:
     *    → If a|b and b|c, then a|c (in sorted order)
     *    → Only need to check consecutive pairs
     *    → Transforms complex constraint into simple check
     *
     * 2. HASH ARRAY FOR RECONSTRUCTION:
     *    → hash[i] stores parent/previous element
     *    → Allows backtracking to build actual subset
     *    → When hash[i] == i, reached start of chain
     *
     * 3. LIS PATTERN REUSE:
     *    → dp[i] = "best ending at i"
     *    → Check all previous, extend if valid
     *    → Track global maximum
     *    → Same pattern, different validity condition
     *
     * 4. MULTIPLE VALID ANSWERS:
     *    → Problem asks for largest subset (any valid one)
     *    → Multiple subsets can have same max length
     *    → Our algorithm returns one of them
     *
     * 5. MATHEMATICAL PROPERTY:
     *    → Divisibility is transitive (after sorting)
     *    → This property is KEY to O(n²) solution
     *    → Without it, would need to check all pairs: O(2ⁿ)
     *
     * RELATED PROBLEMS:
     * -----------------
     * - Longest Increasing Subsequence (same pattern)
     * - Longest Chain of Pairs (similar concept)
     * - Box Stacking Problem (3D version)
     * - Russian Doll Envelopes (2D version)
     *
     * INTERVIEW TIPS:
     * ---------------
     * 1. Immediately think: "Sort first!"
     * 2. Recognize LIS pattern
     * 3. Explain divisibility transitivity
     * 4. Mention hash array for reconstruction
     * 5. Walk through small example
     *
     * This problem beautifully demonstrates how SORTING can transform
     * a complex constraint into a simple, checkable condition!
     *
     * The combination of sorting + DP + reconstruction makes this
     * a CLASSIC interview problem!
     */
}

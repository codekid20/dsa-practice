package DynammicProgramming;

import java.util.Arrays;

public class BuyAndSellStock4 {
    public static void main(String[] args) {
        int k = 4;
        int[] prices = {1,2,4,2,5,7,2,4,9,0};

        System.out.println(maxProfit3(k, prices));
    }

    /*
     * BEST TIME TO BUY AND SELL STOCK IV (At Most K Transactions)
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given an array of stock prices and an integer k, find the MAXIMUM profit
     * you can achieve with at most K transactions.
     *
     * Constraints:
     *   - At most k transactions
     *   - Must SELL before you can BUY again (can't hold multiple stocks)
     *   - A transaction = 1 buy + 1 sell
     *
     * Example:
     *   k = 2, prices = [2, 4, 1]
     *
     *   Best strategy:
     *     - Transaction 1: Buy at 2, Sell at 4 → profit = 2
     *     - Can't do profitable second transaction
     *     - Total profit = 2
     *
     *   k = 2, prices = [3, 2, 6, 5, 0, 3]
     *
     *   Best strategy:
     *     - Transaction 1: Buy at 2, Sell at 6 → profit = 4
     *     - Transaction 2: Buy at 0, Sell at 3 → profit = 3
     *     - Total profit = 4 + 3 = 7
     *
     * KEY INSIGHT - GENERALIZATION OF STOCK III:
     * ------------------------------------------
     * This is the GENERALIZED version of Stock III!
     *
     * Stock I:   k = 1  (single transaction)
     * Stock III: k = 2  (two transactions)
     * Stock IV:  k = K  (k transactions) ← THIS PROBLEM!
     * Stock II:  k = ∞  (unlimited transactions)
     *
     * The SAME DP framework handles ALL stock problems!
     *
     * DP STATE:
     * ---------
     * dp[idx][buy][cap] = Maximum profit from day idx onwards,
     *                     with current state 'buy',
     *                     and 'cap' transactions remaining
     *
     * - idx: Current day (0 to n-1)
     * - buy: Current state (0 = can sell, 1 = can buy)
     * - cap: Remaining transactions (0 to k)
     *
     * The ONLY difference from Stock III is:
     *   Stock III: cap ranges from 0 to 2
     *   Stock IV:  cap ranges from 0 to k
     *
     * LITERALLY THE SAME LOGIC, JUST DIFFERENT RANGE!
     *
     * ==================== MEMOIZATION APPROACH ====================
     */

    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;

        // 3D DP table:
        // dp[idx][buy][cap]
        //   idx: 0 to n-1 (days)
        //   buy: 0 or 1 (can sell or can buy)
        //   cap: 0 to k (transactions remaining)
        int[][][] dp = new int[n][2][k+1];

        for (int[][] row : dp){
            for (int[] r : row){
                Arrays.fill(r, -1);  // -1 means uncomputed
            }
        }

        // Start: Day 0, CAN BUY (buy=1), k transactions remaining
        return profit(0, 1, k, prices, dp);
    }

    private static int profit(int idx, int buy, int cap, int[] prices, int[][][] dp) {
        /*
         * BASE CASE 1: No transactions remaining (cap == 0)
         * --------------------------------------------------
         * Used up all k transactions → can't trade anymore → return 0
         */
        if(cap == 0) return 0;

        /*
         * BASE CASE 2: No more days (idx == prices.length)
         * -------------------------------------------------
         * Processed all days → no more opportunities → return 0
         */
        if(idx == prices.length) return 0;

        // Return memoized result if already computed
        if(dp[idx][buy][cap] != -1) return dp[idx][buy][cap];

        int take;
        int notTake;

        /*
         * STATE 1: CAN BUY (buy == 1)
         * ----------------------------
         * Don't own stock → can BUY or SKIP
         */
        if(buy == 1){
            // Option A: BUY today
            // - Pay prices[idx]
            // - Move to CAN SELL state (buy = 0)
            // - cap stays SAME (transaction incomplete)
            take = -prices[idx] + profit(idx + 1, 0, cap, prices, dp);

            // Option B: SKIP today
            // - Stay in CAN BUY state
            notTake = profit(idx + 1, 1, cap, prices, dp);
        }
        /*
         * STATE 2: CAN SELL (buy == 0)
         * -----------------------------
         * Own stock → can SELL or HOLD
         */
        else {
            // Option A: SELL today
            // - Receive prices[idx]
            // - Move to CAN BUY state (buy = 1)
            // - cap DECREASES by 1 (transaction complete!)
            take = prices[idx] + profit(idx + 1, 1, cap - 1, prices, dp);

            // Option B: HOLD
            // - Stay in CAN SELL state
            notTake = profit(idx + 1, 0, cap, prices, dp);
        }

        // Store and return maximum profit
        return dp[idx][buy][cap] = Math.max(take, notTake);
    }

    /*
     * RECURRENCE RELATION (Same as Stock III, different k):
     * ------------------------------------------------------
     *
     * If cap == 0 or idx == n:
     *   dp[idx][buy][cap] = 0
     *
     * If buy == 1 (can buy):
     *   dp[idx][1][cap] = max(
     *     -prices[idx] + dp[idx+1][0][cap],    // buy today
     *     dp[idx+1][1][cap]                     // skip today
     *   )
     *
     * If buy == 0 (can sell):
     *   dp[idx][0][cap] = max(
     *     prices[idx] + dp[idx+1][1][cap-1],   // sell today (cap--)
     *     dp[idx+1][0][cap]                     // hold
     *   )
     *
     * ==================== TABULATION APPROACH ====================
     */

    public static int maxProfit1(int k, int[] prices) {
        int n = prices.length;

        // 3D DP table with size (n+1) × 2 × (k+1)
        int[][][] dp = new int[n+1][2][k+1];

        /*
         * BASE CASE:
         * ----------
         * dp[n][*][*] = 0 (no more days)
         * dp[*][*][0] = 0 (no transactions left)
         *
         * Java initializes to 0 automatically!
         */

        /*
         * FILL DP TABLE (Bottom-up):
         * ---------------------------
         * Process days in REVERSE (n-1 to 0)
         * For each day, try all buy states and cap values
         */
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {  // Skip cap=0 (always 0)
                    int take;
                    int notTake;

                    if(buy == 1){
                        // CAN BUY state
                        take = -prices[idx] + dp[idx + 1][0][cap];
                        notTake = dp[idx + 1][1][cap];
                    } else {
                        // CAN SELL state
                        take = prices[idx] + dp[idx + 1][1][cap - 1];
                        notTake = dp[idx + 1][0][cap];
                    }

                    dp[idx][buy][cap] = Math.max(take, notTake);
                }
            }
        }

        // Answer: Day 0, CAN BUY, k transactions remaining
        return dp[0][1][k];
    }

    /*
     * ==================== SPACE OPTIMIZATION ====================
     *
     * OBSERVATION:
     * ------------
     * dp[idx] only depends on dp[idx+1]
     * → Only need to store "next day" values
     * → Reduce from O(n × 2 × k) to O(2 × k)
     */

    public static int maxProfit2(int k, int[] prices) {
        int n = prices.length;

        // Store only "next day" values: dp[buy][cap]
        int[][] dp = new int[2][k+1];

        // Base case: already initialized to 0

        // Process days in reverse
        for (int idx = n - 1; idx >= 0; idx--) {
            // Current day's values
            int[][] curr = new int[2][k+1];

            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    int take;
                    int notTake;

                    if(buy == 1){
                        // CAN BUY: Use next day values from dp
                        take = -prices[idx] + dp[0][cap];
                        notTake = dp[1][cap];
                    } else {
                        // CAN SELL: Use next day values from dp
                        take = prices[idx] + dp[1][cap - 1];
                        notTake = dp[0][cap];
                    }

                    curr[buy][cap] = Math.max(take, notTake);
                }
            }

            // Move current to next (for next iteration)
            dp = curr;
        }

        // After all days, dp contains day 0 values
        return dp[1][k];
    }

    /*
     * COMPLEXITY ANALYSIS:
     * --------------------
     *
     * TIME COMPLEXITY: O(n × k)
     *   - n days
     *   - For each day: 2 buy states × k cap values = 2k iterations
     *   - Total: n × 2k = O(n × k)
     *
     * SPACE COMPLEXITY:
     *   - Memoization: O(n × 2 × k) + O(n) recursion stack = O(n × k)
     *   - Tabulation: O(n × 2 × k) = O(n × k)
     *   - Space Optimized: O(2 × k) = O(k)
     *
     * IMPORTANT OPTIMIZATION - WHEN K IS LARGE:
     * ------------------------------------------
     *
     * Special Case: If k >= n/2
     *   → We can do unlimited transactions!
     *   → Why? Maximum possible transactions = n/2 (buy-sell pairs)
     *   → If k >= n/2, it's equivalent to Stock II (unlimited)
     *
     * Optimized Solution:
     *
     * public static int maxProfit(int k, int[] prices) {
     *     int n = prices.length;
     *
     *     // If k >= n/2, use greedy approach (Stock II)
     *     if (k >= n / 2) {
     *         int profit = 0;
     *         for (int i = 1; i < n; i++) {
     *             if (prices[i] > prices[i-1]) {
     *                 profit += prices[i] - prices[i-1];
     *             }
     *         }
     *         return profit;
     *     }
     *
     *     // Otherwise, use DP approach
     *     return maxProfitDP(k, prices);
     * }
     *
     * This optimization is CRUCIAL for large k!
     * Time: O(n × k) → O(n) when k is large
     *
     * EXAMPLE WALKTHROUGH:
     * --------------------
     * k = 2, prices = [3, 2, 6, 5, 0, 3]
     *
     * Optimal Strategy:
     *   Transaction 1: Buy at 2, Sell at 6 → profit = 4
     *   Transaction 2: Buy at 0, Sell at 3 → profit = 3
     *   Total = 7
     *
     * DP Process:
     *   Start: profit(0, 1, 2) - Day 0, can buy, 2 transactions left
     *
     *   Explores paths like:
     *     - Skip day 0 (price=3)
     *     - Buy at day 1 (price=2)
     *     - Sell at day 2 (price=6) → cap becomes 1
     *     - Skip days 3,4
     *     - Buy at day 4 (price=0)
     *     - Sell at day 5 (price=3) → cap becomes 0
     *
     *   Maximum profit across all valid paths = 7
     *
     * EDGE CASES:
     * -----------
     *
     * 1. k = 0 (no transactions allowed)
     *    → Return 0
     *
     * 2. k = 1 (single transaction)
     *    → Same as Stock I
     *    → Can use simpler greedy: buy at min, sell at max after min
     *
     * 3. k >= n/2 (unlimited effectively)
     *    → Same as Stock II
     *    → Use greedy: sum all positive price differences
     *
     * 4. All prices decreasing: [5, 4, 3, 2, 1]
     *    → No profitable transactions possible
     *    → Return 0
     *
     * 5. All prices increasing: [1, 2, 3, 4, 5]
     *    → One transaction sufficient (buy at 1, sell at 5)
     *    → Return 4
     *    → Even if k > 1, only need 1 transaction for max profit
     *
     * 6. Empty array or single price
     *    → No transactions possible
     *    → Return 0
     *
     * COMPARISON: ALL STOCK PROBLEMS
     * -------------------------------
     *
     * Stock I (k=1):
     *   - Simple greedy: track min price, max profit
     *   - Time: O(n), Space: O(1)
     *
     * Stock II (k=∞):
     *   - Greedy: sum all upward movements
     *   - Time: O(n), Space: O(1)
     *   - OR DP with 2D state: dp[idx][buy]
     *
     * Stock III (k=2):
     *   - DP with 3D state: dp[idx][buy][cap] where cap ∈ {0,1,2}
     *   - Time: O(n), Space: O(1) with optimization
     *
     * Stock IV (k=K):
     *   - DP with 3D state: dp[idx][buy][cap] where cap ∈ {0,...,k}
     *   - Time: O(n × k), Space: O(k)
     *   - Optimize to O(n) when k >= n/2
     *
     * Stock with Cooldown:
     *   - DP with states: {buy, sell, cooldown}
     *   - Time: O(n), Space: O(1)
     *
     * Stock with Fee:
     *   - Similar to Stock II, subtract fee on each transaction
     *   - Time: O(n), Space: O(1)
     *
     * KEY INSIGHTS:
     * -------------
     *
     * 1. UNIFIED FRAMEWORK: Same DP approach works for k=1, k=2, k=K
     *    → Change only the range of cap variable
     *
     * 2. STATE DESIGN: Three dimensions capture all info
     *    → idx: which day
     *    → buy: current state (own stock or not)
     *    → cap: remaining transaction budget
     *
     * 3. TRANSACTION COUNTING: Count on SELL, not BUY
     *    → Intuitive: transaction "completes" when we cash out
     *
     * 4. OPTIMIZATION OPPORTUNITY: When k >= n/2
     *    → Switch to greedy O(n) approach
     *    → Huge speedup for large k
     *
     * 5. SPACE OPTIMIZATION: Only need previous day
     *    → O(n × k) → O(k) space
     *
     * 6. GENERALIZATION: This framework extends to other constraints
     *    → Add cooldown: add cooldown state
     *    → Add fee: subtract fee on sell
     *    → Multiple stocks: add stock index dimension
     *
     * MENTAL MODEL:
     * -------------
     * Think of it as a VIDEO GAME:
     *   - You have K "transaction tokens"
     *   - Each day, you can:
     *     → Use a token: buy → wait → sell (consumes 1 token)
     *     → Skip: do nothing (save tokens for later)
     *   - Goal: Maximize gold (profit) before tokens run out
     *
     * The DP explores ALL possible ways to use your tokens
     * across all days and finds the maximum profit path!
     *
     * REAL-WORLD APPLICATION:
     * -----------------------
     * - Portfolio management: Limited trades due to fees/regulations
     * - Day trading: PDT rule limits trades for accounts < $25k
     * - Algorithmic trading: Strategy testing with transaction limits
     * - Resource allocation: Limited actions in constrained systems
     *
     * This is one of the MOST ELEGANT generalizations in DP!
     * A single framework handles k=1, k=2, k=∞, and everything in between!
     */



    // Approach 2:
    // Using N * 4 DP
    public static int maxProfit3(int k, int[] prices) {
        int n = prices.length;
        int transAllowed = 2 * k;
        int[][] dp = new int[n][transAllowed];
        for (int[] row : dp){
            Arrays.fill(row, -1);

        }

        return profit4(0, 0, prices, dp, transAllowed);
    }


    private static int profit4(int idx, int trans, int[] prices, int[][] dp, int transAllowed) {

        if(idx == prices.length || trans == transAllowed) return 0;

        if(dp[idx][trans] != -1) return dp[idx][trans];
        int take;
        int notTake;
        if(trans % 2 == 0){
            take = -prices[idx] + profit4(idx + 1, trans + 1, prices, dp,transAllowed);
            notTake = profit4(idx + 1, trans, prices,dp, transAllowed);

        } else {
            take = prices[idx] + profit4(idx + 1, trans + 1, prices,dp, transAllowed);
            notTake = profit4(idx + 1, trans, prices,dp, transAllowed);

        }
        return dp[idx][trans] = Math.max(take, notTake);
    }
}

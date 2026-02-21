package DynammicProgramming;

import java.util.Arrays;

public class BuyAndSellStock3 {
    public static void main(String[] args) {
        int[] prices = {2,1,2,0,1};

        System.out.println(maxProfit5(prices));
    }

    /*
     * BEST TIME TO BUY AND SELL STOCK III (At Most 2 Transactions)
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given an array of stock prices, find the MAXIMUM profit you can achieve.
     * You can complete at most TWO transactions (2 buy-sell pairs).
     *
     * Constraints:
     *   - At most 2 transactions
     *   - Must SELL before you can BUY again (can't hold multiple stocks)
     *   - A transaction = 1 buy + 1 sell
     *
     * Example:
     *   prices = [3, 3, 5, 0, 0, 3, 1, 4]
     *
     *   Best strategy:
     *     - Transaction 1: Buy at 0, Sell at 3 → profit = 3
     *     - Transaction 2: Buy at 1, Sell at 4 → profit = 3
     *     - Total profit = 3 + 3 = 6
     *
     * KEY INSIGHT - 3D DP STATE:
     * --------------------------
     * This is similar to Stock II, but we need to TRACK REMAINING TRANSACTIONS!
     *
     * We now have THREE dimensions:
     *   1. idx: Current day (which day are we on?)
     *   2. buy: Current state (can buy=1 or can sell=0?)
     *   3. cap: Remaining transaction capacity (how many transactions left?)
     *
     * Think of 'cap' as your "transaction budget":
     *   - Start with cap = 2 (can do 2 transactions)
     *   - Each COMPLETED transaction decreases cap by 1
     *   - When cap = 0, no more transactions allowed
     *
     * IMPORTANT: When does cap decrease?
     * ----------------------------------
     * We decrease cap when we COMPLETE a transaction.
     * A transaction is completed when we SELL (not when we buy).
     *
     * Why count on SELL and not BUY?
     *   - Convention: A transaction is "done" when we cash out (sell)
     *   - Could also count on buy, but then initialization changes
     *   - Counting on sell is more intuitive: "2 sells left = 2 transactions left"
     *
     * DP STATE MEANING:
     * -----------------
     * dp[idx][buy][cap] = Maximum profit from day idx onwards,
     *                     with current state 'buy',
     *                     and 'cap' transactions remaining
     *
     * STATE MACHINE WITH TRANSACTION LIMIT:
     * -------------------------------------
     *
     *         ┌─────────────┐
     *         │   CAN BUY   │ (buy = 1, no stock, cap transactions left)
     *         │  (buy = 1)  │
     *         └─────────────┘
     *          ↓           ↑
     *       [BUY]      [SELL]
     *       -price     +price
     *      cap same   cap - 1  ← Transaction completes on SELL!
     *          ↓           ↑
     *         ┌─────────────┐
     *         │  CAN SELL   │ (buy = 0, own stock, cap transactions left)
     *         │  (buy = 0)  │
     *         └─────────────┘
     *
     * ==================== MEMOIZATION APPROACH ====================
     */

    public static int maxProfit(int[] prices) {
        int n = prices.length;

        // 3D DP table:
        // dp[idx][buy][cap]
        //   idx: 0 to n-1 (days)
        //   buy: 0 or 1 (can sell or can buy)
        //   cap: 0 to 2 (transactions remaining)
        int[][][] dp = new int[n][2][3];

        for (int[][] row : dp){
            for (int[] r : row){
                Arrays.fill(r, -1);  // -1 means uncomputed
            }
        }

        // Start: Day 0, CAN BUY (buy=1), 2 transactions remaining (cap=2)
        return profit(0, 1, 2, prices, dp);
    }

    private static int profit(int idx, int buy, int cap, int[] prices, int[][][] dp) {
        /*
         * BASE CASE 1: No transactions remaining (cap == 0)
         * --------------------------------------------------
         * If we've used up both transactions:
         *   → Can't trade anymore
         *   → Return 0 (no profit possible)
         */
        if(cap == 0) return 0;

        /*
         * BASE CASE 2: No more days (idx == prices.length)
         * -------------------------------------------------
         * If we've processed all days:
         *   → No more opportunities to trade
         *   → Return 0 (no profit possible)
         */
        if(idx == prices.length) return 0;

        // Return memoized result if already computed
        if(dp[idx][buy][cap] != -1) return dp[idx][buy][cap];

        int take;
        int notTake;

        /*
         * STATE 1: CAN BUY (buy == 1)
         * ----------------------------
         * We don't own stock, we can BUY or SKIP
         */
        if(buy == 1){
            // Option A: BUY today
            // - Pay prices[idx] (subtract from profit)
            // - Move to CAN SELL state (buy = 0)
            // - cap stays SAME (transaction not complete yet)
            take = -prices[idx] + profit(idx + 1, 0, cap, prices, dp);

            // Option B: SKIP today (don't buy)
            // - Stay in CAN BUY state (buy = 1)
            // - cap stays same
            notTake = profit(idx + 1, 1, cap, prices, dp);
        }
        /*
         * STATE 2: CAN SELL (buy == 0)
         * -----------------------------
         * We own stock, we can SELL or HOLD
         */
        else {
            // Option A: SELL today
            // - Receive prices[idx] (add to profit)
            // - Move to CAN BUY state (buy = 1)
            // - cap DECREASES by 1 (transaction completed!)
            take = prices[idx] + profit(idx + 1, 1, cap - 1, prices, dp);

            // Option B: HOLD (don't sell)
            // - Stay in CAN SELL state (buy = 0)
            // - cap stays same
            notTake = profit(idx + 1, 0, cap, prices, dp);
        }

        // Store and return maximum profit
        return dp[idx][buy][cap] = Math.max(take, notTake);
    }

    /*
     * DETAILED EXAMPLE WALKTHROUGH (Memoization):
     * --------------------------------------------
     * prices = [1, 2, 3, 4, 5]
     *
     * Optimal:
     *   - Buy at 1, sell at 5 → profit = 4 (uses 1 transaction)
     *   - No second transaction needed
     *   - Total = 4
     *
     * Alternative (using both transactions):
     *   - Buy at 1, sell at 2 → profit = 1
     *   - Buy at 2, sell at 5 → profit = 3
     *   - Total = 4 (same!)
     *
     * The DP explores both paths and finds max = 4
     *
     * Key states:
     *   profit(0, 1, 2): Start with 2 transactions, can buy
     *     → Explores: buy at 1, or skip
     *   profit(1, 0, 2): Bought at 1, can sell, 2 transactions left
     *     → Explores: sell at 2 (cap→1), or hold
     *   profit(2, 1, 1): Sold at 2, can buy, 1 transaction left
     *     → Can do one more transaction!
     *
     * ==================== TABULATION APPROACH ====================
     */

    public static int maxProfit1(int[] prices) {
        int n = prices.length;

        // 3D DP table with size (n+1) × 2 × 3
        // Extra row (n+1) for base case handling
        int[][][] dp = new int[n+1][2][3];

        /*
         * BASE CASE INITIALIZATION:
         * -------------------------
         * dp[n][*][*] = 0: No more days → profit = 0
         * dp[*][*][0] = 0: No transactions left → profit = 0
         *
         * Since Java initializes int arrays to 0, we don't need explicit initialization!
         */

        /*
         * FILL DP TABLE:
         * --------------
         * Process in REVERSE order:
         *   - Days: n-1 down to 0
         *   - Buy states: 0 and 1
         *   - Capacity: 1 and 2 (skip 0 as it's always 0)
         */
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) { // cap=0 always gives 0, skip it
                    int take;
                    int notTake;

                    /*
                     * STATE 1: CAN BUY (buy == 1)
                     */
                    if(buy == 1){
                        // Option A: BUY today
                        // cap stays same (transaction incomplete)
                        take = -prices[idx] + dp[idx + 1][0][cap];

                        // Option B: SKIP today
                        notTake = dp[idx + 1][1][cap];
                    }
                    /*
                     * STATE 2: CAN SELL (buy == 0)
                     */
                    else {
                        // Option A: SELL today
                        // cap decreases (transaction complete!)
                        take = prices[idx] + dp[idx + 1][1][cap - 1];

                        // Option B: HOLD
                        notTake = dp[idx + 1][0][cap];
                    }

                    dp[idx][buy][cap] = Math.max(take, notTake);
                }
            }
        }

        // Answer: Day 0, CAN BUY state, 2 transactions remaining
        return dp[0][1][2];
    }

    /*
     * DP TABLE VISUALIZATION (Simplified 2D view for cap=2):
     * -------------------------------------------------------
     * prices = [3, 3, 5, 0, 0, 3, 1, 4]
     *
     * At each day, for cap=2:
     *         buy=0     buy=1
     *         (sell)    (buy)
     *     8    0         0      ← Base case
     *     7    3         3      ← Day 7: price=4
     *     6    3         4      ← Day 6: price=1
     *     5    3         4      ← Day 5: price=3
     *     4    3         4      ← Day 4: price=0
     *     3    4         4      ← Day 3: price=0
     *     2    4         5      ← Day 2: price=5
     *     1    5         6      ← Day 1: price=3
     *     0    6         6      ← Day 0: price=3
     *
     * The algorithm considers multiple transaction combinations
     * and finds the optimal profit = 6
     *
     * ==================== SPACE OPTIMIZATION ====================
     *
     * OBSERVATION:
     * ------------
     * dp[idx] only depends on dp[idx+1]
     * → We only need to store the "next day" values
     * → Can reduce from 3D array to 2D array
     *
     * Space: O(n × 2 × 3) → O(2 × 3) = O(1)
     */

    public static int maxProfit2(int[] prices) {
        int n = prices.length;

        // Instead of dp[n+1][2][3], use dp[2][3] for "next day"
        // dp[buy][cap] represents the next day's values
        int[][] dp = new int[2][3];

        // Base case: dp is already initialized to 0

        /*
         * ITERATE BACKWARDS:
         * ------------------
         * For each day, compute current values based on "next day" (dp)
         */
        for (int idx = n - 1; idx >= 0; idx--) {
            // Create array for current day's values
            int[][] curr = new int[2][3];

            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    int take;
                    int notTake;

                    if(buy == 1){
                        // BUY: Access dp[0][cap] (next day, can sell, same cap)
                        take = -prices[idx] + dp[0][cap];

                        // SKIP: Access dp[1][cap] (next day, can buy, same cap)
                        notTake = dp[1][cap];
                    } else {
                        // SELL: Access dp[1][cap-1] (next day, can buy, cap decreases)
                        take = prices[idx] + dp[1][cap - 1];

                        // HOLD: Access dp[0][cap] (next day, can sell, same cap)
                        notTake = dp[0][cap];
                    }

                    curr[buy][cap] = Math.max(take, notTake);
                }
            }

            // Move current to next (for next iteration, curr becomes "next day")
            dp = curr;
        }

        // After processing all days, dp holds day 0's values
        // Answer: buy=1 (can buy), cap=2 (2 transactions)
        return dp[1][2];
    }

    /*
     * SPACE OPTIMIZATION VISUALIZATION:
     * ----------------------------------
     *
     * Traditional 3D DP:
     *   dp[n+1][2][3] → Space = O(6n)
     *
     * Space Optimized:
     *   Only store "next day" → dp[2][3] → Space = O(6) = O(1)
     *
     * How it works:
     *   Day n-1: Compute curr based on dp (day n values)
     *            dp = curr (shift: curr becomes next)
     *   Day n-2: Compute curr based on dp (day n-1 values)
     *            dp = curr
     *   ...
     *   Day 0:   Compute curr based on dp (day 1 values)
     *            dp = curr → Contains day 0 values (answer!)
     *
     * TIME COMPLEXITY: O(n × 2 × 3) = O(6n) = O(n)
     *   - For each of n days
     *   - For each of 2 buy states
     *   - For each of 3 cap values (actually 2, skip cap=0)
     *
     * SPACE COMPLEXITY:
     *   - Memoization: O(n × 2 × 3) + O(n) recursion = O(n)
     *   - Tabulation: O(n × 2 × 3) = O(n)
     *   - Space Optimized: O(2 × 3) = O(1)
     *
     * GENERALIZATION TO K TRANSACTIONS:
     * ----------------------------------
     * This approach extends to "at most k transactions":
     *
     * dp[idx][buy][cap] where cap goes from 0 to k
     * Time: O(n × k)
     * Space: O(n × k) or O(k) with optimization
     *
     * For k=2: This problem
     * For k=∞: Stock II (unlimited transactions)
     * For k=1: Stock I (single transaction)
     *
     * WHY CAP DECREASES ON SELL, NOT BUY?
     * -----------------------------------
     *
     * Could we count transactions on BUY instead?
     * YES! But then:
     *   - Initial call: profit(0, 1, 2) still works
     *   - When we BUY: cap--, move to sell state
     *   - When we SELL: cap stays same, move to buy state
     *
     * Both work, but counting on SELL is more intuitive:
     *   - "2 sells remaining = 2 complete transactions possible"
     *   - Matches real-world: transaction "closes" when you cash out
     *
     * KEY INSIGHTS:
     * -------------
     *
     * 1. ADDING DIMENSION: Transaction limit adds 3rd dimension to DP
     *    → Tracks "budget" of remaining transactions
     *
     * 2. TRANSACTION COMPLETION: Count when SELL (not buy)
     *    → A transaction is "done" when we cash out
     *
     * 3. SPACE OPTIMIZATION: Only need previous day's values
     *    → Reduces O(n) space to O(1)
     *
     * 4. GENERALIZABLE: Same framework works for k transactions
     *    → Just change cap range from [0,2] to [0,k]
     *
     * 5. BASE CASES: Two stopping conditions
     *    → cap=0: No transactions left
     *    → idx=n: No days left
     *
     * EDGE CASES:
     * -----------
     *
     * 1. Prices always decreasing: [5, 4, 3, 2, 1]
     *    → No profitable transaction possible
     *    → Return 0
     *
     * 2. Prices always increasing: [1, 2, 3, 4, 5]
     *    → One transaction sufficient: buy at 1, sell at 5
     *    → Return 4 (don't need second transaction)
     *
     * 3. Single price: [5]
     *    → No transaction possible
     *    → Return 0
     *
     * 4. Two prices: [1, 5]
     *    → One transaction: buy at 1, sell at 5
     *    → Return 4
     *
     * COMPARISON WITH STOCK II:
     * --------------------------
     * Stock II (Unlimited):     Stock III (2 Transactions):
     *   dp[idx][buy]             dp[idx][buy][cap]
     *   2D state                 3D state
     *   No transaction limit     cap tracks remaining transactions
     *   Simpler                  More complex, but generalizable
     *
     * This problem is the GATEWAY to understanding Stock IV (k transactions)!
     */

    // Space Optimization
    public static int maxProfitspace(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[2][3]; // No need for base cases as dp is already initialized as 0.

        for (int idx = n - 1; idx >= 0; idx--) {
            int[][] curr = new int[2][3];
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) { // works for cap = 0 as well
                    int take;
                    int notTake;
                    if(buy == 1){
                        take = -prices[idx] + dp[0][cap];
                        notTake = dp[1][cap];

                    } else {
                        take = prices[idx] + dp[1][cap - 1];
                        notTake = dp[0][cap];

                    }
                    curr[buy][cap] = Math.max(take, notTake);
                }


            }

            dp = curr;
        }

        return dp[1][2];
    }



    // Approach 2:
    // N * 4 DP
    public static int maxProfit4(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][4];
        for (int[] row : dp){

            Arrays.fill(row, -1);
        }

        return profit4(0, 0 , prices, dp);
    }

    private static int profit4(int idx, int trans, int[] prices, int[][] dp) {

        if(idx == prices.length || trans == 4) return 0;

        if(dp[idx][trans] != -1) return dp[idx][trans];
        int take;
        int notTake;
        if(trans % 2 == 0){
            take = -prices[idx] + profit4(idx + 1, trans + 1, prices, dp);
            notTake = profit4(idx + 1, trans, prices,dp);

        } else {
            take = prices[idx] + profit4(idx + 1, trans + 1, prices,dp);
            notTake = profit4(idx + 1, trans, prices,dp);

        }
        return dp[idx][trans] = Math.max(take, notTake);
    }


    // Tabulation

    public static int maxProfit5(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n+1][5];

        for (int idx = n-1; idx >= 0; idx--) {
            for (int trans = 3; trans >= 0; trans--) {
                int take;
                int notTake;
                if(trans % 2 == 0){
                    take = -prices[idx] + dp[idx + 1][trans + 1];
                    notTake = dp[idx + 1][trans];

                } else {
                    take = prices[idx] + dp[idx + 1][trans + 1];
                    notTake = dp[idx + 1][ trans];

                }
                dp[idx][trans] = Math.max(take, notTake);
            }
        }

        return dp[0][0];
    }
}

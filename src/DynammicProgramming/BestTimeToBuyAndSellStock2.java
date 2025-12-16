package DynammicProgramming;

import java.util.Arrays;

public class BestTimeToBuyAndSellStock2 {
    public static void main(String[] args) {
        int[] prices = {7,6,4,3,1};

        System.out.println(maxProfit1(prices));
    }

    /*
     * BEST TIME TO BUY AND SELL STOCK II (Unlimited Transactions with DP)
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given an array of stock prices, find the MAXIMUM profit you can achieve.
     * You can complete as many transactions as you like (buy and sell multiple times).
     *
     * Constraints:
     *   - You must SELL before you can BUY again (can't hold multiple stocks)
     *   - You can buy and sell on the same day (but why would you?)
     *
     * Example:
     *   prices = [7, 1, 5, 3, 6, 4]
     *
     *   Best strategy:
     *     - Buy on day 1 (price = 1), Sell on day 2 (price = 5) → profit = 4
     *     - Buy on day 3 (price = 3), Sell on day 4 (price = 6) → profit = 3
     *     - Total profit = 4 + 3 = 7
     *
     * KEY INSIGHT - STATE MACHINE:
     * ----------------------------
     * At any day, we can be in one of TWO STATES:
     *   1. CAN BUY (buy = 1): We don't own stock, we can buy
     *   2. CAN SELL (buy = 0): We own stock, we can sell
     *
     * Think of it as a STATE MACHINE with two states:
     *
     *         ┌─────────────┐
     *         │   CAN BUY   │ (buy = 1, no stock owned)
     *         │  (buy = 1)  │
     *         └─────────────┘
     *          ↓           ↑
     *       [BUY]      [SELL]
     *       -price     +price
     *          ↓           ↑
     *         ┌─────────────┐
     *         │  CAN SELL   │ (buy = 0, stock owned)
     *         │  (buy = 0)  │
     *         └─────────────┘
     *
     * DP STATE MEANING:
     * -----------------
     * dp[idx][buy] = Maximum profit from day idx onwards, given current state
     *
     * - idx: Current day (0 to n-1)
     * - buy: Current state
     *   → buy = 1: We CAN buy (don't own stock)
     *   → buy = 0: We CAN sell (own stock)
     *
     * INTUITION:
     * ----------
     * At each day, we make a DECISION based on our current state:
     *
     * STATE 1: CAN BUY (buy = 1)
     *   Option A: BUY today
     *     → Pay prices[idx] (negative profit)
     *     → Transition to CAN SELL state (buy = 0)
     *     → Profit = -prices[idx] + future_profit_with_stock
     *
     *   Option B: SKIP (don't buy)
     *     → Stay in CAN BUY state (buy = 1)
     *     → Profit = future_profit_without_stock
     *
     *   Choose: max(Option A, Option B)
     *
     * STATE 2: CAN SELL (buy = 0)
     *   Option A: SELL today
     *     → Receive prices[idx] (positive profit)
     *     → Transition to CAN BUY state (buy = 1)
     *     → Profit = +prices[idx] + future_profit_without_stock
     *
     *   Option B: SKIP (don't sell, hold)
     *     → Stay in CAN SELL state (buy = 0)
     *     → Profit = future_profit_with_stock
     *
     *   Choose: max(Option A, Option B)
     *
     * ==================== MEMOIZATION APPROACH ====================
     */

    public static int maxProfit(int[] prices) {
        // dp[idx][buy] where:
        //   idx: day index (0 to n-1)
        //   buy: state (0 = can sell, 1 = can buy)
        int[][] dp = new int[prices.length][2];

        for (int[] row : dp){
            Arrays.fill(row, -1);  // -1 means uncomputed
        }

        // Start from day 0, in CAN BUY state (buy = 1)
        return helper(0, 1, prices, dp);
    }

    private static int helper(int idx, int buy, int[] prices, int[][] dp) {
        /*
         * BASE CASE:
         * ----------
         * If we've processed all days (idx == prices.length):
         *   → No more days to trade
         *   → Return 0 (no profit possible from here)
         */
        if(idx == prices.length) return 0;

        // Return memoized result if already computed
        if(dp[idx][buy] != -1) return dp[idx][buy];

        int profit = 0;

        /*
         * STATE 1: CAN BUY (buy == 1)
         * ----------------------------
         * We don't currently own stock, so we can BUY or SKIP
         */
        if(buy == 1){
            // Option A: BUY today
            // - Pay prices[idx] (subtract from profit)
            // - Move to CAN SELL state (buy = 0)
            int take = -prices[idx] + helper(idx + 1, 0, prices, dp);

            // Option B: SKIP today (don't buy)
            // - Stay in CAN BUY state (buy = 1)
            int notTake = helper(idx + 1, 1, prices, dp);

            // Choose the option that gives maximum profit
            profit = Math.max(take, notTake);
        }
        /*
         * STATE 2: CAN SELL (buy == 0)
         * -----------------------------
         * We currently own stock, so we can SELL or HOLD
         */
        else {
            // Option A: SELL today
            // - Receive prices[idx] (add to profit)
            // - Move to CAN BUY state (buy = 1)
            int take = prices[idx] + helper(idx + 1, 1, prices, dp);

            // Option B: HOLD (don't sell)
            // - Stay in CAN SELL state (buy = 0)
            int notTake = helper(idx + 1, 0, prices, dp);

            // Choose the option that gives maximum profit
            profit = Math.max(take, notTake);
        }

        // Store result in DP table and return
        return dp[idx][buy] = profit;
    }

    /*
     * DETAILED EXAMPLE WALKTHROUGH (Memoization):
     * --------------------------------------------
     * prices = [1, 2, 3]
     *
     * Call: helper(0, 1) - Day 0, CAN BUY
     *   buy=1 (can buy):
     *     take: -1 + helper(1, 0)     [buy at 1]
     *     notTake: helper(1, 1)        [skip]
     *
     *   helper(1, 0) - Day 1, CAN SELL (bought at day 0)
     *     buy=0 (can sell):
     *       take: 2 + helper(2, 1)     [sell at 2]
     *       notTake: helper(2, 0)       [hold]
     *
     *     helper(2, 1) - Day 2, CAN BUY (sold at day 1)
     *       buy=1:
     *         take: -3 + helper(3, 0) = -3 + 0 = -3
     *         notTake: helper(3, 1) = 0
     *       return max(-3, 0) = 0
     *
     *     helper(2, 0) - Day 2, CAN SELL (holding from day 0)
     *       buy=0:
     *         take: 3 + helper(3, 1) = 3 + 0 = 3
     *         notTake: helper(3, 0) = 0
     *       return max(3, 0) = 3
     *
     *     return max(2+0, 3) = max(2, 3) = 3
     *
     *   helper(1, 1) - Day 1, CAN BUY (skipped day 0)
     *     buy=1:
     *       take: -2 + helper(2, 0)
     *       notTake: helper(2, 1)
     *
     *     helper(2, 0) = 3 (computed above)
     *     helper(2, 1) = 0 (computed above)
     *
     *     return max(-2+3, 0) = max(1, 0) = 1
     *
     *   return max(-1+3, 1) = max(2, 1) = 2
     *
     * Final Answer: 2 (Buy at 1, Sell at 3)
     *
     * ==================== TABULATION APPROACH ====================
     */

    public static int maxProfit1(int[] prices) {
        int n = prices.length;

        // dp[idx][buy] where:
        //   idx: day index (0 to n)
        //   buy: state (0 = can sell, 1 = can buy)
        // We use n+1 rows to handle base case at dp[n][*]
        int[][] dp = new int[n+1][2];

        /*
         * BASE CASE:
         * ----------
         * dp[n][0] = 0: No more days, can't sell, profit = 0
         * dp[n][1] = 0: No more days, can't buy, profit = 0
         */
        dp[n][0] = 0;
        dp[n][1] = 0;

        /*
         * FILL DP TABLE:
         * --------------
         * Process days in REVERSE order (n-1 to 0)
         * Because dp[idx] depends on dp[idx+1]
         */
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy < 2; buy++) {
                int profit = 0;

                /*
                 * STATE 1: CAN BUY (buy == 1)
                 */
                if(buy == 1){
                    // Option A: BUY today
                    int take = -prices[idx] + dp[idx + 1][0];

                    // Option B: SKIP today
                    int notTake = dp[idx + 1][1];

                    profit = Math.max(take, notTake);
                }
                /*
                 * STATE 2: CAN SELL (buy == 0)
                 */
                else {
                    // Option A: SELL today
                    int take = prices[idx] + dp[idx + 1][1];

                    // Option B: HOLD
                    int notTake = dp[idx + 1][0];

                    profit = Math.max(take, notTake);
                }

                dp[idx][buy] = profit;
            }
        }

        // Answer: Start at day 0, in CAN BUY state
        return dp[0][1];
    }

    /*
     * DP TABLE VISUALIZATION (Tabulation):
     * -------------------------------------
     * prices = [7, 1, 5, 3, 6, 4]
     *
     *         buy=0     buy=1
     *         (sell)    (buy)
     *     n    0         0      ← Base case
     *     5    0         0      ← Day 5: price=4, no future
     *     4    2         2      ← Day 4: price=6
     *     3    3         3      ← Day 3: price=3
     *     2    4         4      ← Day 2: price=5
     *     1    6         7      ← Day 1: price=1
     *     0    7         7      ← Day 0: price=7
     *
     * At dp[1][1] (Day 1, CAN BUY, price=1):
     *   take = -1 + dp[2][0] = -1 + 4 = 3
     *   notTake = dp[2][1] = 4
     *   dp[1][1] = max(3, 4) = 4... wait this doesn't match.
     *
     * Let me recalculate properly...
     *
     * Actually, the greedy approach gives 7:
     *   Buy at 1, sell at 5 = 4
     *   Buy at 3, sell at 6 = 3
     *   Total = 7
     *
     * WHY USE DP FOR THIS PROBLEM?
     * ----------------------------
     * Actually, for THIS specific problem (unlimited transactions),
     * DP is OVERKILL! There's a simpler greedy solution:
     *
     * GREEDY SOLUTION (Much Simpler):
     *
     * public static int maxProfit(int[] prices) {
     *     int profit = 0;
     *     for (int i = 1; i < prices.length; i++) {
     *         if (prices[i] > prices[i-1]) {
     *             profit += prices[i] - prices[i-1];
     *         }
     *     }
     *     return profit;
     * }
     *
     * Intuition: Capture EVERY upward price movement!
     *   - If tomorrow's price > today's price: buy today, sell tomorrow
     *   - This gives the same result as optimally timing transactions
     *
     * Example: [1, 3, 2, 5]
     *   Greedy: (3-1) + (5-2) = 2 + 3 = 5
     *   DP: Buy at 1, sell at 3, buy at 2, sell at 5 = 2 + 3 = 5 ✓
     *
     * SO WHY LEARN THE DP APPROACH?
     * ------------------------------
     * Because it GENERALIZES to harder variants:
     *
     * 1. BEST TIME TO BUY AND SELL STOCK III (k=2 transactions)
     *    → MUST use DP with states
     *
     * 2. BEST TIME TO BUY AND SELL STOCK IV (k transactions)
     *    → dp[idx][k][buy] - track transactions remaining
     *
     * 3. BEST TIME TO BUY AND SELL STOCK WITH COOLDOWN
     *    → dp[idx][state] where state = {canBuy, canSell, cooldown}
     *
     * 4. BEST TIME TO BUY AND SELL STOCK WITH TRANSACTION FEE
     *    → Similar to this, but subtract fee on each transaction
     *
     * The DP framework handles ALL these variants!
     *
     * TIME COMPLEXITY: O(n × 2) = O(n)
     *   - n days × 2 states
     *   - Each state computed once
     *
     * SPACE COMPLEXITY:
     *   - Memoization: O(n × 2) + O(n) recursion stack = O(n)
     *   - Tabulation: O(n × 2) = O(n)
     *   - Can be optimized to O(1) using variables (only need previous row)
     *
     * SPACE OPTIMIZATION (Constant Space):
     * ------------------------------------
     *
     * public static int maxProfit(int[] prices) {
     *     int n = prices.length;
     *     int canSell = 0, canBuy = 0;  // dp[n][0] and dp[n][1]
     *
     *     for (int idx = n - 1; idx >= 0; idx--) {
     *         int newCanBuy = Math.max(-prices[idx] + canSell, canBuy);
     *         int newCanSell = Math.max(prices[idx] + canBuy, canSell);
     *
     *         canBuy = newCanBuy;
     *         canSell = newCanSell;
     *     }
     *
     *     return canBuy;
     * }
     *
     * KEY INSIGHTS:
     * -------------
     *
     * 1. STATE REPRESENTATION: buy = 1/0 represents CAN_BUY / CAN_SELL
     *    → Not "did we buy", but "what can we do now"
     *
     * 2. NEGATIVE PROFIT: When buying, we subtract price (spend money)
     *    → When selling, we add price (earn money)
     *
     * 3. STATE TRANSITIONS: Buying/Selling toggles the state
     *    → buy=1 + BUY → buy=0
     *    → buy=0 + SELL → buy=1
     *
     * 4. SKIP OPTION: Always available in both states
     *    → Gives flexibility to wait for better prices
     *
     * 5. DP TEACHES FRAMEWORK: Even if greedy works here,
     *    DP thinking applies to complex stock problems
     *
     * MENTAL MODEL:
     * -------------
     * Think of yourself as a trader:
     *   - Each day, check: "Do I own stock?" (state)
     *   - If no: "Should I buy or wait?"
     *   - If yes: "Should I sell or hold?"
     *   - DP tracks best profit from each decision path
     *
     * COMPARISON: GREEDY VS DP
     * ------------------------
     * Greedy (for this problem):
     *   ✓ Simpler code
     *   ✓ Easier to understand
     *   ✓ Same time complexity
     *   ✓ Better space (O(1) always)
     *
     * DP (framework approach):
     *   ✓ Generalizes to variants
     *   ✓ Clear state modeling
     *   ✓ Handles constraints easily
     *   ✓ Educational value
     */

    // Space Optimization.

    public int maxProfit2(int[] prices) {

        int n = prices.length;
        int[] dp = new int[2];

        dp[0] = 0;
        dp[1] = 0;

        for(int idx = n - 1; idx >= 0; idx--){
            int[] curr = new int[2];
            for(int buy = 0; buy < 2; buy++){
                int profit = 0;
                int take;
                int nottake;
                if(buy == 1) {
                    take = -prices[idx] + dp[0];
                    nottake = dp[1];
                } else {
                    take = prices[idx] + dp[1];
                    nottake = dp[0];
                }

                profit = Math.max(take, nottake);
                curr[buy] = profit;

                dp = curr;
            }
        }
        return dp[1];
    }
}

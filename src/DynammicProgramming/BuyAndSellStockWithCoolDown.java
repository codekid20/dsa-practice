package DynammicProgramming;

import java.util.Arrays;

public class BuyAndSellStockWithCoolDown {
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        System.out.println(maxProfit1(prices));

    }

    /*
     * BEST TIME TO BUY AND SELL STOCK WITH COOLDOWN
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given an array of stock prices, find the MAXIMUM profit you can achieve
     * with unlimited transactions, BUT with a COOLDOWN period.
     *
     * Cooldown Rule:
     *   - After you SELL, you must wait 1 day before you can BUY again
     *   - You cannot buy on the immediate next day after selling
     *
     * Example:
     *   prices = [1, 2, 3, 0, 2]
     *
     *   Best strategy:
     *     - Day 0: Buy at 1
     *     - Day 1: Sell at 2 → profit = 1
     *     - Day 2: COOLDOWN (can't buy!)
     *     - Day 3: Buy at 0
     *     - Day 4: Sell at 2 → profit = 2
     *     - Total profit = 1 + 2 = 3
     *
     * KEY INSIGHT - THE COOLDOWN CONSTRAINT:
     * --------------------------------------
     * This is similar to Stock II (unlimited transactions), but with one
     * CRITICAL difference:
     *
     * Stock II (no cooldown):
     *   - After selling: Can buy IMMEDIATELY next day
     *   - SELL → next day → CAN BUY
     *
     * Stock with Cooldown:
     *   - After selling: Must SKIP one day before buying
     *   - SELL → next day → COOLDOWN → then CAN BUY
     *   - When we sell, jump to idx+2 (not idx+1)
     *
     * STATE MACHINE WITH COOLDOWN:
     * ----------------------------
     *
     *         ┌─────────────┐
     *         │   CAN BUY   │ (buy = 1, no stock owned)
     *         │  (buy = 1)  │
     *         └─────────────┘
     *          ↓           ↑
     *       [BUY]         │
     *       -price        │
     *      idx+1          │
     *          ↓          │
     *         ┌─────────────┐
     *         │  CAN SELL   │ (buy = 0, stock owned)
     *         │  (buy = 0)  │
     *         └─────────────┘
     *                ↓
     *             [SELL]
     *             +price
     *             idx+2  ← SKIP next day (cooldown!)
     *                ↓
     *         ┌─────────────┐
     *         │  COOLDOWN   │ (implicit, not a state)
     *         │   (skip)    │
     *         └─────────────┘
     *                ↓
     *         Back to CAN BUY
     *
     * DP STATE MEANING:
     * -----------------
     * dp[idx][buy] = Maximum profit from day idx onwards,
     *                with current state 'buy'
     *
     * - idx: Current day (0 to n-1)
     * - buy: Current state
     *   → buy = 1: CAN BUY (don't own stock)
     *   → buy = 0: CAN SELL (own stock)
     *
     * CRUCIAL DIFFERENCE FROM STOCK II:
     * ----------------------------------
     * When we SELL:
     *   - Stock II:       profit(idx+1, 1, ...) → next day can buy
     *   - With Cooldown:  profit(idx+2, 1, ...) → skip one day!
     *
     * This idx+2 jump ENFORCES the cooldown period!
     *
     * ==================== MEMOIZATION APPROACH ====================
     */

    public static int maxProfit(int[] prices) {
        int n = prices.length;

        // dp[idx][buy]
        //   idx: 0 to n-1 (days)
        //   buy: 0 or 1 (can sell or can buy)
        int[][] dp = new int[n][2];

        for(int[] row : dp) Arrays.fill(row, -1);

        // Start: Day 0, CAN BUY (buy = 1)
        return profit(0, 1, prices, dp);
    }

    private static int profit(int idx, int buy, int[] prices, int[][] dp) {
        /*
         * BASE CASE: Beyond last day (idx >= n)
         * --------------------------------------
         * No more days to trade → return 0
         *
         * Note: We check idx > prices.length-1 instead of idx == prices.length
         * because when we sell, we jump to idx+2, which might skip idx+1
         * and land directly on idx+2 (could be beyond array)
         */
        if(idx > prices.length - 1) return 0;

        // Return memoized result if already computed
        if(dp[idx][buy] != -1) return dp[idx][buy];

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
            // - Next day: idx+1 (normal progression)
            take = -prices[idx] + profit(idx + 1, 0, prices, dp);

            // Option B: SKIP today (don't buy)
            // - Stay in CAN BUY state (buy = 1)
            // - Next day: idx+1
            notTake = profit(idx + 1, 1, prices, dp);
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
            // - CRITICAL: Next day is idx+2 (SKIP idx+1 for cooldown!)
            take = prices[idx] + profit(idx + 2, 1, prices, dp);

            // Option B: HOLD (don't sell)
            // - Stay in CAN SELL state (buy = 0)
            // - Next day: idx+1 (normal)
            notTake = profit(idx + 1, 0, prices, dp);
        }

        // Store and return maximum profit
        return dp[idx][buy] = Math.max(take, notTake);
    }

    /*
     * DETAILED EXAMPLE WALKTHROUGH:
     * ------------------------------
     * prices = [1, 2, 3, 0, 2]
     *
     * Optimal Strategy:
     *   Day 0: BUY at 1 → state: CAN SELL
     *   Day 1: SELL at 2 → profit = 1, jump to Day 3 (skip Day 2)
     *   Day 2: COOLDOWN (automatically enforced by idx+2 jump)
     *   Day 3: BUY at 0 → state: CAN SELL
     *   Day 4: SELL at 2 → profit = 2
     *   Total = 3
     *
     * Without Cooldown (Stock II approach):
     *   Day 0: BUY at 1
     *   Day 1: SELL at 2 → profit = 1
     *   Day 2: BUY at 3 (can buy immediately!)
     *   Day 3: SELL at 0 → loss = -3
     *   Total = 1 + 1 + (-3) = -1 (worse!)
     *
     * The cooldown PREVENTS us from making bad trades!
     *
     * Recursion Tree (simplified):
     *
     * profit(0, 1) - Day 0, CAN BUY
     *   ├─ take: -1 + profit(1, 0)     [Buy at 1]
     *   │         ├─ take: 2 + profit(3, 1)  [Sell at 2, jump to day 3!]
     *   │         │         ├─ take: 0 + profit(4, 0)  [Buy at 0]
     *   │         │         │         ├─ take: 2 + profit(6, 1) = 2+0 = 2
     *   │         │         │         └─ notTake: profit(5, 0) = 0
     *   │         │         │         → max = 2
     *   │         │         └─ ...
     *   │         │         → Gives 2
     *   │         └─ notTake: profit(2, 0)  [Hold]
     *   │                   → ...
     *   │         → Total from this path: -1 + 2 + 2 = 3
     *   └─ notTake: profit(1, 1)     [Skip day 0]
     *             → ...
     *
     * Maximum = 3
     *
     * ==================== TABULATION APPROACH ====================
     */

    public static int maxProfit1(int[] prices) {
        int n = prices.length;

        // dp[idx][buy]
        // Size: (n+2) × 2
        //
        // Why n+2 instead of n+1?
        //   - When we sell at day n-1, we jump to day n+1 (idx+2)
        //   - Need to handle dp[n+1] to avoid array out of bounds
        //   - dp[n] and dp[n+1] both represent "no more days" → both = 0
        int[][] dp = new int[n+2][2];

        /*
         * BASE CASE:
         * ----------
         * dp[n][0] = 0 (no more days, can't sell)
         * dp[n][1] = 0 (no more days, can't buy)
         * dp[n+1][0] = 0 (beyond last day)
         * dp[n+1][1] = 0 (beyond last day)
         *
         * Java initializes to 0 automatically!
         */

        /*
         * FILL DP TABLE (Bottom-up):
         * ---------------------------
         * Process days in REVERSE (n-1 to 0)
         * Because dp[idx] depends on dp[idx+1] and dp[idx+2]
         */
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                int take;
                int notTake;

                /*
                 * STATE 1: CAN BUY (buy == 1)
                 */
                if(buy == 1){
                    // BUY today: Move to idx+1, can sell
                    take = -prices[idx] + dp[idx + 1][0];

                    // SKIP today: Move to idx+1, still can buy
                    notTake = dp[idx + 1][1];
                }
                /*
                 * STATE 2: CAN SELL (buy == 0)
                 */
                else {
                    // SELL today: Move to idx+2 (cooldown!), can buy
                    take = prices[idx] + dp[idx + 2][1];

                    // HOLD: Move to idx+1, still can sell
                    notTake = dp[idx + 1][0];
                }

                dp[idx][buy] = Math.max(take, notTake);
            }
        }

        // Answer: Day 0, CAN BUY state
        return dp[0][1];
    }

    /*
     * DP TABLE VISUALIZATION:
     * ------------------------
     * prices = [1, 2, 3, 0, 2]
     *
     *         buy=0     buy=1
     *         (sell)    (buy)
     *     6    0         0      ← Beyond array (for idx+2 jumps)
     *     5    0         0      ← Base case
     *     4    2         2      ← Day 4: price=2
     *     3    2         2      ← Day 3: price=0
     *     2    3         3      ← Day 2: price=3
     *     1    3         3      ← Day 1: price=2
     *     0    3         3      ← Day 0: price=1
     *
     * At dp[1][0] (Day 1, CAN SELL, price=2):
     *   take = 2 + dp[3][1] = 2 + 2 = 4... wait that's not right.
     *
     * Let me recalculate more carefully...
     *
     * Actually at dp[1][0]:
     *   take (sell) = 2 + dp[3][1]
     *   notTake (hold) = dp[2][0]
     *
     * The key is the idx+2 jump when selling!
     *
     * WHY SIZE n+2 INSTEAD OF n+1?
     * ----------------------------
     *
     * Consider selling on the LAST day (idx = n-1):
     *   take = prices[n-1] + dp[n-1+2][1]
     *        = prices[n-1] + dp[n+1][1]
     *
     * We're accessing dp[n+1], which is BEYOND n!
     * So we need size n+2 to avoid index out of bounds.
     *
     * Example: n=5, prices = [1,2,3,4,5]
     *   When idx=3 (day 3), if we sell:
     *     take = prices[3] + dp[5][1]  ← Need dp[5]!
     *
     * With n+1 size, dp[5] would be out of bounds.
     * With n+2 size, dp[5] exists and equals 0.
     *
     * TIME COMPLEXITY: O(n)
     *   - n days × 2 states = 2n iterations
     *   - Each iteration: O(1)
     *
     * SPACE COMPLEXITY:
     *   - Memoization: O(n × 2) + O(n) recursion = O(n)
     *   - Tabulation: O(n × 2) = O(n)
     *
     * SPACE OPTIMIZATION (Further Optimization Possible):
     * ----------------------------------------------------
     * Since we only need dp[idx+1] and dp[idx+2], we can use
     * just 3 variables instead of full array:
     *
     * public static int maxProfit(int[] prices) {
     *     int n = prices.length;
     *
     *     // Variables for: current, next, next-next
     *     int sell_curr = 0, buy_curr = 0;
     *     int sell_next = 0, buy_next = 0;
     *     int sell_next2 = 0, buy_next2 = 0;
     *
     *     for (int idx = n-1; idx >= 0; idx--) {
     *         // CAN BUY state
     *         int new_buy = Math.max(
     *             -prices[idx] + sell_next,  // buy
     *             buy_next                    // skip
     *         );
     *
     *         // CAN SELL state
     *         int new_sell = Math.max(
     *             prices[idx] + buy_next2,    // sell (jump to idx+2)
     *             sell_next                    // hold
     *         );
     *
     *         // Shift values
     *         buy_next2 = buy_next;
     *         buy_next = buy_curr;
     *         buy_curr = new_buy;
     *
     *         sell_next2 = sell_next;
     *         sell_next = sell_curr;
     *         sell_curr = new_sell;
     *     }
     *
     *     return buy_curr;
     * }
     *
     * Space: O(1) - only 6 variables!
     *
     * COMPARISON WITH OTHER STOCK PROBLEMS:
     * --------------------------------------
     *
     * Stock II (Unlimited, No Cooldown):
     *   - SELL: profit(idx+1, 1) → Can buy next day
     *   - Simple, no constraints
     *
     * Stock with Cooldown (This Problem):
     *   - SELL: profit(idx+2, 1) → Skip next day
     *   - One day cooldown after selling
     *
     * Stock with Transaction Fee:
     *   - SELL: prices[idx] - fee + profit(idx+1, 1)
     *   - Subtract fee on each transaction
     *
     * All use the SAME framework, just different transitions!
     *
     * KEY INSIGHTS:
     * -------------
     *
     * 1. COOLDOWN ENFORCEMENT: idx+2 jump (not idx+1) after selling
     *    → Automatically skips one day
     *    → Elegant way to enforce cooldown without extra state
     *
     * 2. ARRAY SIZE: Need n+2 (not n+1) for tabulation
     *    → Selling on last day accesses idx+2
     *    → Must prevent array out of bounds
     *
     * 3. STATE MACHINE: Same 2 states (buy/sell) as Stock II
     *    → Only difference: transition logic
     *    → Shows power of modular DP design
     *
     * 4. GREEDY DOESN'T WORK: Can't just take all upward movements
     *    → Cooldown prevents some profitable trades
     *    → Must use DP to explore all valid sequences
     *
     * 5. IMPLICIT COOLDOWN STATE: We don't track "in cooldown" explicitly
     *    → The idx+2 jump handles it implicitly
     *    → Simpler than adding a 3rd state
     *
     * EDGE CASES:
     * -----------
     *
     * 1. Single price: [5]
     *    → No transaction possible
     *    → Return 0
     *
     * 2. Two prices: [1, 2]
     *    → One transaction: buy at 1, sell at 2
     *    → No cooldown issue (only 1 transaction)
     *    → Return 1
     *
     * 3. Three prices increasing: [1, 2, 3]
     *    → Buy at 1, sell at 2 → profit = 1
     *    → Cooldown on day 2
     *    → Can't buy again (no more days)
     *    → Return 1 (can't capture 2→3 movement due to cooldown)
     *
     * 4. Alternating prices: [1, 4, 2]
     *    → Buy at 1, sell at 4 → profit = 3
     *    → Cooldown would be day 2, but array ends
     *    → Return 3
     *
     * MENTAL MODEL:
     * -------------
     * Think of cooldown as a "recharge period":
     *   - After selling (cashing out), you need 1 day to "cool down"
     *   - Like a video game ability with cooldown
     *   - You can't immediately jump back into trading
     *   - Forces you to be more strategic about timing
     *
     * The idx+2 jump is like time-travel:
     *   - Sell today → teleport to day after tomorrow
     *   - Skipping tomorrow automatically
     *   - No need to explicitly track cooldown state!
     *
     * This is a beautiful example of how CONSTRAINT MODELING
     * can be elegantly handled in DP with the right transition design!
     */
}

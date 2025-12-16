package DynammicProgramming;

public class BestTimeToBuyAndSellStock {
    /*
     * BEST TIME TO BUY AND SELL STOCK (Single Transaction)
     *
     * PROBLEM STATEMENT:
     * ------------------
     * Given an array of stock prices where prices[i] is the price on day i,
     * find the MAXIMUM profit you can achieve by buying on one day and selling
     * on a LATER day.
     *
     * Constraints:
     *   - You can complete at most ONE transaction (1 buy + 1 sell)
     *   - You must BUY before you SELL (can't sell before buying)
     *   - If no profit possible, return 0
     *
     * Example:
     *   prices = [7, 1, 5, 3, 6, 4]
     *
     *   Best strategy:
     *     - Buy on day 1 (price = 1)
     *     - Sell on day 4 (price = 6)
     *     - Profit = 6 - 1 = 5
     *
     * KEY INSIGHT - GREEDY APPROACH:
     * ------------------------------
     * To maximize profit, we need to:
     *   1. Buy at the LOWEST price seen so far
     *   2. Sell at a price that gives MAXIMUM difference from that lowest price
     *
     * We don't need DP! This is a GREEDY problem that can be solved in ONE PASS.
     *
     * INTUITION:
     * ----------
     * As we traverse the array, we ask ourselves two questions at each day:
     *
     *   1. "What if I sell today?"
     *      → Profit = today's price - cheapest price seen so far
     *      → Track maximum profit across all days
     *
     *   2. "Should I update my buying price?"
     *      → If today's price is lower than previous minimum, update it
     *      → This gives us better potential profit for future selling days
     *
     * APPROACH:
     * ---------
     * 1. Initialize:
     *    - buy = prices[0] (assume we buy on day 0)
     *    - sell = 0 (no profit yet, haven't sold)
     *
     * 2. For each day i from 1 to n-1:
     *    a) Calculate profit if we sell today: profit = prices[i] - buy
     *    b) Update maximum profit: sell = max(sell, profit)
     *    c) Update minimum buying price: buy = min(buy, prices[i])
     *
     * 3. Return maximum profit (sell)
     *
     * WHY THIS WORKS:
     * ---------------
     * By keeping track of the MINIMUM price seen so far, we ensure that
     * for any selling day, we're considering the BEST possible buying day
     * that came before it.
     *
     * The order matters:
     *   - We calculate profit BEFORE updating buy price
     *   - This ensures we don't buy and sell on the SAME day
     *   - We always maintain: buy day < sell day
     */

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        System.out.println(maxProfit(prices));  // Output: 5
    }

    public static int maxProfit(int[] prices) {
        // buy: Minimum price seen so far (best day to buy)
        // Start by assuming we buy on day 0
        int buy = prices[0];

        // sell: Maximum profit achievable (best profit from selling)
        // Initially 0 (no transaction yet)
        int sell = 0;

        // Traverse from day 1 onwards
        for (int i = 1; i < prices.length; i++) {
            // Calculate profit if we sell on day i
            // (using the best buying price seen so far)
            int profit = prices[i] - buy;

            // Update maximum profit
            // Keep the better of: previous best OR today's profit
            sell = Math.max(sell, profit);

            // Update minimum buying price
            // If today's price is lower, it becomes our new best buying day
            buy = Math.min(buy, prices[i]);
        }

        // Return the maximum profit achievable
        return sell;
    }

    /*
     * DETAILED EXAMPLE WALKTHROUGH:
     * ------------------------------
     * prices = [7, 1, 5, 3, 6, 4]
     *
     * Initial state:
     *   buy = 7 (bought on day 0)
     *   sell = 0 (no profit yet)
     *
     * Day 1: price = 1
     *   profit = 1 - 7 = -6 (loss!)
     *   sell = max(0, -6) = 0 (keep 0, don't take loss)
     *   buy = min(7, 1) = 1 (found cheaper buying day!)
     *   State: buy=1, sell=0
     *
     * Day 2: price = 5
     *   profit = 5 - 1 = 4 (nice profit!)
     *   sell = max(0, 4) = 4 (update best profit)
     *   buy = min(1, 5) = 1 (keep day 1 as best buy)
     *   State: buy=1, sell=4
     *
     * Day 3: price = 3
     *   profit = 3 - 1 = 2 (profit, but less than current best)
     *   sell = max(4, 2) = 4 (keep previous best)
     *   buy = min(1, 3) = 1 (keep day 1 as best buy)
     *   State: buy=1, sell=4
     *
     * Day 4: price = 6
     *   profit = 6 - 1 = 5 (BEST profit so far!)
     *   sell = max(4, 5) = 5 (update best profit)
     *   buy = min(1, 6) = 1 (keep day 1 as best buy)
     *   State: buy=1, sell=5
     *
     * Day 5: price = 4
     *   profit = 4 - 1 = 3 (profit, but less than current best)
     *   sell = max(5, 3) = 5 (keep previous best)
     *   buy = min(1, 4) = 1 (keep day 1 as best buy)
     *   State: buy=1, sell=5
     *
     * Final Answer: 5 (Buy at 1, Sell at 6)
     *
     * VISUAL REPRESENTATION:
     * ----------------------
     *
     *   Price
     *     7 ●
     *     6     ●               ● ← Sell here!
     *     5       ●           ↗
     *     4             ●   ●
     *     3               ●
     *     2
     *     1   ●                   ← Buy here!
     *     0 ─────────────────────
     *       0 1 2 3 4 5 (days)
     *
     * The algorithm finds the VALLEY (lowest point) to buy
     * and the PEAK (after the valley) to sell for maximum profit.
     *
     * EDGE CASES:
     * -----------
     *
     * 1. Prices always decreasing: [7, 6, 4, 3, 1]
     *    → No profit possible
     *    → Return 0 (sell remains 0)
     *
     *    Trace:
     *      Day 1: profit = 6-7 = -1, sell = max(0,-1) = 0
     *      Day 2: profit = 4-6 = -2, sell = max(0,-2) = 0
     *      ...
     *      Result: 0
     *
     * 2. Prices always increasing: [1, 2, 3, 4, 5]
     *    → Buy at day 0, sell at last day
     *    → Return 5 - 1 = 4
     *
     *    Trace:
     *      buy stays at 1 (first day)
     *      sell keeps increasing: 1, 2, 3, 4
     *      Result: 4
     *
     * 3. All prices same: [5, 5, 5, 5]
     *    → No profit possible
     *    → Return 0
     *
     * 4. Single day: [5]
     *    → Can't buy and sell (need at least 2 days)
     *    → Loop doesn't execute, return sell = 0
     *
     * 5. Two days: [2, 1]
     *    → Price drops, no profit
     *    → Return 0
     *
     * WHY NOT DP?
     * -----------
     * This problem COULD be solved with DP, but it's overkill:
     *
     * DP Approach (unnecessarily complex):
     *   dp[i][0] = max profit on day i with 0 stocks (sold or never bought)
     *   dp[i][1] = max profit on day i with 1 stock (bought, not sold yet)
     *
     *   dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])  // sell today
     *   dp[i][1] = max(dp[i-1][1], -prices[i])              // buy today
     *
     * But the GREEDY approach is:
     *   - Simpler to understand
     *   - Easier to implement
     *   - Same time/space complexity
     *   - More intuitive
     *
     * TIME COMPLEXITY: O(n)
     *   - Single pass through the array
     *   - Each element processed once
     *
     * SPACE COMPLEXITY: O(1)
     *   - Only two variables used (buy, sell)
     *   - No additional data structures
     *
     * COMPARISON WITH SIMILAR PROBLEMS:
     * ---------------------------------
     *
     * 1. BEST TIME TO BUY AND SELL STOCK II (Multiple Transactions)
     *    → Can buy/sell multiple times
     *    → Add ALL positive differences: sum of (prices[i] - prices[i-1]) if positive
     *    → Still greedy, but different logic
     *
     * 2. BEST TIME TO BUY AND SELL STOCK III (At Most 2 Transactions)
     *    → Need DP to track state
     *    → More complex: track 1st buy, 1st sell, 2nd buy, 2nd sell
     *
     * 3. BEST TIME TO BUY AND SELL STOCK WITH COOLDOWN
     *    → Need DP with states
     *    → After selling, must wait 1 day before buying again
     *
     * KEY INSIGHTS:
     * -------------
     *
     * 1. GREEDY CHOICE: Always buy at the lowest price seen so far
     *    → This maximizes potential future profit
     *
     * 2. SINGLE PASS: We don't need to look ahead
     *    → Current decision only depends on minimum so far
     *
     * 3. ORDER MATTERS: Calculate profit before updating buy
     *    → Prevents buying and selling on the same day
     *
     * 4. VARIABLE NAMING: 'buy' is actually "min_price_so_far"
     *    → 'sell' is actually "max_profit_so_far"
     *    → Names chosen for semantic clarity
     *
     * 5. NEGATIVE PROFITS: We handle them naturally
     *    → max(sell, profit) ensures we never report a loss
     *    → If all profits are negative, sell remains 0
     *
     * ALTERNATIVE IMPLEMENTATION (Same Logic):
     * ----------------------------------------
     *
     * public static int maxProfit(int[] prices) {
     *     int minPrice = Integer.MAX_VALUE;
     *     int maxProfit = 0;
     *
     *     for (int price : prices) {
     *         minPrice = Math.min(minPrice, price);
     *         maxProfit = Math.max(maxProfit, price - minPrice);
     *     }
     *
     *     return maxProfit;
     * }
     *
     * This version:
     *   - Uses more descriptive names
     *   - Starts with Integer.MAX_VALUE for minPrice
     *   - Same logic, slightly different structure
     *
     * REAL-WORLD ANALOGY:
     * -------------------
     * Think of shopping for the best deal:
     *   - As you visit stores, remember the cheapest price (buy)
     *   - At each store, calculate savings if you bought at cheapest and sold here
     *   - Remember the best savings (sell)
     *   - At the end, you know the maximum profit opportunity!
     */
}

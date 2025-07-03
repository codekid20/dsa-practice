package DynammicProgramming;

public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};

        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {

        int buy = prices[0];
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - buy;
            sell = Math.max(sell, profit);

            buy = Math.min(buy, prices[i]);
        }

        return sell;
    }
}

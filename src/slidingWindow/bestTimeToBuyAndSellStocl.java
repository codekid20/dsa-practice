package slidingWindow;

public class bestTimeToBuyAndSellStocl {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int Buy = prices[0];
        int Sell = 0;

        for (int i = 1; i < prices.length; i++) {
            if(prices[i] < Buy){
                Buy = prices[i];
            } else {
                Sell = Math.max(Sell, prices[i] - Buy);
            }
        }

        return Sell;
    }
}

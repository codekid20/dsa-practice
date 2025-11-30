package DynammicProgramming;

import java.util.Arrays;
public class PerfectSquares {
    public static void main(String[] args) {

    }

    public int numSquares(int n) {

        int count = (int) Math.sqrt(n);

        int[] squares = new int[count];

        for(int i = 1; i <= count; i++) {
            squares[i - 1] = i * i;
        }

        return coinChange(squares, n);
    }

    public int coinChange(int[] coins, int amount) {

        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for(int[] ro : dp) {
            Arrays.fill(ro, -1);
        }
        int coin = coins(n - 1, coins, amount, dp);
        return coin == (int) 1e9 ? -1 : coin;
    }

    public int coins(int idx, int[] coins, int amt, int[][] dp){

        if(idx == 0) {
            if(amt % coins[idx] == 0){
                return amt / coins[idx];
            } else {
                return (int) 1e9;
            }
        }

        if(dp[idx][amt] != -1) return dp[idx][amt];

        int nottake = coins(idx - 1, coins, amt, dp);
        int take;
        if(coins[idx] <= amt) {
            take = 1 + coins(idx, coins, amt - coins[idx], dp);
        } else {
            take = (int) 1e9;
        }

        return dp[idx][amt] = Math.min(take,nottake);
    }
}

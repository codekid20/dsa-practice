package DynammicProgramming;
import java.util.*;
public class Triangle {
    public static void main(String[] args) {
        int[][] arr = {{2},{3,4},{6,5,7},{4,1,8,3}};
        List<List<Integer>> triangle = new ArrayList<>();

        for (int[] row : arr){
            List<Integer> list = new ArrayList<>();
            for(int num : row){
                list.add(num);
            }

            triangle.add(list);
        }

        System.out.println(minimumTotal3(triangle));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return minSum(triangle,n,0,0,dp);
    }

    private static int minSum(List<List<Integer>> triangle, int n, int i, int j,int[][] dp) {

        if(i == n - 1) return triangle.get(n - 1).get(j);
        if(dp[i][j] != -1) return dp[i][j];
        int down = triangle.get(i).get(j) + minSum(triangle,n,i+1,j,dp);
        int dia = triangle.get(i).get(j) + minSum(triangle,n,i+1,j+1,dp);

        return dp[i][j] = Math.min(down,dia);
    }

    // Tabulation
    // Time: O(n*n)
    // Space: O(n*n)
    public static int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[n-1][i] = triangle.get(n-1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + dp[i+1][j];
                int dia = triangle.get(i).get(j) + dp[i+1][j+1];

                dp[i][j] = Math.min(down,dia);
            }
        }

        return dp[0][0];
    }

    // Space Optimized:
    // Time: O(n*n)
    // Space: O(n*n)
    public static int minimumTotal3(List<List<Integer>> triangle) {

        int n = triangle.size();
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n-1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            int[] curr = new int[n];
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + dp[j];
                int dia = triangle.get(i).get(j) + dp[j+1];

                curr[j] = Math.min(down,dia);
            }

            dp = curr;
        }

        return dp[0];
    }
}

package DynammicProgramming;
import java.util.*;
public class cherryPickup2 {
    public static void main(String[] args) {
        int[][] grid = {{3,1,1},{2,5,1},{1,5,5},{2,1,1}};
        System.out.println(cherryPickup(grid));
    }

    public static int cherryPickup(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[][][] dp = new int[r][c][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return pickup(0,0,c - 1, r, c, grid, dp);
    }

    public static int pickup(int i, int j1, int j2, int r, int c, int[][] grid, int[][][] dp){
        if(j1 < 0 || j2 < 0 || j1 >= c || j2 >= c){
            return (int) -1e8;
        }

        if(i == r -1){
            if(j1 == j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }

        if (dp[i][j1][j2] != -1) return dp[i][j1][j2];

        int maxi = (int)-1e8;
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int value = 0;
                if(j1 == j2) value = grid[i][j1];
                else value = grid[i][j1] + grid[i][j2];
                value += pickup(i + 1, j1+dj1, j2+dj2,r,c, grid, dp);

                maxi = Math.max(maxi, value);
            }
        }

        return dp[i][j1][j2] = maxi;
    }
}

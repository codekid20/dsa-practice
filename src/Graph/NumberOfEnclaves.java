package Graph;

import java.util.LinkedList;
import java.util.Queue;

class Pair3{
    int row;
    int col;

    public Pair3(int ro, int co){
        this.row = ro;
        this.col = co;
    }
}
public class NumberOfEnclaves {
    public static void main(String[] args) {
        int[][] grid = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};

        System.out.println(numEnclaves2(grid));
    }

    public static int numEnclaves(int[][] grid) {

        Queue<Pair3> queue = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;

        int[][] vis = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // first row ; // first col ; // last row ; // last col
                if(i == 0 || j == 0 || i == n - 1 || j == m - 1){
                    if(grid[i][j] == 1){
                        queue.add(new Pair3(i,j));
                        vis[i][j] = 1;
                    }
                }
            }
        }

        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        while (!queue.isEmpty()){
            Pair3 node = queue.remove();
            int row = node.row;
            int col = node.col;

            for (int i = 0; i < 4; i++) {
                int nRow = row + delRow[i];
                int nCol = col + delCol[i];

                if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && vis[nRow][nCol] == 0 && grid[nRow][nCol] == 1){
                    queue.add(new Pair3(nRow, nCol));
                    vis[nRow][nCol] = 1;
                }
            }
        }


        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 1 && vis[i][j] == 0){
                    ans++;
                }
            }
        }

        return ans;
    }


    public static int numEnclaves2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i == 0 || j == 0 || i == n -1 || j == m -1){
                    if(grid[i][j] == 1){
                        dfs(grid,i,j);
                    }
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 1){
                    ans++;
                }
            }
        }

        return ans;
    }

    private static void dfs(int[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0){
            return;
        }

        grid[i][j] = 0;

        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}

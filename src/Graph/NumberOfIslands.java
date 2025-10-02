package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public static void main(String[] args) {

    }

    class Pair{
        int first;
        int second;

        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        int count = 0;
        for(int row = 0; row < n; row++){
            for(int col = 0; col < m; col++){
                if(vis[row][col] == 0 && grid[row][col] == 1){
                    count++;
                    bfs(row, col, vis, grid);
                }
            }
        }

        return count;
    }

    private void bfs(int row, int col, int[][] vis, char[][] grid) {
        vis[row][col] = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row, col));
        int n = grid.length;
        int m = grid[0].length;

        while (!q.isEmpty()){
            int ro = q.peek().first;
            int co = q.peek().second;
            q.poll();

            for (int delrow = -1; delrow <= 1; delrow++) {
                for (int delcol = -1; delcol <= 1; delcol++) {
                    if (delrow == 0 && delcol == 0) continue;
                    // Skip diagonals: only allow one of row or col to change
                    if (Math.abs(delrow) + Math.abs(delcol) != 1) continue;
                    int nrow = ro + delrow;
                    int ncol = co + delcol;

                    if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && grid[nrow][ncol] == 1 && vis[nrow][ncol] == 0){
                        vis[nrow][ncol] = 1;
                        q.add(new Pair(nrow, ncol));
                    }
                }
            }
        }
    }

    private void bfs1(int row, int col, int[][] vis, char[][] grid) {
        vis[row][col] = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row, col));
        int n = grid.length;
        int m = grid[0].length;
        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};
        while (!q.isEmpty()){
            int ro = q.peek().first;
            int co = q.peek().second;
            q.poll();

            for (int i = 0; i < 4; i++) {
                int nrow = ro + drow[i];
                int ncol = co + dcol[i];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && grid[nrow][ncol] == 1 && vis[nrow][ncol] == 0){
                    vis[nrow][ncol] = 1;
                    q.add(new Pair(nrow, ncol));
                }
            }
        }
    }



    // Approach 2: Sinking the island
    public int numIslands1(char[][] grid) {
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                count += sink(grid, row, col);
            }
        }
        return count;
    }

    private int sink(char[][] grid, int row, int col) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0'){
            return 0;
        }
        grid[row][col] = '0';

        sink(grid,row - 1, col);
        sink(grid, row + 1, col);
        sink(grid, row, col - 1);
        sink(grid, row, col + 1);
        return 1;
    }
}

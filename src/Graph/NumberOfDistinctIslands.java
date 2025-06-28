package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class NumberOfDistinctIslands {
    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}};

        System.out.println(countDistinctIslands(grid));
    }

    static int countDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] vis = new int[n][m];

        HashSet<ArrayList<String>> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(vis[i][j] == 0 && grid[i][j] == 1){
                    ArrayList<String> vec = new ArrayList<>();
                    dfs(i , j, vis, grid, vec, i , j);
                    set.add(vec);
                }
            }
        }

        return set.size();
    }

    private static void dfs(int row, int col, int[][] vis, int[][] grid, ArrayList<String> vec, int row0, int col0) {
        vis[row][col] = 1;

        vec.add(tostring(row - row0, col - col0));
        int n = grid.length;
        int m = grid[0].length;

        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, -1, 0, 1};

        for (int i = 0; i < 4; i++) {
            int nRow = row + delRow[i];
            int nCol = col + delCol[i];

            if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && vis[nRow][nCol] == 0 && grid[nRow][nCol] == 1){
                dfs(nRow, nCol, vis, grid, vec, row0, col0);
            }
        }
    }

    public static String tostring(int r, int c){
        return r + " " + c;
    }
}

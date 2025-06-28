package Graph;

import java.util.*;
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {

        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};

        System.out.println(pacificAtlantic(heights));
    }

    static int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        if(heights == null || heights.length == 0 || heights[0].length == 0){
            return ans;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            dfs(heights, pacific, Integer.MIN_VALUE, row, 0);
            dfs(heights, atlantic, Integer.MIN_VALUE, row, cols - 1);
        }

        for (int col = 0; col < cols; col++) {
            dfs(heights,pacific,Integer.MIN_VALUE,0,col);
            dfs(heights,atlantic,Integer.MIN_VALUE,rows - 1, col);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]){
                    List<Integer> inner = new ArrayList<>();
                    inner.add(i);
                    inner.add(j);
                    ans.add(inner);
                }
            }
        }

        return ans;

    }

    private static void dfs(int[][] heights, boolean[][] visited, int height, int x, int y) {
        int ro = heights.length;
        int co = heights[0].length;

        if(x < 0 || x >= ro || y < 0 || y >= co || visited[x][y] || heights[x][y] < height){
            return;
        }

        visited[x][y] = true;

        for (int[] dir : dirs){

            int nrow = x + dir[0];

            int ncol = y + dir[1];

            dfs(heights, visited, heights[x][y], nrow, ncol);
        }
    }

    // Approach 2: BFS LATER
}

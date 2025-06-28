package Graph;

public class MaxAreaOfIsland {
    public static void main(String[] args) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] vis = new int[rows][cols];
        int maxArea = 0;
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if(grid[r][c] == 1 && vis[r][c] != 1){
                    int area = islandArea(grid, r, c, vis, dir);
//                    int area = islandArea1(grid, r, c, vis);
                    maxArea = Math.max(area,maxArea);
                }
            }
        }

        return maxArea;
    }

    private static int islandArea(int[][] grid, int ro, int co, int[][] vis, int[][] dirs) {
        if(ro < 0 || ro >= grid.length || co < 0 || co >= grid[0].length || grid[ro][co] == 0 || vis[ro][co] == 1){
            return 0;
        }

        vis[ro][co] = 1;
        int area = 1;

        for(int[] dir : dirs){
            int nrow = ro + dir[0];
            int ncol = co + dir[1];

            area += islandArea(grid,nrow,ncol,vis,dirs);
        }

        return area;
    }

    private static int islandArea1(int[][] grid, int ro, int co, int[][] vis) {
        if(ro < 0 || ro >= grid.length || co < 0 || co >= grid[0].length || grid[ro][co] == 0 || vis[ro][co] == 1){
            return 0;
        }

        vis[ro][co] = 1;
        int area = 1;

        area += islandArea1(grid,ro-1,co,vis);
        area += islandArea1(grid,ro+1,co,vis);
        area += islandArea1(grid,ro,co - 1,vis);
        area += islandArea1(grid,ro,co + 1,vis);

        return area;
    }
}

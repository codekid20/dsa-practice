package Graph;

public class IslandPerimeter {
    public static void main(String[] args) {
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        System.out.println(islandPerimeter(grid));
    }

    // Idea :
    // 1. take a visited array.
    // 2. travers grid, when encounter 1, dfs recursively on it (only if not visited) if its surrounding are water, increase count.
    // 3. do it for every land.

    static int count; // Cannot pass count in functions calls because it's passed by value and not passed by reference.
    public static int islandPerimeter(int[][] grid) {
        int[][] vis = new int[grid.length][grid[0].length];
        count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] == 1 && vis[row][col] == 0){
                    dfs(row,col,grid,vis);
                }
            }
        }

        return count;
    }

    private static void dfs(int row, int col, int[][] grid, int[][] vis) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0){
            count++;
            return;
        }

        // This is important so that I don't fall in iterative loop.
        if(vis[row][col] == 1){
            return;
        }

        vis[row][col] = 1;

        dfs(row - 1, col, grid, vis);
        dfs(row + 1, col, grid, vis);
        dfs(row, col - 1, grid, vis);
        dfs(row, col + 1, grid, vis);

        return;
    }


    // Approach: 2
    public static int islandPerimeter2(int[][] grid) {
        int perimeter = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        perimeter -= 2;
                    }

                    if (j > 0 && grid[i][j - 1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }
        return perimeter;
    }
}

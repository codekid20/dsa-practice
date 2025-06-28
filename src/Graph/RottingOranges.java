package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid));
    }

    // BFS :
    // Approach 1:
    // 1. Identify all rotten oranges initially and place their position in queue. These oranges simultaneously rot neighboring fresh oranges.
    // 2. Identify the size of the queue and increase time by one and loop that many times in the 1st minute.
    // 3. Also count the fresh oranges.
    // 4. keep marking fresh oranges as rotten. In the end if fresh oranges left, return -1 otherwise return count.

    public static int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] == 2){
                    queue.offer(new int[]{i,j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if(fresh == 0) return 0;

        int count = 0;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()){
            if(fresh == 0){
                return count;
            }

            count++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                int r = point[0];
                int c = point[1];
                for (int[] dir : dirs){
                    int nrow = r + dir[0];
                    int ncol = c + dir[1];

                    if(nrow < 0 || ncol < 0 || nrow >= rows || ncol >= cols || grid[nrow][ncol] == 0 || grid[nrow][ncol] == 2){
                        continue;
                    }

                    grid[nrow][ncol] = 2;

                    queue.offer(new int[]{nrow,ncol});

                    fresh--;
                }
            }
        }

        return -1;
    }
}

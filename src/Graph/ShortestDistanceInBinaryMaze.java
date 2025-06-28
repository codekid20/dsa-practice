package Graph;

import java.util.LinkedList;
import java.util.Queue;

class Tuple {
    int first, second, third;
    public Tuple(int first, int second, int third){
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
public class ShortestDistanceInBinaryMaze {
    public static void main(String[] args) {

    }

    // 4 direction movement
    // Time Complexity: O(M*N)

    int shortestPath(int[][] grid, int[] source, int[] destination) {

        if(source[0] == destination[0] && source[1] == destination[1]){
            return 0;
        }

        Queue<Tuple> q = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        int[][] dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = (int) 1e9;
            }
        }

        dist[source[0]][source[1]] = 0;
        q.add(new Tuple(0, source[0], source[1]));

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!q.isEmpty()){
            Tuple it = q.poll();
            int dis = it.first;
            int r = it.second;
            int c = it.third;

            for (int i = 0; i < 4; i++) {
                int nrow = r + dr[i];
                int ncol = c + dc[i];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && grid[nrow][ncol] == 1 && dis + 1 < dist[nrow][ncol]){
                    dist[nrow][ncol] = 1 + dis;
                    if(nrow == destination[0] && ncol == destination[1]){
                        return dis + 1;
                    }

                    q.add(new Tuple(1 + dis, nrow, ncol));
                }
            }
        }

        return -1;
    }


    // Approach 2: Moving 8 directions
    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;


        if(grid[0][0] == 1 || grid[n -1][m - 1] == 1){
            return -1;
        }

        Queue<Tuple> q = new LinkedList<>();

        int[][] dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = (int) 1e9;
            }
        }

        dist[0][0] = 1;
        q.add(new Tuple(0, 0, 0));

        int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

        while (!q.isEmpty()){
            Tuple it = q.poll();
            int dis = it.first;
            int r = it.second;
            int c = it.third;

            if(r == n - 1 && c == m - 1){
                return dis;
            }

            for (int i = 0; i < 8; i++) {
                int nrow = r + dr[i];
                int ncol = c + dc[i];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && grid[nrow][ncol] == 0 && dis + 1 < dist[nrow][ncol]){
                    dist[nrow][ncol] = 1 + dis;
                    q.add(new Tuple(1 + dis, nrow, ncol));
                }
            }
        }

        return -1;
    }
}

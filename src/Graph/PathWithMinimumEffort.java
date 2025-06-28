package Graph;

import java.util.PriorityQueue;

class tuple {
    int distance;
    int row;
    int col;

    public tuple(int distance, int row, int col){
        this.distance = distance;
        this.row = row;
        this.col = col;
    }
}
public class PathWithMinimumEffort {
    public static void main(String[] args) {
        int[][] heights = {{1,2,2},{3,8,2},{5,3,5}};
        System.out.println(minimumEffortPath(heights));
    }

    public static int minimumEffortPath(int[][] heights) {

        int n = heights.length;
        int m = heights[0].length;

        PriorityQueue<tuple> pq = new PriorityQueue<>((a,b) -> a.distance - b.distance);
        int[][] dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = (int) 1e9;
            }
        }

        dist[0][0] = 0;

        pq.add(new tuple(0,0,0));
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!pq.isEmpty()){
            tuple it = pq.poll();
            int diff = it.distance;
            int r = it.row;
            int c = it.col;

            if(r == n - 1 && c == m - 1){
                return diff;
            }

            for (int i = 0; i < 4; i++) {
                int nrow = r + dr[i];
                int ncol = c + dc[i];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m){
                    int newEffort = Math.max(Math.abs(heights[r][c] - heights[nrow][ncol]), diff);

                    if(newEffort < dist[nrow][ncol]){
                        dist[nrow][ncol] = newEffort;
                        pq.add(new tuple(newEffort, nrow, ncol));
                    }
                }
            }
        }

        return 0;
    }
}

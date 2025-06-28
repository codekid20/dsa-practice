package Graph;

import java.util.Arrays;

public class FloydWarshall {
    public static void main(String[] args) {
        int[][] dist = {{0, 4, (int) 1e9, 5, (int) 1e9}, {(int) 1e9, 0, 1, (int) 1e9, 6}, {2, (int) 1e9, 0, 3, (int) 1e9}, {(int) 1e9, (int) 1e9, 1, 0, 2}, {1, (int) 1e9, (int) 1e9, 4, 0}};
        floydWarshall(dist);
        System.out.println(Arrays.deepToString(dist));
    }

    // Time Complexity : O(N^3)
    // Space Complexity: O(N^2)
    public static void floydWarshall(int[][] dist) {

        int n = dist.length;
        for(int k = 0; k < n; k++){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(dist[i][k] != 1e9 && dist[k][j] != 1e9) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }
}

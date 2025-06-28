package Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {
    public static void main(String[] args) {

    }

    // Time Complexity: O(V * E)
    // Space Complexity :O(V)

    public static int[] bellmanFord(int V, int[][] edges, int src) {

        int[] dist = new int[V];

        Arrays.fill(dist, (int) 1e8);

        dist[src] = 0;

        for (int i = 0; i < V-1; i++) {
            for (int[] edge : edges){
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                if(dist[u] != (int) 1e8 && dist[u] + wt < dist[v]){
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Nth Relaxation to check negative cycle
        for (int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            if(dist[u] != (int) 1e8 && dist[u] + wt < dist[v]){
                int[] temp = new int[1];
                temp[0] = -1;
                return temp;
            }
        }

        return dist;
    }
}

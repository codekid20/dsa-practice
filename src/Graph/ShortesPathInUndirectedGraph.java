package Graph;
import java.util.*;
public class ShortesPathInUndirectedGraph {
    public static void main(String[] args) {
        int adj[][] = {{1, 3}, {0, 2}, {1, 6}, {0, 4}, {3, 5}, {4, 6}, {2, 5, 7, 8}, {6, 8}, {7, 6}};

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < adj.length; i++) {
            ArrayList<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < adj[i].length; j++) {
                neighbors.add(adj[i][j]);
            }
            adjList.add(neighbors);
        }

        int src = 0;

        System.out.println(Arrays.toString(shortestPath(adjList, src)));
    }

    // Time Complexity: O(V + E)

    public static int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {

        int[] dist = new int[adj.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        while (!queue.isEmpty()){
            int node = queue.poll();
            for (int it : adj.get(node)){
                if(dist[node] + 1 < dist[it]){
                    dist[it] = 1 + dist[node];
                    queue.add(it);
                }
            }
        }

        for (int i = 0; i < adj.size(); i++){
            if(dist[i] == Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }

        return dist;
    }
}

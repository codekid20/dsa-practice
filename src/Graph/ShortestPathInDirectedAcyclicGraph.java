package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class Pair{
    int first;
    int second;

    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}
public class ShortestPathInDirectedAcyclicGraph {
    public static void main(String[] args) {
        int V = 4, E = 2;
        int[][] edges = {{0,1,2}, {0,2,1}};

        System.out.println(Arrays.toString(shortestPath(V, E, edges)));
    }

    // Time Complexity: O(V + E)
    // Auxiliary Space : O(V+E)

    // Algorithm:
    // 1. Do a Toposort on Graph
    // 2. Take the nodes out of stack(used for toposort) and relax the edges
    // 3. Create a distance array initially with infinite distance.


    public static int[] shortestPath(int V, int E, int[][] edges) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            ArrayList<Pair> inner = new ArrayList<>();
            adj.add(inner);
        }

        for (int i = 0; i < E; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            adj.get(u).add(new Pair(v, wt));
        }

        int[] vis = new int[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if(vis[i] == 0){
                topoSort(i, adj, vis, stack);
            }
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0] = 0;
        while (!stack.isEmpty()){
            int node = stack.pop();

            for (Pair p : adj.get(node)) {
                int v = p.first;
                int wt = p.second;

                if(dist[node] + wt < dist[v]){
                    dist[v] = dist[node] + wt;
                }
            }
        }

        for (int i = 0; i < dist.length; i++) {
            if(dist[i] == Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }

        return dist;
    }

    private static void topoSort(int node, ArrayList<ArrayList<Pair>> adj, int[] vis, Stack<Integer> stack) {

        vis[node] = 1;
        for (int i = 0; i < adj.get(node).size(); i++) {
            int v = adj.get(node).get(i).first;
            if(vis[v] == 0){
                topoSort(v, adj, vis, stack);
            }
        }

        stack.add(node);
    }
}

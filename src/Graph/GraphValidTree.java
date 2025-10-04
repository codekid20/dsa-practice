package Graph;

import java.util.*;

public class GraphValidTree {
    public static void main(String[] args) {

    }


    public static Boolean checkGraph(int[][] edges, int n, int m) {

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        return isConnected(adj, n) && !isCycle(adj, n);
    }

    private static boolean isCycle(List<List<Integer>> adj, int V) {

        boolean[] vis = new boolean[V];

        for(int i = 0; i < V; i++){
            if(!vis[i]) {
                if(dfs1(i, adj, vis, -1)){
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs1(int start, List<List<Integer>> adj, boolean[] vis, int parent) {

        vis[start] = true;

        for(int neighbour : adj.get(start)) {
            if(!vis[neighbour]){
                if(dfs1(neighbour, adj, vis, start)){
                    return true;
                }
            } else if (neighbour != parent) {
                return true;
            }
        }

        return false;
    }

    private static boolean isConnected(List<List<Integer>> adj, int V) {

        boolean[] vis = new boolean[V];

        dfs(0, adj, vis);

        for(boolean v : vis) {
            if(!v) return false;
        }

        return true;
    }

    private static void dfs(int node, List<List<Integer>> adj, boolean[] vis) {

        vis[node] = true;

        for(int neighbour : adj.get(node)) {
            if(!vis[neighbour]){
                dfs(neighbour, adj, vis);
            }
        }
    }
}

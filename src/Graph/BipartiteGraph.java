package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraph {
    public static void main(String[] args) {
        int V = 3;
        int[][] edges = {{0, 1}, {1,2}};

        System.out.println(isBipartite(V, edges));
    }

    // Time Complexity : O(V + E)
    public static boolean isBipartite(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if(color[i] == -1){
                if(!check(i, V, adj, color)){
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean check(int start, int V, ArrayList<ArrayList<Integer>> adj, int[] color) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 0;

        while (!queue.isEmpty()){
            int node = queue.remove();

            for(int it : adj.get(node)){
                if(color[it] == -1){
                    color[it] = 1 - color[node];
                    queue.add(it);
                } else if (color[it] == color[node]){ // is the adjacent guy having the same color someone did color it on some other path
                    return false;
                }
            }
        }

        return true;
    }

    // DFS
    public static boolean isBipartite2(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if(color[i] == -1){
                if(!dfs(i, 0, color, adj)){
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean dfs(int node, int col, int[] color, ArrayList<ArrayList<Integer>> adj) {

        color[node] = col;

        for(int it : adj.get(node)){
            if(color[it] == -1){
                if(!dfs(it, 1 - col, color, adj)){
                    return false;
                }
            } else if(color[it] == col){
                return false;
            }
        }

        return true;
    }
}

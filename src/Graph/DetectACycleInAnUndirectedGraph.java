package Graph;

import java.util.*;

public class DetectACycleInAnUndirectedGraph {
    public static void main(String[] args) {
        List<Integer>[] adj = new ArrayList[3];

        for (int i = 0; i < 3; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < 3; i++) {
            adj[i].add(i);
        }

        int V = 4, E = 4;
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {2, 3}};

        System.out.println(isCycle1(V, edges));
    }


    public static boolean isCycle(int V, int[][] edges) {

        List<Integer>[] adj = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if(!vis[i]){
                if(bfs(i,adj,vis)){
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean bfs(int start, List<Integer>[] adj, boolean[] vis) {
        vis[start] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start,-1});

        while (!queue.isEmpty()){
            int[] vertex = queue.poll();
            int node = vertex[0];
            int parent = vertex[1];

            for (int neighbour : adj[node]){
                if(!vis[neighbour]){
                    vis[neighbour] = true;
                    queue.add(new int[]{neighbour,node});
                } else if(neighbour != parent){
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean isCycle1(int V, int[][] edges) {

        boolean[] vis = new boolean[V];
        List<Integer>[] adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        for (int i = 0; i < V; i++) {
            if(!vis[i]){
                if(dfs(i, adj, vis, -1)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(int start, List<Integer>[] adj, boolean[] vis, int parent) {
        vis[start] = true;

        for (int neighbour : adj[start]){
            if(!vis[neighbour]){
                if(dfs(neighbour,adj, vis, start)){
                    return true;
                }
            } else if(neighbour != parent){
                return true;
            }
        }

        return false;
    }
}

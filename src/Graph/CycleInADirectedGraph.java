package Graph;
import java.util.*;
public class CycleInADirectedGraph {
    public static void main(String[] args) {

    }

    // Approach 1: BFS : Kahn;s Algorithm


    public boolean isCyclic(int V, int[][] edges) {

        List<Integer>[] adj = new List[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
        }

        int[] indegree = new int[V];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < V; i++){
            for (int it : adj[i]){
                indegree[it]++;
            }
        }

        for (int i = 0; i < V; i++) {
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        int count = 0;

        while (!queue.isEmpty()){
            int node = queue.poll();

            count++;

            for (int it : adj[node]){
                indegree[it]--;
                if(indegree[it] == 0){
                    queue.add(it);
                }
            }
        }

        return count != V;
    }


    // Approach 2: USING DFS
    // Makes use of two arrays: visited and pathVisited

    public static boolean isCyclic1(int V, int[][] edges) {

        List<Integer>[] adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
        }

        int[] vis = new int[V];
        int[] pathVis = new int[V];

        for(int i = 0; i < V; i++){
            if(vis[i] == 0){
                if(dfs(i, adj, vis, pathVis)){
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(int node, List<Integer>[] adj, int[] vis, int[] pathVis) {

        vis[node] = 1;
        pathVis[node] = 1;

        for(int it : adj[node]){
            if(vis[it] == 0){
                if(dfs(it, adj, vis, pathVis)){
                    return true;
                }
            } else if(pathVis[it] == 1){
                return true;
            }
        }

        pathVis[node] = 0;
        return false;
    }
}

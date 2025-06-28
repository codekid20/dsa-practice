package Graph;
import java.util.*;
public class TopologicalSorting {
    public static void main(String[] args) {
        int V = 4, E = 3;
        int[][] edges = {{3, 0}, {1, 0}, {2, 0}};

        System.out.println(topoSort(V, edges));
    }

    // Approach 1: USING DFS
    // Time Complexity: O(V+E)
    // Auxiliary space: O(V)

    public static ArrayList<Integer> topoSort(int V, int[][] edges) {

        List<Integer>[] adj = new List[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }


        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
        }

        Stack<Integer> stack = new Stack<>();
        int[] vis = new int[V];

        for (int i = 0; i < V; i++) {
            if(vis[i] == 0){
                dfs(i,vis,stack,adj);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (!stack.isEmpty()){
            ans.add(stack.pop());
        }

        return ans;
    }

    private static void dfs(int start, int[] vis, Stack<Integer> stack, List<Integer>[] adj) {
        vis[start] = 1;
        for(int neighbour : adj[start]){
            if(vis[neighbour] == 0){
                dfs(neighbour, vis, stack, adj);
            }
        }

        stack.push(start);
    }


    // Approach 2: USING BFS -> Kahn's Algorithm
    // Time Complexity: O(V+E)
    // Auxiliary Space: O(V)
    public static ArrayList<Integer> topoSort1(int V, int[][] edges) {

        List<Integer>[] adj = new List[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }


        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
        }

        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for(int it : adj[i]){
                indegree[it]++;
            }
        }


        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        while (!queue.isEmpty()){
            int node = queue.remove();

            ans.add(node);

            for(int it : adj[node]){
                indegree[it]--;

                if(indegree[it] == 0){
                    queue.add(it);
                }
            }
        }

        return ans;
    }


}

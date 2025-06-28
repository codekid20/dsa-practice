package Graph;
import java.util.*;
public class FindEventualSafeStates {
    public static void main(String[] args) {
        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
        System.out.println(eventualSafeNodes1(graph));
    }

    public static List<Integer> eventualSafeNodes(int[][] graph) {

        int V = graph.length;

        int[] vis = new int[V];
        int[] pathVis = new int[V];
        int[] safe = new int[V];
        List<Integer> ans = new ArrayList<>();

        for(int i = 0; i < V; i++){
            if(vis[i] == 0){
                dfs(i, graph, vis, pathVis, safe);
            }
        }

        for (int i = 0; i < V; i++) {
            if(safe[i] == 1){
                ans.add(i);
            }
        }

        return ans;
    }

    private static boolean dfs(int node, int[][] adj, int[] vis, int[] pathVis, int[] safe) {

        vis[node] = 1;
        pathVis[node] = 1;
        for (int it : adj[node]) {
            if(vis[it] == 0){
                if(dfs(it,adj,vis,pathVis,safe)){ // Tells previous nodes if cycle is present
                    return true;
                }
            } else if(pathVis[it] == 1){
                return true;
            }
        }
        safe[node] = 1;
        pathVis[node] = 0;
        return false;
    }


    // Approach 2: BFS -> Topological Sort -> Kahns's Algorithm

    public static List<Integer> eventualSafeNodes1(int[][] graph) {

        List<Integer>[] revGraph = new List[graph.length];
        int[] indegree = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            revGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < graph.length; i++) {
            int[] edge = graph[i];

            for(int it : edge){
                revGraph[it].add(i);
                indegree[i]++;
            }
        }


        Queue<Integer> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < graph.length; i++) {
            if(indegree[i] == 0){
                queue.add(i);
            }
        }


        while (!queue.isEmpty()){
            int node = queue.poll();

            ans.add(node);

            for(int it : revGraph[node]){
                indegree[it]--;
                if(indegree[it] == 0){
                    queue.add(it);
                }
            }
        }

        Collections.sort(ans);
        return ans;
    }
}

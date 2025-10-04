package Graph;
import java.util.ArrayList;
import java.util.List;
public class NumberOfConnectedComponentsInAnUndirectedGraph {
    public static void main(String[] args) {

    }

    public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if(!vis[i]) {
                ArrayList<Integer> component = new ArrayList<>();
                dfs(i, adj, vis, component);
                ans.add(component);
            }
        }

        return ans;
    }

    private void dfs(int start, List<List<Integer>> adj, boolean[] vis, ArrayList<Integer> component) {

        vis[start] = true;
        component.add(start);

        for(int neighbour : adj.get(start)) {
            if(!vis[neighbour]) {
                dfs(neighbour, adj, vis, component);
            }
        }
    }
}


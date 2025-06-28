package Graph;
import java.util.*;
public class DFSInGraph {
    public static void main(String[] args) {

    }

    public static ArrayList<Integer> depthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        boolean[] visited = new boolean[v + 1];
        visited[0] = true;
        ArrayList<Integer> ans = new ArrayList<>();
        dfs(0, visited, edges, ans);
        return ans;
    }

    private static void dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> edges, ArrayList<Integer> ans) {

        visited[node] = true;
        ans.add(node);

        for (int it : edges.get(node)){
            if(!visited[it]){
                dfs(it, visited, edges, ans);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> depthFirstSearch1(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            int first = edges.get(i).get(0);
            int second = edges.get(i).get(1);

            ArrayList<Integer> list = adjList.get(first);
            list.add(second);

            list = adjList.get(second);
            list.add(first);
        }


        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[v + 1];

        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                ArrayList<Integer> component = new ArrayList<>();
                dfs1(i, visited, adjList, component);
                ans.add(component);
            }
        }

        return ans;
    }

    private static void dfs1(int node, boolean[] visited, ArrayList<ArrayList<Integer>> edges, ArrayList<Integer> component) {

        visited[node] = true;
        component.add(node);

        for (int it : edges.get(node)){
            if(!visited[it]){
                dfs1(it, visited, edges, component);
            }
        }
    }
}

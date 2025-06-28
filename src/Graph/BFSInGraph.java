package Graph;
import java.util.*;
public class BFSInGraph {
    public static void main(String[] args) {
        int[][] list = { {1,2,3},{4}, {5}, {},{},{}};

        List<List<Integer>> adj = new ArrayList<>();
        for (int[] arr : list) {
            List<Integer> subList = new ArrayList<>();
            for (int num : arr) {
                subList.add(num);
            }
            adj.add(subList);
        }

        System.out.println(bfsTraversal(list.length, adj));
    }

    public static List<Integer> bfsTraversal(int n, List<List<Integer>> adj){
        List<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()){
            int node = queue.poll();
            bfs.add(node);

            for(int it : adj.get(node)){
                if(!visited[it]){
                    visited[it] = true;
                    queue.offer(it);
                }
            }
        }

        return bfs;
    }
}

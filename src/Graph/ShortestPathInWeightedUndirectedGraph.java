package Graph;
import java.util.*;

class Pair5 {
    int first;
    int second;

    public Pair5(int first, int second){
        this.first = first;
        this.second = second;
    }
}
public class ShortestPathInWeightedUndirectedGraph {
    public static void main(String[] args) {
        int n = 5, m= 6;
        int[][] edges = {{1, 2, 2}, {2, 5, 5}, {2, 3, 4}, {1, 4, 1}, {4, 3, 3}, {3, 5, 1}};

        System.out.println(shortestPath(n,m,edges));
    }

    public static List<Integer> shortestPath(int n, int m, int edges[][]) {

        ArrayList<ArrayList<Pair5>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            adj.get(u).add(new Pair5(v, wt));
            adj.get(v).add(new Pair5(u, wt));
        }

        PriorityQueue<Pair5> pq = new PriorityQueue<Pair5>((x, y) -> x.first - y.first);
        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        dist[1] = 0;
        pq.add(new Pair5(0, 1));

        while (!pq.isEmpty()){

            Pair5 it = pq.poll();
            int dis = it.first;
            int node = it.second;

            for(Pair5 iter : adj.get(node)){
                int adjNode = iter.first;
                int edW = iter.second;
                if(dis + edW < dist[adjNode]){
                    dist[adjNode] = dis + edW;
                    pq.add(new Pair5(dis + edW, adjNode));
                    parent[adjNode] = node;
                }
            }
        }


        List<Integer> path = new ArrayList<>();
        if(dist[n] == Integer.MAX_VALUE){
            path.add(-1);
            return path;
        }

        int node = n;

        while (parent[node] != node){
            path.add(0,node);
            node = parent[node];
        }

        path.add(0,1);

        path.add(dist[n]);
        return path;
    }
}

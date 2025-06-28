package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

class Pair1 {
    int distance;
    int node;

    public Pair1(int dist, int val){
        this.distance = dist;
        this.node = val;
    }
}
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        int V = 3;
        int[][] edges = {{0, 1, 1}, {1, 2, 3}, {0, 2, 6}};
        int src = 2;


        System.out.println(Arrays.toString(dijkstra(V, edges, src)));

    }

    // Time Complexity : O(E*logV)
    // Space Complexity : O(V)
    public static int[] dijkstra(int V, int[][] edges, int src) {

        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            // Add edge u -> v
            ArrayList<Integer> inner1 = new ArrayList<>();
            inner1.add(v);
            inner1.add(wt);
            adj.get(u).add(inner1);

            // Add edge v -> u (since undirected)
            ArrayList<Integer> inner2 = new ArrayList<>();
            inner2.add(u);
            inner2.add(wt);
            adj.get(v).add(inner2);

        }

        PriorityQueue<Pair1> pq = new PriorityQueue<>((x,y) -> x.distance - y.distance);
        int[] dist = new int[V];
//        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new Pair1(0, src));

        while (!pq.isEmpty()){
            Pair1 p = pq.poll();
            int dis = p.distance;
            int node = p.node;

            for (int i = 0; i < adj.get(node).size(); i++) {
                int weight = adj.get(node).get(i).get(1);
                int adjNode = adj.get(node).get(i).get(0);

                if(dis + weight < dist[adjNode]){
                    dist[adjNode] = dis + weight;
                    pq.add(new Pair1(dist[adjNode], adjNode));
                }
            }
        }

        return dist;

    }
}

package Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Destination {
    long first;
    int second;

    public Destination(long first, int second){
        this.first = first;
        this.second = second;
    }
}
public class NumberOfWaysToArriveAtDestination {
    public static void main(String[] args) {
        int n = 7;
        int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};

        System.out.println(countPaths(n, roads));
    }

    public static int countPaths(int n, int[][] roads) {

        ArrayList<ArrayList<Destination>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        int m = roads.length;
        for (int i = 0; i < m; i++) {
            int u = roads[i][0];
            int v = roads[i][1];
            int d = roads[i][2];

            adj.get(u).add(new Destination(v,d));
            adj.get(v).add(new Destination(u,d));
        }

        PriorityQueue<Destination> pq = new PriorityQueue<>((x,y) -> Long.compare(x.first, y.first));
        long[] dist = new long[n];
        int[] ways = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Long.MAX_VALUE;
            ways[i] = 0;
        }

        dist[0] = 0;
        ways[0] = 1;
        pq.add(new Destination(0,0));
        int mod = (int) 1e9 + 7;
        while (!pq.isEmpty()){
            Destination it = pq.poll();
            long dis = it.first;
            int node = it.second;

            for (Destination iter : adj.get(node)){
                int adjNode = iter.second;
                long edw = iter.first;

                if(dis + edw < dist[adjNode]){
                    dist[adjNode] = dis + edw;
                    ways[adjNode] = ways[node];
                    pq.add(new Destination(dis + edw, adjNode));
                } else if (dis + edw == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }

        return ways[n - 1] % mod;
    }
}

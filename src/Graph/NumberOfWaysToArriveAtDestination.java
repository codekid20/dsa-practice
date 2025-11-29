package Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
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

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];
            adj.get(u).add(new Destination(d, v));
            adj.get(v).add(new Destination(d, u));
        }

        PriorityQueue<Destination> pq = new PriorityQueue<>((x,y) -> Long.compare(x.first, y.first));
        long[] dist = new long[n];
        int[] ways = new int[n];

        Arrays.fill(dist, Long.MAX_VALUE);
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

        return ways[n - 1];
    }
}

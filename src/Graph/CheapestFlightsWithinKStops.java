package Graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Flights1 {
    int first;
    int second;

    public Flights1(int first, int second){
        this.first = first;
        this.second = second;
    }
}

class Flights {
    int stop;
    int node;
    int cost;

    public Flights(int stop, int node, int cost){
        this.stop = stop;
        this.node = node;
        this.cost = cost;
    }
}

public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        int n = 5;
        int[][] flights = {{4,1,1},{1,2,3},{0,3,2},{0,4,10},{3,1,1},{1,4,3}};
        int src = 2, dst = 1, k = 1;

        System.out.println(findCheapestPrice(n, flights, src, dst, k));
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        ArrayList<ArrayList<Flights1>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int m = flights.length;
        for (int i = 0; i < m; i++) {
            int u = flights[i][0];
            int v = flights[i][1];
            int c = flights[i][2];

            adj.get(u).add(new Flights1(v, c));
        }

        Queue<Flights> q = new LinkedList<>();

        // {stops, node, cost}
        q.add(new Flights(0 ,src, 0));

        int[] dist = new int[n];

        Arrays.fill(dist, (int) 1e9);

        dist[src] = 0;

        while (!q.isEmpty()){

            Flights it = q.poll();
            int stops = it.stop;
            int node = it.node;
            int cost = it.cost;

            if(stops > k) continue;
            for(Flights1 iter : adj.get(node)){
                int adjNode = iter.first;
                int edgeCost = iter.second;

                if(cost + edgeCost < dist[adjNode] && stops <= k){
                    dist[adjNode] = cost + edgeCost;
                    q.add(new Flights(stops + 1, adjNode, cost + edgeCost));
                }
            }
        }

        if(dist[dst] == (int) 1e9) return -1;
        return dist[dst];

    }
}

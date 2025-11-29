package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Time {

    int node;
    int dist;

    public Time (int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}
public class NetWorkDelayTime {
    public static void main(String[] args) {

    }

    public int networkDelayTime(int[][] times, int n, int k) {

        ArrayList<ArrayList<Time>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] time : times) {

            int u = time[0];
            int v = time[1];
            int wt = time[2];

            adj.get(u).add(new Time(v, wt));
        }


        PriorityQueue<Time> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, (int) 1e9);

        dist[k] = 0;

        pq.add(new Time(k, 0));

        while (!pq.isEmpty()) {

            Time t = pq.poll();
            int time = t.dist;
            int node = t.node;

            for(Time it : adj.get(node)) {

                int next = it.node;
                int w = it.dist;

                if(time + w < dist[next]) {
                    dist[next] = time + w;
                    pq.add(new Time(next, time + w));
                }
            }
        }

        int maxTime = 0;

        for (int i = 0; i <= n; i++) {
            if(dist[i] == (int) 1e9) return -1;
            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }
}

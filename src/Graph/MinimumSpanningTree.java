package Graph;
import java.util.*;

// Prim's Algorithm

class MSP {
    int node;
    int distance;

    public MSP(int node, int distance){
        this.node = node;
        this.distance = distance;
    }
}
public class MinimumSpanningTree {
    public static void main(String[] args) {

    }
    public static int spanningTree(int V, int E, List<List<int[]>> adj) {

        PriorityQueue<MSP> pq = new PriorityQueue<MSP>((a,b) -> a.distance - b.distance);

        int[] vis = new int[V];

        int sum = 0;

        pq.add(new MSP(0, 0));


        while (!pq.isEmpty()) {
            MSP removed = pq.remove();
            int wt = removed.distance;
            int node = removed.node;

            if (vis[node] == 1) continue;

            vis[node] = 1;
            sum += wt;

            for (int i = 0; i < adj.get(node).size(); i++) {
                int edW = adj.get(node).get(i)[1];
                int adjNode = adj.get(node).get(i)[0];

                if (vis[adjNode] == 0) {
                    pq.add(new MSP(adjNode, edW));
                }
            }
        }


        return sum;
    }
}

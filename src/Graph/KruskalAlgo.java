package Graph;

import java.util.Arrays;
import java.util.Comparator;

class DSU {
    private int[] rank, parent;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = i;
        }
    }

    public int find(int i) {
        if(parent[i] != i) {
            parent[i] = find(parent[i]);
        }

        return parent[i];
    }

    public void union(int x, int y) {

        int s1 = find(x);
        int s2 = find(y);

        if(s1 != s2) {
            if(rank[s1] < rank[s2]) parent[s1] = s2;
            else if (rank[s1] > rank[s2]) {
                parent[s2] = s1;
            } else {
                parent[s2] = s1;
                rank[s1]++;
            }
        }
    }
}
public class KruskalAlgo {
    public static void main(String[] args) {

    }

    public static int kruskalsMST(int V, int[][] edges) {

        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));

        DSU dsu = new DSU(V);

        int cost = 0;
        int count = 0;

        for(int[] e : edges) {
            int x = e[0], y = e[1], w = e[2];

            if(dsu.find(x) != dsu.find(y)){
                dsu.union(x,y);
                cost += w;
                if(++count == V - 1) break;
            }
        }

        return cost;
    }
}

package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfProvinces {
    public static void main(String[] args) {
        int[][] isConnected = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        System.out.println(findCircleNum(isConnected));
    }

    // Time Complexity: o(n^2)
    // Space Complexity: o(n)
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int count = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                count++;
                dfs(i,isConnected,visited);
            }
        }

        return count;
    }

    public static void dfs(int node, int[][] isConnected, boolean[] visit){
        visit[node] = true;

        for (int i = 0; i < isConnected.length; i++) {
            if(isConnected[node][i] == 1 && !visit[i]){
                dfs(i, isConnected,visit);
            }
        }
    }


    // Approach 2: BFS

    public static int findCircleNum1(int[][] isConnected) {
        int n = isConnected.length;
        int count = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                count++;
                bfs(i,isConnected,visited);
            }
        }

        return count;
    }

    public static void bfs(int node, int[][] isConnected, boolean[] visit){
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visit[node] = true;

        while (!q.isEmpty()){
            int no = q.poll();
            for (int i = 0; i < isConnected.length; i++) {
                if(isConnected[no][i] == 1 && !visit[i]){
                    q.offer(i);
                    visit[i] = true;
                }
            }
        }
    }
}

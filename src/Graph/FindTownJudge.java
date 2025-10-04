package Graph;

public class FindTownJudge {
    public static void main(String[] args) {
        int n = 2;
        int[][] trust = {{1,2}};
        System.out.println(findJudge(n, trust));
    }

//    🔹 Intuition
//
//    We can treat this as a graph problem:
//
//    Each person is a node.
//
//            A “trust” is a directed edge a → b.
//
//    Judge conditions:
//
//    Outdegree = 0 (doesn’t trust anyone)
//
//    Indegree = n - 1 (trusted by everyone)
    public static int findJudge(int n, int[][] trust) {

        int[] degree = new int[n + 1];

        for(int[] t : trust) {
            int u = t[0];
            int v = t[1];

            degree[u]--; // u trust someone loses a point
            degree[v]++; // v is trusted -> gain a point;
        }

        for (int i = 1; i <= n; i++) {
            if(degree[i] == n - 1) return i;
        }

        return -1;
    }
}

package Graph;
import java.util.*;
public class CourseSchedule2 {
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};

        System.out.println(Arrays.toString(findOrder(numCourses, prerequisites)));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        List<Integer>[] adj = new List[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] edge : prerequisites){
            int u = edge[0];
            int v = edge[1];

            adj[v].add(u);
        }

        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            for(int it : adj[i]){
                indegree[it]++;
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        int[] ans = new int[numCourses];
        int idx = 0;

        while (!queue.isEmpty()){
            int node = queue.poll();

            ans[idx++] = node;

            for(int it : adj[node]){
                indegree[it]--;
                if(indegree[it] == 0){
                    queue.add(it);
                }
            }
        }

        return ans;
    }
}

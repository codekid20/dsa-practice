package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0},{0,1}};

        System.out.println(canFinish(numCourses, prerequisites));
    }


    // Topological Sorting.
    // Generate Topological sort. If in the end there are nodes left means it has cycle and hence cyclic dependency.

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        List<Integer>[] adj = new List[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] edge : prerequisites){
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
        }

        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++){
            for (int it : adj[i]){
                indegree[it]++;
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        int count = 0;

        while (!queue.isEmpty()){
            int node = queue.poll();

            count++;

            for (int it : adj[node]){
                indegree[it]--;
                if(indegree[it] == 0){
                    queue.add(it);
                }
            }
        }

        return count == numCourses;
    }
}

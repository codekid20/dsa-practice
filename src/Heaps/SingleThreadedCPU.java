package Heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SingleThreadedCPU {
    public static void main(String[] args) {
        int[][] tasks = {{7,10},{7,12},{7,5},{7,4},{7,2}};
//        System.out.println(Arrays.toString(getOrder(tasks)));
        getOrder(tasks);
    }

    public static int[] getOrder(int[][] tasks) {

        int[][] pool = new int[tasks.length][3];

        for (int i = 0; i < tasks.length; i++) {
            pool[i][0] = tasks[i][0];
            pool[i][1] = tasks[i][1];
            pool[i][2] = i;
        }

        Arrays.sort(pool, (a,b) -> a[0] - b[0]);
        PriorityQueue<int[]> available = new PriorityQueue<>((a,b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);

        int time = 0;
        int index = 0;
        int i = 0;
        int[] res = new int[tasks.length];

        while (index < tasks.length){
            if(available.isEmpty()){
                time = Math.max(time, pool[i][0]);
            }

            while (i < tasks.length && time >= pool[i][0]){
                available.offer(pool[i++]);
            }

            int[] curTask = available.poll();
            res[index++] = curTask[2];
            time += curTask[1];
        }
        return res;
    }
}

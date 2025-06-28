package Heaps;

import java.util.PriorityQueue;

public class FurthestBuildingYouCanReach {
    public static void main(String[] args) {
        int[] heights = {4,12,2,7,3,18,20,3,19};
        int bricks = 10, ladders = 2;
        System.out.println(furthestBuilding(heights, bricks, ladders));
    }

    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);

        for (int i = 0; i < heights.length - 1; i++) {
            if(heights[i] > heights[i+1]){
                continue;
            }

            bricks = bricks - (heights[i + 1] - heights[i]);
            pq.offer(heights[i+1] - heights[i]);

            if(bricks < 0){
                bricks += bricks + pq.poll();
                if (ladders > 0) {
                    ladders--;
                } else {
                    return i;
                }
            }
        }

        return heights.length - 1;
    }
}

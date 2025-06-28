package Heaps;

import java.util.HashMap;
import java.util.PriorityQueue;

public class LeastNumberOfUniqueIntegersafterKRemovals {
    public static void main(String[] args) {
        int[] arr = {4,3,1,1,3,3,2};
        int k = 3;
        System.out.println(findLeastNumOfUniqueInts(arr, k));
    }

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr){
            map.put(i, map.getOrDefault(i,0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(map.values());

        while(k > 0){
            k = k - pq.poll(); // ON EACH REMOVAL WE ARE REMOVING FREQUENCY AND NOT ELEMENT. SO IT COULD BE POSSIBLE THAT WE COULD END UP DELETE MORE THEN K elements. Hence k could become less then 0.
        }

        return k < 0 ? pq.size() + 1 : pq.size();
    }
}

package Heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class FrequencySort {
    public static void main(String[] args) {
        int[] arr = {1,2,1,1,2,3,4,3,4,5};
        System.out.println(freqSort(arr));
    }

    public static List<Integer> freqSort(int[] nums){
        List<Integer> ans = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> map.get(a[1]) == map.get(b[1]) ? a[0] - b[0] : map.get(a[1]) - map.get(b[1]));

        for (int num : map.keySet()){
            pq.offer(new int[]{num,map.get(num)});
        }

        while (!pq.isEmpty()){
            int[] curr = pq.poll();
            int freq = curr[1];
            int num = curr[0];

            for(int i = 0; i < freq; i++){
                ans.add(num);
            }
        }
        return ans;
    }
}

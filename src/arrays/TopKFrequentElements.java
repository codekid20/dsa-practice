package arrays;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent2(nums,k)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num,0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));

        for(int num : map.keySet()){
            pq.add(num);

            if(pq.size() > k){
                pq.poll();
            }
        }

        int[] ans = new int[k];

        for(int i = k-1; i >= 0; i--){
            ans[i] = pq.poll();
        }

        return ans;
    }

    public static int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num,map.getOrDefault(num,0) + 1);
        }

        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        for (int i = 0; i < bucket.length; i++){
            bucket[i] = new ArrayList<>();
        }

        for(int num : map.keySet()){
            bucket[map.get(num)].add(num);
        }

        List<Integer> answer = new ArrayList<>();
        for(int i = bucket.length - 1; i >= 0 && answer.size() < k; i--){
            if(!bucket[i].isEmpty()){
                answer.addAll(bucket[i]);
            }
        }

        int[] ans = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }

        return ans;
    }
}

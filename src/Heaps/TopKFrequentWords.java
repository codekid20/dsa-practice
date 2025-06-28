package Heaps;

import java.util.*;

public class TopKFrequentWords {
    public static void main(String[] args) {

    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for(String word : words){
            map.put(word, map.getOrDefault(word,0)+1);
        }


        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> map.get(a).equals(map.get(b)) ? b.compareTo(a) : map.get(a) - map.get(b));

        for (String word : map.keySet()){
            pq.offer(word);

            if(pq.size() > k){
                pq.poll();
            }
        }


        while (!pq.isEmpty()){
            ans.add(0,pq.poll());
        }

        return ans;
    }
}

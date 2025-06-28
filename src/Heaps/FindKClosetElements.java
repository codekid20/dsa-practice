package Heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FindKClosetElements {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int k = 4, x = 3;

        System.out.println(findClosestElements4(arr, k, x));
    }

//    Approach 1: Using Priority Queue

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> Math.abs(a - x) == Math.abs(b - x) ? b - a : Math.abs(b-x) - Math.abs(a-x));

        for(int num : arr){
            pq.offer(num);

            if(pq.size() > k){
                pq.poll();
            }
        }

        while (!pq.isEmpty()){
            ans.add(pq.poll());
        }

        Collections.sort(ans);
        return ans;

    }


    // Approach 2: Using Binary Search

    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int right = arr.length - k;

        while(left < right){
            int mid  = left + (right - left) / 2;

            if(x - arr[mid] > arr[mid + k] - x){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        for (int i = left; i < left + k; i++){
            ans.add(arr[i]);
        }

        return ans;
    }


    // Approach 3: Using Heap with pair

    public static List<Integer> findClosestElements3(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.diff == b.diff ? a.index - b.index : a.diff - b.diff);

        for(int i = 0; i < arr.length; i++){
            pq.offer(new Pair(arr[i], Math.abs(arr[i] - x), i));
        }

        while (!pq.isEmpty() && k > 0){
            ans.add(pq.poll().value);
            k--;
        }

        Collections.sort(ans);
        return ans;
    }

    static class Pair{
        int value;
        int diff;
        int index;

        public Pair(int value, int diff, int index){
            this.value = value;
            this.diff = diff;
            this.index = index;
        }
    }



    // Approach 4: Using Heap

    public static List<Integer> findClosestElements4(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[0] != b[0]){
                return b[0] - a[0];
            } else {
                return b[1] - a[1];
            }
        });

        for (int num : arr){
            int dist = Math.abs(num - x);
            pq.offer(new int[]{dist,num});


            if(pq.size() > k){
                pq.poll();
            }
        }

        while (!pq.isEmpty()){
            ans.add(pq.poll()[1]);
        }

        Collections.sort(ans);

        return ans;
    }
}

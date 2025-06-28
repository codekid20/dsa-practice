package general.hashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        HashSet<Integer> set = new HashSet<>();
//        map.put(1,11);
//        map.put(2,22);
//        map.put(3,33);
//        map.put(4,44);

//        map.put(1,1);
//        map.put(1,0);
//        System.out.println(map.keySet());
//        System.out.println(map.values());
//        System.out.println(map.get(3));
//        System.out.println(map.get(1));
//        System.out.println(map.remove(4));
//        System.out.println(map.remove(3,33));
//        map.clear();
//        System.out.println(map.size());

//        System.out.println(map.get(1));

//        set.add(1);
//        System.out.println(set.add(1));


        TreeMap<String, Integer> map = new TreeMap<>();
        String[] words = {"i","love","leetcode","i","love","coding"};

        for (String word : words){
            map.put(word, map.getOrDefault(word,0) + 1);
        }

        System.out.println(map.keySet());
        System.out.println(map.values());
    }
}

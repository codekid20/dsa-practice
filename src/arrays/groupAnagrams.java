package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class groupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strs); // Call the groupAnagrams method
        System.out.println(result);

//        char[] arr = {'a', 'd', 'i', 't', 'y', 'a'};
//        String s = new String(arr);
//        System.out.println(s);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> groupedAnagrams = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (String s: strs){
            char[] sorted = s.toCharArray();
            Arrays.sort(sorted);
            String str = new String(sorted);
            if(!map.containsKey(str)){
                map.put(str, new ArrayList<>());
            }
            map.get(str).add(s);
        }

        groupedAnagrams.addAll(map.values());
        return groupedAnagrams;
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = new char[26];
            for (char c : s.toCharArray()) ca[c - 'a']++;
            String keyStr = String.valueOf(ca);
            System.out.println(keyStr);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }
}

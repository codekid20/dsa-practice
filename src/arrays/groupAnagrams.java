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
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> groupedAnagrams = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] characters = s.toCharArray();
            System.out.println(characters);
            Arrays.sort(characters); // Sort the char array
            String sorted = new String(characters); // Convert the sorted char array back to a string
//            System.out.println(sorted);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(s);
        }

        groupedAnagrams.addAll(map.values());
        return groupedAnagrams;
    }

//    public static List<List<String>> groupAnagrams(String[] strs) {
//        if (strs == null || strs.length == 0) return new ArrayList<>();
//        HashMap<String, List<String>> map = new HashMap<>();
//        for (String s : strs) {
//            char[] ca = new char[26];
//            for (char c : s.toCharArray()) ca[c - 'a']++;
//            String keyStr = String.valueOf(ca);
//            System.out.println(keyStr);
//            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
//            map.get(keyStr).add(s);
//        }
//        return new ArrayList<>(map.values());
//    }
}

package slidingWindow;

import java.util.Arrays;
import java.util.HashMap;

public class PermutationInString {
    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";

        System.out.println(checkInclusion(s1,s2));
    }

    public static boolean checkInclusion(String s1, String s2) {

        s1 = sort(s1);
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            if(s1.equals(sort(s2.substring(i, i + s1.length())))) {
                return true;
            }
        }

        return false;
    }

    private static String sort(String s) {
        char[] t =s.toCharArray();
        Arrays.sort(t);

        return new String(t);
    }


    // 2: Using HashMap

    public static boolean checkInclusion1(String s1, String s2) {

        if(s1.length() > s2.length()) {
            return false;
        }

        HashMap<Character, Integer> s1map = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            s1map.put(s1.charAt(i), s1map.getOrDefault(s1.charAt(i), 0) + 1);
        }

        for (int i = 0; i <= s2.length() - s1.length(); i++) {

            HashMap<Character, Integer> s2map = new HashMap<>();

            for (int j = 0; j < s1.length(); j++) {

                s2map.put(s2.charAt(i + j), s2map.getOrDefault(s2.charAt(i + j), 0) + 1);
            }

            if(matches(s1map, s2map)){
                return true;
            }
        }

        return false;
    }

    private static boolean matches(HashMap<Character, Integer> s1map, HashMap<Character, Integer> s2map) {

        for(char key : s1map.keySet()){
            if(s1map.get(key) - s2map.getOrDefault(key, -1) != 0) {
                return false;
            }
        }

        return true;
    }


    public static boolean checkInclusion2(String s1, String s2) {

        if(s1.length() > s2.length()) {
            return false;
        }

        int[] s1arr = new int[26];
        int[] s2arr = new int[26];

        for(int i = 0; i < s1.length(); i++) {
            s1arr[s1.charAt(i) - 'a']++;
            s2arr[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if(matches1(s1arr,s2arr)){
                return true;
            }

            s2arr[s2.charAt(i + s1.length()) - 'a']++;
            s2arr[s2.charAt(i) - 'a']--;
        }

        return matches1(s1arr,s2arr);
    }

    private static boolean matches1(int[] s1arr, int[] s2arr) {
        for (int i = 0; i < 26; i++) {
            if(s1arr[i] != s2arr[i]) return false;
        }

        return true;
    }
}

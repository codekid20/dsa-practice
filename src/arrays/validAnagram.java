package arrays;

import java.util.Arrays;

public class validAnagram {
    public static void main(String[] args) {
        String s = "a";
        String t = "ab";

        System.out.println(isAnagram(s, t));
    }

    // Approach 1: Can be done using hashmap also
    public static boolean isAnagram(String s, String t) {
        int[] charArray = new int[26];
        for(char c : s.toCharArray()){
            charArray[c - 'a']++;
        }

        for (char c : t.toCharArray()){
            charArray[c - 'a']--;
        }

        for (int i = 0; i < 26; i++){
            if(charArray[i] != 0){
                return false;
            }
        }

        return true;
    }

    // Approach 2: Sorting
    public boolean isAnagram2(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }
}

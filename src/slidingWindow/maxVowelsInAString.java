package slidingWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class maxVowelsInAString {
    public static void main(String[] args) {
        String s = "leetcode";
        int k = 3;
        System.out.println(maxVowels2(s, k));
    }

    // Sliding Window of size k
    // Count vowels in current window
    // When window slides forward -> add new char (right), remove old char (left)
    // Track max vowel count seen across all windows


    public static int maxVowels(String s, int k) {
         HashSet<Character> map = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
         int ans = 0;
         int count = 0;

         for(int i = 0; i < s.length(); i++){
             if(map.contains(s.charAt(i))){
                 count++;
             }

             if(i >= k && map.contains(s.charAt(i - k))){
                 count--;
             }

             ans = Math.max(ans, count);
         }
         return ans;
    }

    public static int maxVowels2(String s, int k){
        HashSet<Character> set = new HashSet<>(Arrays.asList('a','e','i','o','u'));
        int ans = 0;
        int count = 0;

        for(int i = 0; i < k; i++){
            count += set.contains(s.charAt(i)) ? 1 : 0;
        }
        ans = count;

        for(int i = k; i < s.length(); i++){
            count += set.contains(s.charAt(i)) ? 1 : 0;
            count -= set.contains(s.charAt(i-k)) ? 1 : 0;
            ans = Math.max(count, ans);
        }

        return ans;
    }
}

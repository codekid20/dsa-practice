package slidingWindow;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String str = "pwwkew";
        int ans = lengthOfLongestSubstring(str);
        System.out.println(ans);
    }

    // Sliding Window + HashMap
    // Expand right and count chars
    // If a char repeats (freq > 1), shrink from left until valid
    // Track max window length with all unique chars

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLen = 0;

        while(right < s.length()) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c,0) + 1);

            while(map.get(c) > 1) {
                char cc = s.charAt(left);
                map.put(cc, map.get(cc) - 1);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }
}

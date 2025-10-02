package slidingWindow;

import java.util.HashMap;

public class longestSubstringWithAtMostKDistinctCharacters {
    public static void main(String[] args) {
        String str = "abbbbbbc";
        int k = 2;
        System.out.println(kDistinctChars2(k, str));
    }

    // Sliding Window + HashMap
    // Expand window by moving right and count chars in map
    // If distinct chars exceed k -> shrink window from left until valid
    // Keep track of max window length that satisfies <= k distinct chars

    public static int kDistinctChars(int k, String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLen = 0;

        while(right < str.length()){
            map.put(str.charAt(right), map.getOrDefault(str.charAt(right), 0) + 1);

            while(map.size() > k){
                map.put(str.charAt(left), map.getOrDefault(str.charAt(left),0) - 1);
                if(map.get(str.charAt(left)) == 0){
                    map.remove(str.charAt(left));
                }

                left = left + 1;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right = right + 1;
        }

        return maxLen;
    }

    public static int kDistinctChars2(int k, String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLen = 0;

        while(right < str.length()){
            map.put(str.charAt(right), map.getOrDefault(str.charAt(right), 0) + 1);

            if(map.size() > k){
                map.put(str.charAt(left), map.getOrDefault(str.charAt(left),0) - 1);
                if(map.get(str.charAt(left)) == 0){
                    map.remove(str.charAt(left));
                }

                left = left + 1;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right = right + 1;
        }

        return maxLen;
    }
}

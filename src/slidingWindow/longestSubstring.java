package slidingWindow;

import java.util.HashMap;

public class longestSubstring {
    public static void main(String[] args) {
        String str = "pwwkew";
        System.out.println(lengthOfLongestSubstring(str));
    }

    private static int lengthOfLongestSubstring(String str) {
        HashMap<Character, Integer> chars = new HashMap<>();

        int left = 0;
        int right = 0;
        int ans = 0;

        while(right < str.length()){
            char r = str.charAt(right);
            chars.put(r, chars.getOrDefault(r,0) + 1);

            while(chars.get(r) > 1){
                char l = str.charAt(left);
                chars.put(l, chars.get(l) - 1);
                left++;
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}

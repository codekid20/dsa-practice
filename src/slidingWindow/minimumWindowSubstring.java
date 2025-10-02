package slidingWindow;

import java.util.HashMap;

public class minimumWindowSubstring {
    public static void main(String[] args) {
        String s1 = "ADOBECODEBANC";
        String s2 = "ABC";
        System.out.println(minWindow(s1, s2));

    }

    public int findSubstring(String s) {
        int[] map = new int[128]; // hash map (for ASCII chars)
        int counter = 0; // used to check whether substring is valid
        int begin = 0, end = 0; // two pointers
        int d = 0; // length of substring result (depends on min/max logic)

        // Example: initialize map here (depends on problem statement)
        // for (char c : t.toCharArray()) {
        //     map[c]++;
        //     counter++;
        // }

        while (end < s.length()) {
            char cEnd = s.charAt(end++);
            if (map[cEnd]-- > 0) {
                // modify counter here
                counter--;
            }

            while (counter == 0) { // condition when substring is valid
                // update d here if finding minimum substring
                // d = Math.min(d, end - begin);

                char cBegin = s.charAt(begin++);
                if (map[cBegin]++ >= 0) {
                    // modify counter here
                    counter++;
                }
            }

            // update d here if finding maximum substring
            // d = Math.max(d, end - begin);
        }

        return d;
    }


    public static String minWindow(String s, String t) {

        int n = s.length();
        int m = t.length();
        int l = 0;
        int r = 0;
        int minLen = Integer.MAX_VALUE;
        int sidx = -1;
        int count = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < m; i++) {

            map.put(t.charAt(i), map.getOrDefault(t.charAt(i),0) + 1);
        }

        while(r < n){
            if(map.getOrDefault(s.charAt(r), 0) > 0) count += 1;

            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) - 1);

            while (count == m){
                if(r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    sidx = l;
                }

                map.put(s.charAt(l), map.getOrDefault(s.charAt(l), 0) + 1);

                if(map.get(s.charAt(l)) > 0) count -= 1;
                l++;
            }

            r = r + 1;
        }

        return sidx == -1 ? "" : s.substring(sidx, sidx + minLen);

    }
}

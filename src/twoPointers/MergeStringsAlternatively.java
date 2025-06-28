package twoPointers;

public class MergeStringsAlternatively {
    public static void main(String[] args) {
        String word1 = "ab";
        String word2 = "pqrs";

        System.out.println(mergeAlternately(word1, word2));
    }

    public static String mergeAlternately(String word1, String word2) {
        int p1 = 0;
        int p2 = 0;
        StringBuilder sb = new StringBuilder();
        boolean reverse = false;

        while (p1 < word1.length() && p2 < word2.length()){
            if(!reverse){
                sb.append(word1.charAt(p1));
                p1++;
            } else {
                sb.append(word2.charAt(p2));
                p2++;
            }
            reverse = !reverse;
        }

        if(p1 < word1.length()){
            while(p1 < word1.length()){
                sb.append(word1.charAt(p1));
                p1++;
            }
        }

        if(p2 < word2.length()){
            while(p2 < word2.length()){
                sb.append(word2.charAt(p2));
                p2++;
            }
        }

        return sb.toString();
    }

    // Approach 2: More optimized and better code
    // Time O(n + m)
    // Space O(n + m)
    public static String mergeAlternately2(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int idx = 0;
        StringBuilder sb = new StringBuilder();

        while (idx < len1 || idx < len2){
            if (idx < len1){
                sb.append(word1.charAt(idx));
            }

            if (idx < len2){
                sb.append(word1.charAt(idx));
            }

            idx++;
        }
        return sb.toString();
    }
}

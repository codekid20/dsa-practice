package Graph;

public class VerifyingAnAlienDictionary {
    public static void main(String[] args) {
        String[] words = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";

        System.out.println(isAlienSorted(words,order));

    }
    public static boolean isAlienSorted(String[] words, String order) {

        int[] rank = new int[26];
        for (int i = 0; i < order.length(); i++) {
            rank[order.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) {
            if(!isSorted(words[i], words[i + 1], rank)) return false;
        }

        return true;
    }

    private static boolean isSorted(String word, String word1, int[] rank) {

        int len1 = word.length();
        int len2 = word1.length();

        int minLen = Math.min(len1,len2);

        for (int i = 0; i < minLen; i++) {

            char c1 = word.charAt(i);
            char c2 = word1.charAt(i);
            if( c1 != c2) {
                return rank[c1 - 'a'] < rank[c2 - 'a'];
            }
        }

        return len1 <= len2;
    }
}

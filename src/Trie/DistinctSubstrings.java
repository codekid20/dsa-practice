package Trie;


// Key Insight
//
//        The total number of substrings of a string of length n is n * (n+1) / 2 + 1 (including empty string).
//
//        But we want distinct substrings.
//
//        We can use a Trie to store all substrings.
//
//        Every time we insert a new substring into the Trie, we check if we created a new node.
//
//        The number of new nodes created = number of distinct substrings (excluding empty string).
//
//        Then add 1 for the empty string.

class TrieNode3 {

    TrieNode3[] children = new TrieNode3[26];
}
public class DistinctSubstrings {

    private static TrieNode3 root;
    private static int count;

    public DistinctSubstrings(){
        root = new TrieNode3();
        count = 0;
    }

    public static int countDistinctSubstrings(String s) {

        int n = s.length();

        for (int i = 0; i < n; i++) {
            TrieNode3 node = root;
            for (int j = i; j < n; j++) {
                int index = s.charAt(j) - 'a';
                if(node.children[index] == null){
                    node.children[index] = new TrieNode3();
                }
                node = node.children[index];
                count++;
            }
        }

        return count + 1;
    }
}

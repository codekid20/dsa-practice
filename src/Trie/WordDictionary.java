package Trie;

public class WordDictionary {

    private static class TrieNode2 {
        TrieNode2[] children  = new TrieNode2[26];
        boolean isEndOfWord = false;
    }

    private TrieNode2 root;

    public WordDictionary() {
        root = new TrieNode2();
    }

    public void addWord(String word) {

        TrieNode2 node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode2();
            }

            node = node.children[index];
        }

        node.isEndOfWord = true;
    }

    public boolean search(String word) {

        return dfsSearch(word, 0, root);
    }

    private boolean dfsSearch(String word, int pos, TrieNode2 node) {
        if(node == null) return false;
        if(pos == word.length()){
            return node.isEndOfWord;
        }

        char c = word.charAt(pos);
        if(c == '.'){
            for(TrieNode2 child : node.children){
                if(dfsSearch(word, pos + 1, child)){
                    return true;
                }
            }

            return false;
        } else {
            int index = c - 'a';
            return dfsSearch(word, pos + 1, node.children[index]);
        }
    }
}

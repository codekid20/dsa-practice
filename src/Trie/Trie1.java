package Trie;

class TrieNode1 {

    TrieNode1[] children = new TrieNode1[26];

    int wordCount;
    int prefixCount;

    TrieNode1() {
        wordCount = 0;
        prefixCount = 0;
    }
}

public class Trie1 {

    private TrieNode1 root;

    Trie1() {
        root = new TrieNode1();
    }

    public void insert(String word) {

        TrieNode1 node = root;
        for(char c : word.toCharArray()) {
            int index = c - 'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode1();
            }

            node = node.children[index];
            node.prefixCount++;
        }

        node.wordCount++;
    }

    public int countWordsEqualTo(String word) {

        TrieNode1 node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if(node.children[index] == null){
                return 0;
            }

            node = node.children[index];
        }

        return node.wordCount;
    }

    public int countWordsStartingWith(String word) {

        TrieNode1 node = root;
        for (char c : word.toCharArray()){
            int index = c - 'a';
            if(node.children[index] == null) {
                return 0;
            }

            node = node.children[index];
        }

        return node.prefixCount;
    }

    public void erase(String word) {

        TrieNode1 node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            TrieNode1 child = node.children[index];
            if(child == null) return;
            child.prefixCount--;
            node = child;
        }
        node.wordCount--;
    }
}

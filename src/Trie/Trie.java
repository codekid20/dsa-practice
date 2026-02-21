package Trie;
/*
 * TRIE (PREFIX TREE) DATA STRUCTURE
 *
 * WHAT IS A TRIE?
 * ---------------
 * A Trie (pronounced "try") is a tree-based data structure used for efficient
 * storage and retrieval of strings, particularly for PREFIX-based operations.
 *
 * Also called:
 *   - Prefix Tree
 *   - Digital Tree
 *   - Radix Tree (variant)
 *
 * WHY USE A TRIE?
 * ---------------
 * Problem: Given a dictionary of words, we need to:
 *   1. Check if a word exists
 *   2. Find all words with a given prefix
 *   3. Auto-complete functionality
 *   4. Spell checking
 *
 * Naive Approach (using ArrayList<String>):
 *   - Search: O(n × m) where n = number of words, m = word length
 *   - Prefix search: O(n × m)
 *   - Space: O(total characters)
 *
 * Trie Approach:
 *   - Search: O(m) where m = word length
 *   - Prefix search: O(m)
 *   - Space: O(ALPHABET_SIZE × total nodes)
 *
 * STRUCTURE:
 * ----------
 * A Trie is a tree where:
 *   - Each NODE represents a single character
 *   - Each PATH from root to a node represents a string
 *   - Root node is empty (represents empty string)
 *   - Each node has up to ALPHABET_SIZE children (26 for lowercase a-z)
 *
 * VISUAL EXAMPLE:
 * ---------------
 * Words: ["cat", "car", "card", "care", "dog", "dodge", "doll"]
 *
 *                    root
 *                   /    \
 *                  c      d
 *                  |      |
 *                  a      o
 *                 / \    /|\
 *                t   r  g d l
 *               [*]  |\ [*]| |
 *                   d e   g l
 *                  [*][*] [*][*]
 *
 * [*] = isEndOfWord = true (valid word ends here)
 *
 * Key observations:
 *   - Common prefixes share nodes (space efficient!)
 *   - "ca" is shared by "cat", "car", "card", "care"
 *   - "do" is shared by "dog", "dodge", "doll"
 *
 * ==================== TRIE NODE STRUCTURE ====================
 */

class TrieNode {
    /*
     * CHILDREN ARRAY:
     * ---------------
     * children[0] → 'a'
     * children[1] → 'b'
     * ...
     * children[25] → 'z'
     *
     * Each index represents a character ('a' to 'z')
     * If children[i] is null → no word uses that character at this position
     * If children[i] is not null → there's a word continuing with that character
     */
    TrieNode[] children = new TrieNode[26];

    /*
     * END OF WORD FLAG:
     * -----------------
     * Marks if a valid word ENDS at this node
     *
     * Example: Insert "cat" and "catch"
     *   - At 't' in "cat": isEndOfWord = true
     *   - At 'h' in "catch": isEndOfWord = true
     *   - At 'c', 'a' (not end): isEndOfWord = false
     *
     * This distinguishes between:
     *   - A complete word: "cat"
     *   - Just a prefix: "ca" (not inserted as complete word)
     */
    boolean isEndOfWord;

    /*
     * CONSTRUCTOR:
     * ------------
     * Initialize a new node with:
     *   - All children set to null (no children yet)
     *   - isEndOfWord = false (not a word ending by default)
     */
    TrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}

/*
 * ==================== TRIE DATA STRUCTURE ====================
 */

public class Trie {

    /*
     * ROOT NODE:
     * ----------
     * The starting point of the Trie
     * Represents empty string ""
     * All words branch out from here
     */
    private TrieNode root;

    /*
     * CONSTRUCTOR:
     * ------------
     * Initialize Trie with an empty root node
     */
    public Trie() {
        root = new TrieNode();
    }

    /*
     * ==================== INSERT OPERATION ====================
     *
     * PURPOSE: Add a word to the Trie
     *
     * ALGORITHM:
     * ----------
     * 1. Start at root
     * 2. For each character in word:
     *    a. Calculate index (0-25) from character
     *    b. If child at index doesn't exist, create new node
     *    c. Move to that child node
     * 3. Mark last node as end of word
     *
     * TIME COMPLEXITY: O(m) where m = length of word
     * SPACE COMPLEXITY: O(m) in worst case (all new nodes)
     */
    public void insert(String word) {
        // Start traversal from root
        TrieNode node = root;

        // Process each character in the word
        for (char c : word.toCharArray()) {
            /*
             * CHARACTER TO INDEX CONVERSION:
             * ------------------------------
             * 'a' - 'a' = 0
             * 'b' - 'a' = 1
             * 'c' - 'a' = 2
             * ...
             * 'z' - 'a' = 25
             */
            int index = c - 'a';

            /*
             * CREATE NODE IF DOESN'T EXIST:
             * ------------------------------
             * If path for this character doesn't exist, create it
             */
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }

            /*
             * MOVE TO NEXT NODE:
             * ------------------
             * Traverse down the tree following the character path
             */
            node = node.children[index];
        }

        /*
         * MARK END OF WORD:
         * -----------------
         * After processing all characters, mark this node as word ending
         * This distinguishes "cat" from just having "ca" as a prefix
         */
        node.isEndOfWord = true;
    }

    /*
     * INSERTION EXAMPLE:
     * ------------------
     * Insert "cat":
     *
     * Step 1: Process 'c'
     *   root.children[2] = new TrieNode()  (create 'c' node)
     *   node = root.children[2]
     *
     * Step 2: Process 'a'
     *   node.children[0] = new TrieNode()  (create 'a' node under 'c')
     *   node = node.children[0]
     *
     * Step 3: Process 't'
     *   node.children[19] = new TrieNode() (create 't' node under 'a')
     *   node = node.children[19]
     *
     * Step 4: Mark end
     *   node.isEndOfWord = true  (mark 't' as word ending)
     *
     * Result:
     *   root → c → a → t[*]
     *
     * ==================== SEARCH OPERATION ====================
     *
     * PURPOSE: Check if a COMPLETE word exists in the Trie
     *
     * ALGORITHM:
     * ----------
     * 1. Start at root
     * 2. For each character in word:
     *    a. Calculate index
     *    b. If child at index doesn't exist, word not found → return false
     *    c. Move to that child
     * 3. After traversing all characters, check if current node is word ending
     *
     * DIFFERENCE FROM startsWith:
     * ---------------------------
     * search("cat") on Trie with ["catch"]:
     *   - Can traverse to 't', but isEndOfWord = false
     *   - Returns FALSE (not a complete word)
     *
     * startsWith("cat") on same Trie:
     *   - Can traverse to 't'
     *   - Returns TRUE (valid prefix)
     *
     * TIME COMPLEXITY: O(m) where m = length of word
     * SPACE COMPLEXITY: O(1) - only uses pointer
     */
    public boolean search(String word) {
        // Start from root
        TrieNode node = root;

        // Traverse the path corresponding to the word
        for (char c : word.toCharArray()) {
            int index = c - 'a';

            /*
             * PATH DOESN'T EXIST:
             * -------------------
             * If we can't continue traversing, word doesn't exist
             */
            if(node.children[index] == null){
                return false;
            }

            // Move to next character's node
            node = node.children[index];
        }

        /*
         * CHECK IF COMPLETE WORD:
         * -----------------------
         * We reached the end of input word, but is it marked as complete?
         *
         * Example scenarios:
         *   - Trie has "catch", search("cat"):
         *     → Traverse succeeds, but node.isEndOfWord = false → return false
         *
         *   - Trie has "cat", search("cat"):
         *     → Traverse succeeds, node.isEndOfWord = true → return true
         */
        return node.isEndOfWord;
    }

    /*
     * SEARCH EXAMPLE:
     * ---------------
     * Trie contains: ["cat", "car", "card"]
     *
     * search("cat"):
     *   root → c → a → t[*]
     *   isEndOfWord = true → return TRUE ✓
     *
     * search("ca"):
     *   root → c → a
     *   isEndOfWord = false → return FALSE ✗
     *   (it's a prefix, but not a complete word)
     *
     * search("catch"):
     *   root → c → a → t → c (NULL)
     *   Path breaks → return FALSE ✗
     *
     * ==================== STARTS WITH OPERATION ====================
     *
     * PURPOSE: Check if any word in Trie starts with given prefix
     *
     * ALGORITHM:
     * ----------
     * 1. Start at root
     * 2. For each character in prefix:
     *    a. Calculate index
     *    b. If child doesn't exist, no word has this prefix → return false
     *    c. Move to that child
     * 3. If we successfully traverse entire prefix, return true
     *
     * KEY DIFFERENCE FROM search():
     * -----------------------------
     * startsWith() doesn't check isEndOfWord!
     * It only checks if the PATH exists in the Trie.
     *
     * TIME COMPLEXITY: O(m) where m = length of prefix
     * SPACE COMPLEXITY: O(1)
     */
    public boolean startsWith(String prefix) {
        // Start from root
        TrieNode node = root;

        // Try to traverse the prefix path
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';

            /*
             * PATH DOESN'T EXIST:
             * -------------------
             * If we can't continue, no word starts with this prefix
             */
            if(node.children[index] == null) {
                return false;
            }

            // Move to next character's node
            node = node.children[index];
        }

        /*
         * PREFIX EXISTS:
         * --------------
         * We successfully traversed the entire prefix
         * Some word(s) in the Trie start with this prefix
         *
         * Note: We don't check isEndOfWord here!
         * Because we only care if the prefix path exists,
         * not if it's a complete word.
         */
        return true;
    }

    /*
     * STARTS WITH EXAMPLE:
     * --------------------
     * Trie contains: ["cat", "car", "card", "dog"]
     *
     * startsWith("ca"):
     *   root → c → a
     *   Path exists → return TRUE ✓
     *   (Words "cat", "car", "card" all start with "ca")
     *
     * startsWith("cat"):
     *   root → c → a → t
     *   Path exists → return TRUE ✓
     *   (Even though "cat" is a complete word, the prefix exists)
     *
     * startsWith("do"):
     *   root → d → o
     *   Path exists → return TRUE ✓
     *   (Word "dog" starts with "do")
     *
     * startsWith("bat"):
     *   root → b (NULL)
     *   Path doesn't exist → return FALSE ✗
     */
}
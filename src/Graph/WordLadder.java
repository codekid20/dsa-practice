package Graph;
import java.util.*;
public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        List<String> words = new ArrayList<>(Arrays.asList(wordList));
        System.out.println(ladderLength(beginWord, endWord, words));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)){
            return 0;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int len = 0;
        queue.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();

            len++;

            for (int i = 0; i < size; i++) {

                String word = queue.poll();
                for (int j = 0; j < beginWord.length(); j++) {
                    char[] temp = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        temp[j] = ch;
                        String str = new String(temp);

                        if (str.equals(endWord)) {
                            return len + 1;
                        }

                        if (set.contains(str) && !visited.contains(str)) {
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
        }
        return 0;
    }
}

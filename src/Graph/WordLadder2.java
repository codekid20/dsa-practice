package Graph;
import java.util.*;
public class WordLadder2 {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        List<String> words = new ArrayList<>(Arrays.asList(wordList));
        System.out.println(findLadders(beginWord, endWord, words));
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> ans = new ArrayList<>();
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)){
            return ans;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String word = queue.poll();
                List<String> inner = new ArrayList<>();
                inner.add(word);
                for (int j = 0; j < word.length(); j++) {
                    char[] temp = word.toCharArray();
                        for (char ch = 'a'; ch <= 'z'; ch++){
                            temp[j] = ch;
                            String str = new String(temp);
                            if(str.equals(endWord)){
                                inner.add(str);
                                ans.add(inner);
                                return ans;
                            }

                            if(set.contains(str) && !visited.contains(str)){
                                queue.add(str);
                                inner.add(str);
                                visited.add(str);
                            }
                        }

                        ans.add(inner);
                }

            }
        }

        return ans;

    }
}

package slidingWindow;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

public class maxVowelsSubstring {
    public static void main(String[] args) {
        String s = "abciiidef";
        int k = 3;

        System.out.println(maxVowels(s,k));
    }

    private static int maxVowels(String s, int k) {
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u'));
        int ans = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            if(vowels.contains(s.charAt(i))){
                count++;
            }

            if(i >= k & vowels.contains(s.charAt(Math.abs(i-k)))){
                count--;
            }
            ans = Math.max(ans,count);
    }

//  Method 1
//    private static int maxVowels(String s, int k) {
//        HashSet<Character> vowels = new HashSet<>();
//        vowels.add('a');
//        vowels.add('e');
//        vowels.add('i');
//        vowels.add('o');
//        vowels.add('u');
//
//        int count = 0;
//        for(int i = 0; i < k; i++){
//            count += vowels.contains(s.charAt(i)) ? 1 : 0;
//        }
//        int answer = count;
//        for(int i = k; i < s.length(); i++){
//            count += vowels.contains(s.charAt(i)) ? 1 : 0;
//            count -= vowels.contains(s.charAt(i - k)) ? 1 : 0;
//            answer = Math.max(answer, count);
//        }
    return ans;
    }
}

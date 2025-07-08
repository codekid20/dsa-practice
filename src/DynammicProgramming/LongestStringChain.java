package DynammicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestStringChain {
    public static void main(String[] args) {
        String[] words = {"a","b","ba","bca","bda","bdca"};

        System.out.println(longestStrChain(words));
    }

    public static int longestStrChain(String[] words) {
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int len = 1;
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        for (int idx = 0; idx < n; idx++) {
            for (int prev = 0; prev < idx; prev++) {
                if(check(words[idx], words[prev]) && dp[idx] < 1 + dp[prev]){
                    dp[idx] = 1 + dp[prev];
                }
            }
            if(dp[idx] > len){
                len = dp[idx];
            }
        }
        return len;
    }

    private static boolean check(String s1, String s2) {

        if(s1.length() != s2.length() + 1) return false;

        int first = 0;
        int second = 0;

        while (first < s1.length()){
            if(second < s2.length() && s1.charAt(first) == s2.charAt(second)){
                first++;
                second++;
            } else {
                first++;
            }
        }

        return first == s1.length() && second == s2.length();
    }
}

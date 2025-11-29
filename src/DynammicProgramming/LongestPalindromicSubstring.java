package DynammicProgramming;

import java.util.Collections;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "aacabdkacaa";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {

        String reverse = new StringBuilder(s).reverse().toString();

        return longestCommonSubsequence(s, reverse);
    }

    public static String longestCommonSubsequence(String text1, String text2) {

        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n+1][m+1];

        for (int pos1 = 1; pos1 <= n; pos1++) {
            for (int pos2 = 1; pos2 <=  m; pos2++) {
                if(text1.charAt(pos1 - 1) == text2.charAt(pos2 - 1)){
                    dp[pos1][pos2] = 1 + dp[pos1 - 1][ pos2 - 1];
                } else {
                    dp[pos1][pos2] = 0;
                }
            }
        }

//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= m; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        int len = dp[n][m];

        char[] str = new char[len];
        int idx = len - 1;

        int i = n;
        int j = m;

        while (i > 0 && j > 0){
            if(text1.charAt(i-1) == text2.charAt(j-1)){
                str[idx] = text1.charAt(i-1);
                idx--;
                i--;
                j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }

//        System.out.println(Arrays.toString(str));

        // Character Array to String
//        System.out.println(new String(str));

        return new String(str);

    }

    public String longestPalindrome1(String s) {

        if(s == null || s.length() == 0) return "";

        String ans = "";

        for(int i = 0; i < s.length(); i++) {

            String odd = expandAroundCentre(s, i, i);
            String even = expandAroundCentre(s, i, i + 1);

            if(odd.length() > ans.length()) {
                ans = odd;
            }

            if(even.length() > ans.length()) {
                ans = even;
            }
        }

        return ans;
    }

    public String expandAroundCentre(String s, int left, int right){

        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }
}

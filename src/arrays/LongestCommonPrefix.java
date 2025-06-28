package arrays;

import java.util.Arrays;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append('a');
        sb.append('d');
        sb.append('i');
        sb.append('t');
        sb.append('y');
        sb.append('a');

        System.out.println(sb.toString());
        sb.delete(3, sb.length());
        System.out.println(sb.toString());
    }


    // When the array is sorted lexicographically, the strings that are most different (in terms of prefix) will move to the first and last positions
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String s1 = strs[0];
        String s2 = strs[strs.length - 1];
        int idx = 0;
        while (idx < s1.length() && idx < s2.length()){
            if(s1.charAt(idx) == s2.charAt(idx)){
                idx++;
            } else {
                break;
            }
        }

        return s1.substring(0, idx);
    }
}

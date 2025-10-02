package StackandQueques;
import java.util.ArrayList;
import java.util.List;
public class GenerateParentheses {
    public static void main(String[] args) {
        int n = 1;
        System.out.println(generateParenthesis(n));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        helper(ans, "", 0, 0, n);
        return ans;
    }

    private static void helper(List<String> ans, String s, int open, int close, int max) {

        if(s.length() == max * 2){
            ans.add(s);
            return;
        }

        if(open < max) {
            helper(ans, s + '(', open + 1, close, max);
        }

        if(close < open) {
            helper(ans, s + ')', open, close + 1, max);
        }
    }
}

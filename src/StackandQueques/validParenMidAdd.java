package StackandQueques;

import java.util.Stack;

public class validParenMidAdd {
    public static void main(String[] args) {
        String s = "(((";
        System.out.println(minAddToMakeValid(s));
    }

    public static int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()){
            if(ch == '('){
                stack.push(ch);
            } else{
                if(!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                } else{
                    stack.push(ch);
                }
            }
        }
        return stack.size();
    }
}

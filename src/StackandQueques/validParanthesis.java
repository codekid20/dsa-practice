package StackandQueques;

import java.util.Stack;

public class validParanthesis {
    public static void main(String[] args) {
//        String s = "([])";
//        System.out.println(isValid(s));

        String str = "aditya";
        for(char ch : str.toCharArray()){
            System.out.println(ch);
        }
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch: s.toCharArray()){
            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            } else {
                if(ch == ')'){
                    if(stack.isEmpty() || stack.pop() != '('){
                        return false;
                    }
                }
                if(ch == '}'){
                    if(stack.isEmpty() || stack.pop() != '{'){
                        return false;
                    }
                }
                if(ch == ']'){
                    if(stack.isEmpty() || stack.pop() != '['){
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}

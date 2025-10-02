package StackandQueques;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class EvaluateReversePolishNotation {
    public static void main(String[] args) {

    }

    public static int evalRPN(String[] tokens) {

        HashSet<String> set = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        Stack<Integer> st = new Stack<>();
        for(String str : tokens){

            if(set.contains(str)){
                int b = st.pop();
                int a = st.pop();
                int result = switch (str) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    case "/" -> a / b;
                    default -> 0;
                };

                st.push(result);
            } else {
                st.push(Integer.parseInt(str));
            }
        }

        return st.pop();
    }
}

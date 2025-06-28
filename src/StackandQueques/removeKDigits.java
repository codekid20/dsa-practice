package StackandQueques;

import java.util.Stack;

public class removeKDigits {
    public static void main(String[] args) {
        String num = "10";
        int k = 1;
        System.out.println(removeKdigits(num, k));
    }

    public static String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < num.length(); i++){
            while (!st.isEmpty() && k > 0 && (st.peek() - '0') > (num.charAt(i) - '0')){
                st.pop();
                k--;
            }

            st.push(num.charAt(i));
        }

        while (k > 0){
            st.pop();
            k--;
        }

        if(st.isEmpty()){
            return "0";
        }

        StringBuilder res = new StringBuilder();

        while(!st.isEmpty()){
            res.append(st.pop());
        }

        while(res.length() != 0 && res.substring(res.length() - 1).equals("0")){
            res.deleteCharAt(res.length() - 1);
        }

        res.reverse();

        if(res.isEmpty()){
            return "0";
        }

        return res.toString();
    }
}

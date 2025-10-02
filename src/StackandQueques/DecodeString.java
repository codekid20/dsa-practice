package StackandQueques;

import java.util.Stack;

public class DecodeString {
    public static void main(String[] args) {
        String s = "2[abc]3[cd]ef";
        System.out.println(decodeString(s));
    }

    public static String decodeString(String s) {

        String ans = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> ansStack = new Stack<>();

        int idx = 0;

        while (idx < s.length()) {

            if(Character.isDigit(s.charAt(idx))){
                int count = 0;

                while(Character.isDigit(s.charAt(idx))){

                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }

                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                ansStack.push(ans);
                ans = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(ansStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(ans);
                }

                ans = temp.toString();
                idx++;
            } else {
                ans += s.charAt(idx++);
            }
        }

        return ans;
    }
}

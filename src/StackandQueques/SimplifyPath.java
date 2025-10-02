package StackandQueques;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayDeque;
public class SimplifyPath {
    public static void main(String[] args) {
        String path = "/home/";
        String[] Char = path.split("/");
        System.out.println(Arrays.toString(Char));
        System.out.println(simplifyPath(path));
    }
    public static String simplifyPath(String path) {

        Deque<String> dq = new LinkedList<>();
        String[] directories = path.split("/");
        for (String dir : directories){
            if(dir.equals(".") || dir.isEmpty()) continue;
            else if (dir.equals("..")) {
                if (!dq.isEmpty()){
                    dq.removeLast();
                }
            } else {
                dq.addLast(dir);
            }
        }

        return "/" + String.join("/", dq);
    }

    public static String simplifyPath2(String path) {
        Deque<String> stack = new ArrayDeque<>();  // efficient for push/pop at end
        String[] parts = path.split("/");

        for (String dir : parts) {
            if (dir.isEmpty() || dir.equals(".")) {
                continue; // skip current dir or empty
            } else if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast(); // pop the last directory
                }
            } else {
                stack.addLast(dir); // add directory to the end
            }
        }

        return "/" + String.join("/", stack);
    }
}

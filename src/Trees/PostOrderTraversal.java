package Trees;

import java.util.*;

public class PostOrderTraversal {
    public static void main(String[] args) {

    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        ans.addAll(postorderTraversal(root.left));
        ans.addAll(postorderTraversal(root.right));
        ans.add(root.val);

        return ans;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        if (root == null){
            return postorder;
        }

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();

        st1.push(root);

        while (!st1.isEmpty()){
            root = st1.pop();
            st2.add(root);

            if(root.left != null){
                st1.push(root.left);
            }

            if (root.right != null){
                st1.push(root.right);
            }
        }

        while (!st2.isEmpty()){
            postorder.add(st2.pop().val);
        }
        return postorder;
    }

    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        if (root == null){
            return postorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode temp = stack.peek().right;
                if (temp == null){
                    temp = stack.pop();
                    postorder.add(temp.val);
                    while (!stack.isEmpty() && temp == stack.peek().right){
                        temp = stack.pop();
                        postorder.add(temp.val);
                    }
                } else {
                    curr = temp;
                }
            }
        }

        return postorder;
    }
}

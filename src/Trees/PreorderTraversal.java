package Trees;

import java.util.*;

public class PreorderTraversal {
    public static void main(String[] args) {

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        ans.add(root.val);
        ans.addAll(preorderTraversal(root.left));
        ans.addAll(preorderTraversal(root.right));

        return ans;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        if(root == null){
            return preorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            preorder.add(node.val);

            if (node.right != null){
                stack.push(node.right);
            }

            if(node.left != null){
                stack.push(node.left);
            }
        }
        return preorder;
    }
}

package Trees;

import com.sun.source.tree.Tree;

import java.util.*;

public class PreorderTraversal {
    public static void main(String[] args) {

    }

//    Root → Left → Right
//    Time: O(n)
//    Space: O(h) where h = height
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

//    Time: O(n)
//    Space: O(h)
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

    public List<Integer> preorderTraversal3(TreeNode root){

        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        if(root == null) return ans;
        TreeNode node = root;

        while (node != null || !st.isEmpty()){

            while (node != null){
                ans.add(node.val);
                st.push(node);
                node = node.left;
            }


            node = st.pop();
            node = node.right;
        }

        return ans;
    }
}

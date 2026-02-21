package Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {
    public static void main(String[] args) {

    }

//    Left → Root → Right
//    For a Binary Search Tree, in-order traversal gives you nodes in sorted order (ascending)

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (true){
            if(node != null){
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty()){
                    break;
                }

                node = stack.pop();
                inorder.add(node.val);
                node = node.right;
            }
        }

        return inorder;
    }

//    Time: O(n)
//    Space: O(h) where h = height (call stack)
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorder(root, ans);
        return ans;
    }

    public void inorder(TreeNode node, List<Integer> ans){

        if(node == null) return;
        inorder(node.left, ans);
        ans.add(node.val);
        inorder(node.right, ans);
    }


    public List<Integer> inorderTraversal2(TreeNode root){

        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode node = root;
        while(node != null || !st.isEmpty()){

            while(node != null){
                st.add(node);
                node = node.left;
            }

            node = st.pop();
            ans.add(node.val);
            node = node.right;
        }

        return ans;
    }
}

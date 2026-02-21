package Trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class invertABT {
    public static void main(String[] args) {

    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    // BFS
    public TreeNode invertTree1(TreeNode root){

        Queue<TreeNode> q = new LinkedList<>();

        if(root == null) return null;

        q.offer(root);

        while(!q.isEmpty()) {
            TreeNode node = q.poll();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if(node.left != null) q.offer(node.left);
            if(node.right != null) q.offer(node.right);
        }

        return root;
    }

    // DFS

    public TreeNode invertTree2(TreeNode root){

        Stack<TreeNode> st = new Stack<>();

        if(root == null) return null;

        st.push(root);

        while(!st.isEmpty()) {
            TreeNode node = st.pop();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if(node.left != null) st.push(node.left);
            if(node.right != null) st.push(node.right);
        }

        return root;
    }
}

package Trees;

public class UnivaluedBinaryTree {
    public static void main(String[] args) {

    }

    boolean unival = true;
    public boolean isUnivalTree(TreeNode root) {
        if(root == null){
            return unival;
        }

        helper(root, root.val);
        return unival;
    }

    public void helper(TreeNode node, int val){
        if(node == null){
            return;
        }

        if(node.val != val){
            unival = false;
        }

        helper(node.left, val);
        helper(node.right, val);
    }
}

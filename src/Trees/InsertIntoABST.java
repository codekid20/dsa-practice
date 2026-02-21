package Trees;

public class InsertIntoABST {
    public static void main(String[] args) {

    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        helper(root, val);
        return root;
    }

    // Note:
    // 1. A new node will be added either to the left of any node or to the right of it.
    // 2. If val is bigger than node val, than new node will be inserted in this right tree, If it is smaller than it will be inserted in the left tree.

    public void helper(TreeNode node, int val){

        if(node == null){
            return;
        }

        if(node.right == null && node.val < val){
            node.right = new TreeNode(val);
        }

        if(node.left == null && node.val > val){
            node.left = new TreeNode(val);
        }

        if(node.val < val){
            helper(node.right, val);
        }

        if(node.val > val){
            helper(node.left, val);
        }
    }
}

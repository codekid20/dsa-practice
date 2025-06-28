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

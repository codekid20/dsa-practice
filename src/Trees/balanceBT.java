package Trees;

public class balanceBT {
    public static void main(String[] args) {

    }

    boolean balanced = true;
    public boolean isBalanced(TreeNode root) {
        height(root);

        return balanced;
    }

    public int height(TreeNode node){
        if(node == null){
            return 0;
        }

        int left = height(node.left);
        int right = height(node.right);
        if(Math.abs(left - right) > 1){
            balanced = false;
        }

        return Math.max(left, right) + 1;
    }
}

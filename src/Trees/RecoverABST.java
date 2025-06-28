package Trees;

public class RecoverABST {
    public static void main(String[] args) {

    }

    // To avoid null pointer exception when comparing first time
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    TreeNode first = null;

    TreeNode second = null;

    public void recoverTree(TreeNode root) {

        inorder(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;

    }

    private void inorder(TreeNode root) {
        if(root == null){
            return;
        }

        inorder(root.left);

        // do some business
        if(first == null && prev.val > root.val){
            first = prev;
        }

        if (first != null && prev.val > root.val){
            second = root;
        }

        prev = root;

        inorder(root.right);
    }
}

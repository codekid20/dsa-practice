package Trees;

public class ConstructBSTFromPreorder {
    public static void main(String[] args) {

    }

    int i = 0;
    public TreeNode bstFromPreorder(int[] preorder) {

        TreeNode root = buildTree(preorder, Integer.MAX_VALUE);

        return root;
    }

    private TreeNode buildTree(int[] preorder, int maxValue) {
        if(preorder.length == i || preorder[i] > maxValue){
            return null;
        }

        TreeNode root = new TreeNode(preorder[i++]);
        root.left = buildTree(preorder, root.val);
        root.right = buildTree(preorder, maxValue);

        return root;
    }
}

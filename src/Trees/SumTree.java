package Trees;

public class SumTree {
    public static void main(String[] args) {

    }

    static boolean SumTree;
    public static boolean isSumTree(TreeNode root){
        if(root == null){
            return SumTree;
        }
        SumTree = true;
        sumTreeHelper(root);
        return SumTree;
    }

    private static int sumTreeHelper(TreeNode root) {
        if(root == null){
            return 0;
        }

        // leaf node, return its value directly
        if(root.left == null && root.right == null){
            return root.val;
        }

        int left = sumTreeHelper(root.left);
        int right = sumTreeHelper(root.right);

        if(left + right != root.val){
            SumTree = false;
        }

        return left + right + root.val;
    }
}

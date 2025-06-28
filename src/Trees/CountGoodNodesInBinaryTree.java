package Trees;

public class CountGoodNodesInBinaryTree {
    public static void main(String[] args) {

    }

    public int goodNodes(TreeNode root) {

        return good(root, root.val);
    }

    private int good(TreeNode root, int maxVal) {
        if(root == null){
            return 0;
        }
        maxVal = Math.max(maxVal, root.val);
        int ans = (root.val >= maxVal) ? 1 : 0;
        ans += good(root.left, maxVal);
        ans += good(root.right, maxVal);
        return ans;
    }
}

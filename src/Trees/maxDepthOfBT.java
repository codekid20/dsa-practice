package Trees;

public class maxDepthOfBT {
    public static void main(String[] args) {

    }

//    Return the maximum depth (height) of a binary tree.
//    Depth = number of nodes along the longest path from root to leaf.
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }
}

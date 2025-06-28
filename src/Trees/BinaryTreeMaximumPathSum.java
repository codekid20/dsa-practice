package Trees;

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {

    }

    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxSum;
    }

    public int helper(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = helper(root.left);
        int right = helper(root.right);

        left = Math.max(0, left);
        right = Math.max(0, right);

        int sum = left + right + root.val;
        maxSum = Math.max(maxSum,sum);

        return Math.max(left,right) + root.val;
    }
}

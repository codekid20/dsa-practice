package Trees;

public class pathSum {
    public static void main(String[] args) {

    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }

        if(root.left == null && root.right == null){ // important!!! return only when you have reached lead node
            return root.val == targetSum;
        }

        return (hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val));
    }

    public boolean helper(TreeNode node, int target){
        if(node == null){
            return false;
        }

        if(target == 0){
            return true;
        }

        return (helper(node.left, target - node.val) || helper(node.right, target - node.val));
    }
}

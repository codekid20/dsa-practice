package Trees;

public class RangeSumOfBST {
    public static void main(String[] args) {

    }

    int sum = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        helper(root, low, high);
        return sum;
    }

    public void helper(TreeNode node , int low, int high){
        if(node == null){
            return;
        }

        if(node.val > low){
            helper(node.left, low, high);
        }

        if(node.val >= low && node.val <= high){
            sum += node.val;
        }

        if(node.val < high){
            helper(node.right, low, high);
        }
    }
}

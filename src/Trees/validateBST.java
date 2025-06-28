package Trees;

public class validateBST {
    public static void main(String[] args) {

    }

    public boolean isValidBST(TreeNode root) {

        return validBST(root, null, null);
    }

    private boolean validBST(TreeNode root, Integer low, Integer high) {
        if(root == null){
            return true;
        }

        if(low != null && root.val <= low){
            return false;
        }

        if(high!= null && root.val >= high){
            return false;
        }

        return validBST(root.left, low, root.val) && validBST(root.right, root.val, high);
    }
}

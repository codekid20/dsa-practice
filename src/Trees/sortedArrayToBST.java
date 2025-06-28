package Trees;

public class sortedArrayToBST {
    public static void main(String[] args) {

    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0){
            return null;
        }

        return BST(nums, 0, nums.length - 1);
    }

    private TreeNode BST(int[] nums, int left, int right) {
        if(left > right){
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = BST(nums, left, mid - 1);
//        root.right = BST(nums, mid, right); // wrong because of left == mid == right // cause stack overflow
        root.right = BST(nums, mid + 1, right);

        return root;
    }
}

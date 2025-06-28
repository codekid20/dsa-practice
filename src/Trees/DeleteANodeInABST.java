package Trees;

public class DeleteANodeInABST {
    public static void main(String[] args) {

    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }

        if(root.val == key){
            return deletehelper(root);
        }

        TreeNode dummy = root;

        while(root != null){
            if(root.val > key){
                if(root.left != null && root.left.val == key){
                    root.left = deletehelper(root.left);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                if(root.right != null && root.right.val == key){
                    root.right = deletehelper(root.right);
                    break;
                } else {
                    root = root.right;
                }
            }
        }

        return dummy;
    }

    private TreeNode deletehelper(TreeNode root) {
        if(root.left == null){
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else {
            TreeNode rightChild = root.right;
            TreeNode lastRight = findLastRight(root.left);
            lastRight.right = rightChild;
            return root.left;
        }
    }

    private TreeNode findLastRight(TreeNode root) {
        if(root.right == null){
            return root;
        }
        return findLastRight(root.right);
    }
}

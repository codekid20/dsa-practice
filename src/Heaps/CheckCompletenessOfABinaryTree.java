package Heaps;

public class CheckCompletenessOfABinaryTree {
    public static void main(String[] args) {

    }public boolean isCompleteTree(TreeNode root) {
        int nodes = countNodes(root);
        return isComplete(root,0,nodes);
    }

    private boolean isComplete(TreeNode root, int index, int nodes) {
        if(root == null){
            return true;
        }

        if(index >= nodes){
            return false;
        }

        return isComplete(root.left, 2 * index + 1, nodes) && isComplete(root.right, 2 * index + 2, nodes);
    }

    private int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }


}

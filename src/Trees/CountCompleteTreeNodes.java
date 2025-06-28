package Trees;

public class CountCompleteTreeNodes {
    public static void main(String[] args) {

    }

    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }

        int left = countNodes(root.left);
        int right = countNodes(root.right);

        return left + right + 1;
    }
}

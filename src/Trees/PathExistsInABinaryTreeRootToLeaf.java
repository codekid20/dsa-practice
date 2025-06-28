package Trees;

public class PathExistsInABinaryTreeRootToLeaf {
    public static void main(String[] args) {

    }

    public boolean findPath(TreeNode root, int[] arr){
        if(root == null){
            return arr.length == 0;
        }

        return helper(root, arr, 0);
    }

    public boolean helper(TreeNode node, int[] arr, int index){
        if(node == null){
            return false;
        }

        if(index >= arr.length || node.val != arr[index]){
            return false;
        }

        if(node.left == null && node.right == null && index == arr.length - 1){
            return true;
        }

        return helper(node.left, arr, index + 1) || helper(node.right, arr, index + 1);
    }

}

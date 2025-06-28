package Trees;

public class CountSingleValuedSubtrees {
    public static void main(String[] args) {

    }

    int count = 0;
    public int singlevalued(Node root) {
        helper(root);
        return count;
    }

    public boolean helper(Node node){
        if(node == null){
            return true;
        }

        boolean left = helper(node.left);
        boolean right = helper(node.right);

        if(!left || !right){
            return false;
        }

        if(node.left != null && node.val != node.left.val){
            return false;
        }

        if(node.right != null && node.val != node.right.val){
            return false;
        }

        count++;
        return true;
    }
}

package Trees;

class NodeValue{
    public int maxNode, minNode, maxSize;

    NodeValue(int minNode, int maxNode, int maxSize){
        this.minNode = minNode;
        this.maxNode = maxNode;
        this.maxSize = maxSize;
    }
}
public class LargestBSTInABinaryTree {
    public static void main(String[] args) {

    }
    public int largestBst(Node root){
        return helper(root).maxSize;
    }

    private NodeValue helper(Node root){
        // An empty tree is a BST of size 0
        if(root == null){
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        NodeValue left = helper(root.left);
        NodeValue right = helper(root.right);

        if(left.maxNode < root.val && root.val < right.minNode){
            // IT IS A BST
            return new NodeValue(Math.min(root.val, left.minNode), Math.max(root.val, right.maxNode), left.maxSize + right.maxSize + 1);
        }

        // otherwise return [-inf, inf] so that parent cant be valid BST
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
    }

}

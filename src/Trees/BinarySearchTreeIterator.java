package Trees;

import java.util.Stack;

public class BinarySearchTreeIterator {
    public static void main(String[] args) {

    }

    private Stack<TreeNode> stack = new Stack<>();
    public BinarySearchTreeIterator(TreeNode root) {
        pushLeft(root);
    }

    private void pushLeft(TreeNode root) {
        while (root != null){
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        pushLeft(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

package Heaps;

public class CheckIfBinaryTreeIsHeap {
    public static void main(String[] args) {

    }

    boolean isHeap(Node tree) {
        int nodes = countNodes(tree);
        return isComplete(tree, 0, nodes) && isMaxHeap(tree);
    }

    private boolean isMaxHeap(Node tree) {
        if(tree.left == null && tree.right == null){
            return true;
        }

        if(tree.right == null){
            return tree.data >= tree.left.data;
        }

        return tree.data >= tree.left.data && tree.data >= tree.right.data && isMaxHeap(tree.left) && isMaxHeap(tree.right);
    }

    private boolean isComplete(Node tree, int index, int nodes) {
        if(tree == null){
            return true;
        }

        if(index >= nodes){
            return false;
        }

        return isComplete(tree.left, 2 * index + 1, nodes) && isComplete(tree.right, 2 * index + 2, nodes);
    }

    private int countNodes(Node tree) {
        if(tree == null){
            return 0;
        }

        return 1 + countNodes(tree.left) + countNodes(tree.right);
    }
}

package Trees;

public class cousinsInBinaryTree {
    public static void main(String[] args) {

    }

    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode xx = findNode(root, x);
        TreeNode yy = findNode(root,y);

        return ((level(root,xx,0) == level(root,yy,0)) && (!isSibling(root, xx, yy)));
    }

    private boolean isSibling(TreeNode node, TreeNode xx, TreeNode yy) {
        if(node == null){
            return false;
        }

        return ((node.left == xx && node.right == yy) ||
                (node.left == yy && node.right == xx) ||
                isSibling(node.left, xx, yy) ||
                isSibling(node.right,xx,yy));
    }

    private int level(TreeNode node, TreeNode xx, int level) {
        if(node == null){
            return 0;
        }
        if(node == xx){
            return level;
        }
        int l = level(node.left,xx,level + 1);
        if(l != 0) {
            return l;
        }
        return level(node.right, xx, level + 1);
    }

    private TreeNode findNode(TreeNode node, int x) {
        if(node == null){
            return null;
        }

        if(node.val == x){
            return node;
        }

        TreeNode n = findNode(node.left, x); // check left side of tree using recursion
        if(n != null){ // i have found node in left side of recursion
            return n;
        }
        return findNode(node.right, x);
    }
}

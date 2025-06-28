package Trees;

public class sameTree {
    public static void main(String[] args) {

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null){
            return p == q;
        }

        if(p.val != q.val){ // check all nodes children
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

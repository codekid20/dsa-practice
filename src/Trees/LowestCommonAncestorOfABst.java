package Trees;

public class LowestCommonAncestorOfABst {
    public static void main(String[] args) {

    }

//    Time: O(h) where h = height (O(log n) balanced, O(n) skewed)
//    Space: O(h) - recursion stack

//    Cases:
//    1. Both nodes p and q are in left subtree.
//    2. Both nodes p and q arer in right subtree.
//    3. Split point one is on left another on right OR one is root.

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }

        // Both nodes are on right side
        if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }

        // Both nodes are on left
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }

//        Split point (One left One right) OR one is root
        return root;

    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q){

        TreeNode curr = root;

        while(curr != null){
            if(curr.val > p.val && curr.val > q.val){
                curr = curr.left;
            } else if (curr.val < p.val && curr.val < q.val) {
                curr = curr.right;
            } else {
                return curr;
            }
        }

        return null;
    }
}

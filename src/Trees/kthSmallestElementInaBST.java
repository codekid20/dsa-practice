package Trees;

import java.util.ArrayList;

public class kthSmallestElementInaBST {
    public static void main(String[] args) {

    }

    ArrayList<Integer> ans = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        helper(root);
        return ans.get(k=1);
    }

    public void helper(TreeNode node){
        if(node == null){
            return;
        }

        helper(node.left);
        ans.add(node.val);
        helper(node.right);
    }

    int count = 0;
    public int kthSmallest2(TreeNode root, int k) {

        return helper2(root, k).val;
    }
     public TreeNode helper2(TreeNode node, int k){
        if(node == null){
            return null;
        }

        TreeNode left = helper2(node.left,k);
         if (left != null) {
             return left;
         }

         count++;

         if(count == k){
             return node;
         }

         return helper2(node.right, k);
     }
}

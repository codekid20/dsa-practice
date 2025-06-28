package Trees;

import java.util.ArrayList;
import java.util.List;

public class BoundaryTraversalOfBT {
    public static void main(String[] args) {

    }

    public static ArrayList<Integer> traverseBoundary(TreeNode root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(!isLeaf(root)){
            ans.add(root.val);
        }
        addleftBoundary(root,ans);
        addLeaves(root,ans);
        addrightBoundary(root,ans);
        return ans;
    }

    private static void addLeaves(TreeNode root, ArrayList<Integer> ans) {
        if(isLeaf(root)){
            ans.add(root.val);
            return;
        }

        if(root.left != null){
            addLeaves(root.left,ans);
        }

        if(root.right != null){
            addLeaves(root.right,ans);
        }
    }

    private static void addrightBoundary(TreeNode root, ArrayList<Integer> ans) {
        TreeNode current = root.right;
        ArrayList<Integer> temp = new ArrayList<>();
        while (current != null){
            if(!isLeaf(current)){
                temp.add(current.val);
            }

            if(current.right != null){
                current = current.right;
            } else {
                current = current.left;
            }
        }

        for (int i = temp.size() - 1; i >= 0; i--) {
            ans.add(temp.get(i));
        }
    }

    private static void addleftBoundary(TreeNode root, ArrayList<Integer> ans) {
        TreeNode current = root.left;
        while (current != null){
            if(!isLeaf(current)){
                ans.add(current.val);
            }
            if(current.left != null){
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }

    private static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}

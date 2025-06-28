package Trees;

import java.util.HashMap;

public class ConstructBTFromPostorderAndInorder {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        TreeNode root = treeBuild(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);

        return root;
    }

    private TreeNode treeBuild(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, HashMap<Integer, Integer> map) {
        if(inStart > inEnd || postStart > postEnd){
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);

        int inorderIndex = map.get(postorder[postEnd]);
        int leftSize = inorderIndex - inStart;

        root.left = treeBuild(inorder, inStart, inorderIndex - 1, postorder, postStart, postStart + leftSize - 1, map);
        root.right = treeBuild(inorder, inorderIndex + 1, inEnd, postorder, postStart + leftSize, postEnd - 1, map);

        return root;
    }
}

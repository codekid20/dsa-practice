package Trees;

import java.util.*;

public class BTFromPreorderAndPostorder {
    public static void main(String[] args) {

    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }

        TreeNode root = buildTree(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1, map);

        return root;
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd, Map<Integer, Integer> map) {
        if(preStart > preEnd || postStart > postEnd){
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);

        if(preStart == preEnd){
            return root;
        }

        int leftRoot = preorder[preStart + 1];
        int leftIndexInPost = map.get(leftRoot);
        int leftSize = leftIndexInPost - postStart + 1;

        root.left = buildTree(preorder, preStart + 1, preStart + leftSize, postorder, postStart, leftIndexInPost,map);
        root.right = buildTree(preorder, preStart + 1 + leftSize, preEnd, postorder, leftIndexInPost + 1, postEnd - 1, map);

        return root;
    }
}

package Trees;

import java.util.HashMap;
import java.util.Map;

public class BTFromPreorderInorder {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);

        return root;

    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int InStart, int InEnd, Map<Integer, Integer> map){
        if(preStart > preEnd || InStart > InEnd){
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);

        int inInorder = map.get(root.val); // position of root in inorder
        int numsLeft = inInorder - InStart; // number of node left of root

        // for left side, nodes will start from prestart + 1, and will go till number of left nodes which we found above, and inorder
        // will go from 1st node to inorder node position - 1
        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, InStart,inInorder - 1, map);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inInorder + 1, InEnd, map);

        return root;
    }
}

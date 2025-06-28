package Trees;

import com.sun.source.tree.Tree;

import java.util.*;

public class ReverseOddLevelsOfBinaryTree {
    public static void main(String[] args) {

    }

    public TreeNode reverseOddLevels(TreeNode root) {
        helper(root.left,root.right,0);
        return root;
    }

    public void helper(TreeNode leftChild, TreeNode rightChild, int level){
        if(leftChild == null || rightChild == null){
            return;
        }

        if(level % 2 == 0){
            int temp = rightChild.val;
            rightChild.val = leftChild.val;
            leftChild.val = temp;
        }

        helper(leftChild.left, rightChild.right, level+1);
        helper(leftChild.right, rightChild.left, level+1);
    }


    // USING BFS
    public TreeNode reverseOddLevels2(TreeNode root) {
        if(root == null){
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<TreeNode> currentNodes = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentNodes.add(currentNode);

                if(currentNode.left != null){
                    queue.offer(currentNode.left);
                }

                if(currentNode.right != null){
                    queue.offer(currentNode.right);
                }
            }

            if(level % 2 == 1){
                int left = 0;
                int right = currentNodes.size() - 1;

                while(left < right) {
                    int temp = currentNodes.get(left).val;
                    currentNodes.get(left).val = currentNodes.get(right).val;
                    currentNodes.get(right).val = temp;

                    left++;
                    right--;
                }
            }

            level++;
        }

        return root;
    }
}

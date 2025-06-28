package Trees;

import java.util.LinkedList;
import java.util.Queue;

public class evenOddTree {
    public static void main(String[] args) {

    }

    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean even = true;

        while (!queue.isEmpty()){
            int levelSize = queue.size();

            int prev = Integer.MAX_VALUE;
            if(even){
                prev = Integer.MIN_VALUE;
            }

            while (levelSize > 0){
                TreeNode current = queue.poll();
                if((even && (current.val % 2 == 0 || current.val <= prev)) || (!even && (current.val % 2 == 1 || current.val >= prev))){
                    return false;
                }

                prev = current.val;
                if (current.left != null){
                    queue.offer(current.left);
                }

                if(current.right != null){
                    queue.add(current.right);
                }

                levelSize--;
            }

            even = !even;
        }

        return true;
    }
}

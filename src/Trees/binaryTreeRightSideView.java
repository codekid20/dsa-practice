package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class binaryTreeRightSideView {
    public static void main(String[] args) {

    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if(i == levelSize - 1){
                    ans.add(currentNode.val);
                }

                if(currentNode.left != null){
                    queue.offer(currentNode.left);
                }

                if(currentNode.right != null){
                    queue.offer(currentNode.right);
                }
            }
        }

        return ans;
    }
}

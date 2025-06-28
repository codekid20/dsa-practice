package Trees;

import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeftTreeValue {
    public static void main(String[] args) {

    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode current = root;
        queue.offer(current);

        while (!queue.isEmpty()){
            current = queue.poll();
            if(current.right != null){
                queue.offer(current.right);
            }

            if (current.left != null){
                queue.offer(current.left);
            }
        }
        return current.val;
    }

    int maxDepth = -1;
    int ans = 0;
    public int findBottomLeftValue2(TreeNode root) {
        dfs(root,0);
        return ans;
    }

    private void dfs(TreeNode root, int depth) {
        if(root == null){
            return;
        }

        if(depth > maxDepth){
            maxDepth = depth;
            ans = root.val;
        }

        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}

package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLargestValueInEachTreeRow {
    public static void main(String[] args) {

    }

    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int levelSize = queue.size();
            int currMax = Integer.MIN_VALUE;
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currMax = Math.max(currMax,current.val);
                if (current.left != null){
                    queue.offer(current.left);
                }

                if(current.right != null){
                    queue.offer(current.right);
                }
            }
            ans.add(currMax);
        }

        return ans;
    }

    List<Integer> ans = new ArrayList<>();
    public List<Integer> largestValues(TreeNode root) {

        dfs(root,0);
        return ans;
    }

    private void dfs(TreeNode root, int depth) {
        if(root == null){
            return;
        }

        if(ans.size() == depth){
            ans.add(root.val);
        } else {
            ans.set(depth, Math.max(ans.get(depth), root.val));
        }

        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}

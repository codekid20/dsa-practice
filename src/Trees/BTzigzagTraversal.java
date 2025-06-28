package Trees;

import java.util.*;

public class BTzigzagTraversal {
    public static void main(String[] args) {

    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        boolean reverse = false;
        while(!deque.isEmpty()){
            int levelSize = deque.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                if(!reverse){
                    TreeNode currentNode = deque.pollFirst();
                    currentLevel.add(currentNode.val);
                    if(currentNode.left != null){
                        deque.addLast(currentNode.left);
                    }
                    if(currentNode.right != null){
                        deque.addLast(currentNode.right);
                    }
                } else {
                    TreeNode currentNode = deque.pollLast();
                    currentLevel.add(currentNode.val);
                    if(currentNode.right != null){
                        deque.addFirst(currentNode.right); // this order is very important
                    }

                    if(currentNode.left != null){
                        deque.addFirst(currentNode.left);
                    }

                }
            }
            reverse = !reverse;
            ans.add(currentLevel);
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int Level = 0;
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> inner = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if(Level % 2 == 0){
                    inner.add(currentNode.val);
                } else {
                    inner.add(0,currentNode.val);
                }

                if(currentNode.left != null){
                    queue.offer(currentNode.left);
                }

                if(currentNode.right != null){
                    queue.offer(currentNode.right);
                }
            }
            ans.add(inner);
            Level++;
        }

        return ans;
    }
}

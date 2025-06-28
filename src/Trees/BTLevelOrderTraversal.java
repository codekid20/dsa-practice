    package Trees;

    import java.util.ArrayList;
    import java.util.LinkedList;
    import java.util.List;
    import java.util.Queue;
    public class BTLevelOrderTraversal {
        public static void main(String[] args) {

        }
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ans = new ArrayList<>();
            if(root == null){
                return ans;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()){
                int size = queue.size();
                List<Integer> inner = new ArrayList<>();
                for(int i = 0; i < size; i++){
                    TreeNode currentNode = queue.poll();
                    if(currentNode.left != null) {
                        queue.offer(currentNode.left);
                    }

                    if(currentNode.right != null){
                        queue.offer(currentNode.right);
                    }

                    inner.add(currentNode.val);
                }

                ans.add(inner);
            }

            return ans;
        }
    }

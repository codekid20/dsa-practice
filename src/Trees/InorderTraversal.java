package Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {
    public static void main(String[] args) {

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (true){
            if(node != null){
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty()){
                    break;
                }

                node = stack.pop();
                inorder.add(node.val);
                node = node.right;
            }
        }

        return inorder;
    }
}

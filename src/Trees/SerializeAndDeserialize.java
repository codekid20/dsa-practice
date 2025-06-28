package Trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeAndDeserialize {
    public static void main(String[] args) {
        StringBuilder res = new StringBuilder();
        res.append(1);
        res.append(" ");
        res.append(2);
        res.append(" ");
        res.append(3);
        res.append(" ");

        String s = res.toString();
        Queue<String> queue = new LinkedList<>(Arrays.asList(s.split(" ")));
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }

    public String serialize(TreeNode root) {
        if(root == null) {
            return "";
        }

        StringBuilder ans = new StringBuilder();
        buildString(root, ans);
        return ans.toString();
    }

    private void buildString(TreeNode root, StringBuilder ans) {
        if(root == null){
            ans.append("X").append(" ");
        } else {
            ans.append(root.val).append(" ");
            buildString(root.left, ans);
            buildString(root.right, ans);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == ""){
            return null;
        }

        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(" ")));

        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String s = queue.poll();
        if(s.equals("X")){
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(s));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;

    }
}

package Trees;

public class LinkedListInABinaryTree {
    public static void main(String[] args) {

    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root == null){
            return false;
        }

        return path(root, head);
    }

    private boolean path(TreeNode root, ListNode head) {
        if(root == null){
            return false;
        }

        if(dfs(root,head)){
            return true;
        }

        return path(root.left, head) || path(root.right, head);
    }

    private boolean dfs(TreeNode root, ListNode head) {
        if(head == null){
            return true;
        }

        if(root == null){
            return false;
        }

        if(root.val != head.val){
            return false;
        }


        return dfs(root.left, head.next) || dfs(root.right, head.next);
    }
}

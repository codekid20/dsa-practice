package Trees;

public class populatingNextRightPointer {
    public static void main(String[] args) {

    }

    public Node connect(Node root) {
        Node leftMost = root;
        if(root == null){
            return null;
        }

        while (leftMost.left != null){
            Node current = leftMost;
            while (current != null){
                current.left.next = current.right;

                if(current.next != null){
                    current.right.next = current.next.left;
                }

                current = current.next;
            }

            leftMost = leftMost.left;
        }

        return root;
    }
}

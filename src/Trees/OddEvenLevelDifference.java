package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OddEvenLevelDifference {
    public static void main(String[] args) {

    }

    public int getLevelDiff(Node root) {

        if(root == null){
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int oddSum = 0;
        int evenSum = 0;
        int level = 0;
        while(!queue.isEmpty()){
            int leveSize = queue.size();
            ArrayList<Integer> inner = new ArrayList<>();
            for (int i = 0; i < leveSize; i++) {
                Node node = queue.poll();
                inner.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }
            }

            if(level % 2 == 0){
                for (Integer integer : inner) {
                    evenSum += integer;
                }
            } else {
                for (Integer integer : inner) {
                    oddSum += integer;
                }
            }

            level++;
        }

        return evenSum - oddSum;
    }

    public int getLevelDiff2(Node root){
        if(root == null){
            return 0;
        }

        return root.val - getLevelDiff(root.left) - getLevelDiff(root.right);
    }
}

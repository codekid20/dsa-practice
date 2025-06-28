package Trees;

import java.util.*;

class Pair{
    Node node;
    int pos;

    Pair(Node node, int pos){
        this.node = node;
        this.pos = pos;
    }
}
public class TopViewOfBT {
    public static void main(String[] args) {

    }

    public static ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root,0));

        while (!queue.isEmpty()){
            Pair curr = queue.poll();
            int pos = curr.pos;
            Node temp = curr.node;

            if(map.get(pos) == null){
                map.put(pos, temp.val);
            }

            if(temp.left != null){
                queue.offer(new Pair(temp.left, pos - 1));
            }

            if (temp.right != null){
                queue.offer(new Pair(temp.right, pos + 1));
            }
        }

//        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
//            ans.add(entry.getValue());
//        }
        for (Integer value : map.values()){
            ans.add(value);
        }

        return ans;
    }
}

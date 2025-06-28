package Trees;

import java.util.*;

public class VerticalOrderTraversal {
    public static void main(String[] args) {

    }

    public List<List<Integer>> verticalTraversal(TreeNode node) {
        List<List<Integer>> ans = new ArrayList<>();

        if(node == null){
            return ans;
        }

        int col = 0;
        Queue<Map.Entry<TreeNode, Integer>> queue = new LinkedList<>();
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        queue.offer(new AbstractMap.SimpleEntry<>(node, col));

        int min = 0;
        int max = 0;

        while (!queue.isEmpty()){
            Map.Entry<TreeNode, Integer> removed = queue.poll();

            node = removed.getKey();
            col = removed.getValue();

            if (node != null){
                if(!map.containsKey(col)){
                    map.put(col, new ArrayList<>());
                }

                map.get(col).add(node.val);

                min = Math.min(min, col);
                max = Math.max(max, col);

                queue.offer(new AbstractMap.SimpleEntry<>(node.left,col - 1));
                queue.offer(new AbstractMap.SimpleEntry<>(node.right, col + 1));
            }

        }

        for (int i = min; i <= max; i++) {
            List<Integer> vertical = map.get(i);
            Collections.sort(vertical);
            ans.add(vertical);
        }

        return ans;
    }
}

package Graph;
import java.util.*;
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
public class CloneGraph {

    public static void main(String[] args) {

    }

    private Map<Node, Node> visited = new HashMap<>();;
    public Node cloneGraph(Node node) {

        if(node == null) return null;

        if(visited.containsKey(node)) return visited.get(node);

        Node cloneNode = new Node();
        cloneNode.val = node.val;
        cloneNode.neighbors = new ArrayList<>();

        visited.put(node, cloneNode);

        for(Node neighbour : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbour));
        }


        return cloneNode;
    }
}

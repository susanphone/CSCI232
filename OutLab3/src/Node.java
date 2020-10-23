import java.util.Map;

public class Node {
    // give a node an id and know who its neighbors are
    private int id;
    private Map<String, Integer> neighbors;

public Node(String id, Map<String, Integer> neighbors) {
    //this.id = id;
    this.neighbors = neighbors;
}
    // get the id of the node
    public int getId() {
        return id;
    }

    // get all the neighbors of a specific node
    public Map<String, Integer> getNeighbors() {
        return neighbors;
    }
}

/*
Susan McCartney
CSCI 232 - OLab4
November 20, 2020
 */

package OLab4;

import java.util.HashMap;

public class Node {
    private final String word;
    private HashMap<Integer, Node> children;

    // Node represents each word in the tree
    // and stores its children
    public Node(String word) {
        this.word = word;
        children = new HashMap<>();
    }

    // Gets word
    public String getWord() {
        return word;
    }
    // Returns child with specific weight
    public Node getChild (int weight) {
        return children.get(weight);
    }
    public HashMap<Integer, Node> getChildren() {
        return children;
    }

    // Adds a new child to existing map
    // Throws an exception when weight already exists
    public void addChild(Node node, int weight) {
        children.put(weight, node);
    }

    // Checks to see if there is a child with a specific weight
    public boolean hasChild (int weight) {
        return children.containsKey(weight);
    }

    @Override
    public String toString() {
        return word;
    }
}

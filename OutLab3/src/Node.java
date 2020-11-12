/*
Susan McCartney
CSCI 232 - Lab 3
October 23, 2020
 */

import java.util.Map;

public class Node {
    // give a node an id and know who its neighbors are
    private int id;

    // Neighbor id to connection weight
    private Map<Integer, Integer> neighbors;

    private boolean isMarked;



    public Node(int id) {
        this.id = id;
        this.isMarked = false;
    }

    // get the id of the node
    public int getId() {
        return id;
    }

    // get all the neighbors of a specific node
    public Map<Integer, Integer> getNeighbors() {
        return neighbors;
    }

    // mark the node that has been visited
    public void setMarked(boolean marked) {
        this.isMarked = marked;
    }

    // check to see if a node has been visited
    public boolean isMarked() {
        return isMarked;
    }

    // add a connection between two nodes
    public void addNeighbor(int endNode, int weight) {
        neighbors.put(endNode, weight);
    }
}

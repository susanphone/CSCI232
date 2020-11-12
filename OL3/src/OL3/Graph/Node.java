/*
Susan McCartney
CSCI 232 - Out Lab 3
November 6, 2020
 */
package OL3.Graph;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private final int nodeId;

    private final Map<Integer, Integer> edges;
    private int distanceTo;
    private Node shortestPath;

    private boolean marked;

    public Node(int n) {
        nodeId = n;
        marked = false;
        distanceTo = Integer.MAX_VALUE;
        edges = new HashMap<>();
    }

    // getters ******
    public int getNodeId() {
        return nodeId;
    }

    public int getDistanceTo() {
        return distanceTo;
    }

    public Node getShortestPath() {
        return shortestPath;
    }

    public Map<Integer, Integer> getEdges() {
        return edges;
    }

    // Setters *****
    public void setDistanceTo(int distanceTo) {
        this.distanceTo = distanceTo;
    }

    public void setShortestPath(Node shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    // Other Methods *****

    // track if node has been visited
    public boolean isMarked() {
        return marked;
    }

    // add edge and weight to Map of edges
    public void addEdge(int endId, int weight) {
        edges.put(endId, weight);
    }

    // reset program for another user input
    public void reset() {
        marked = false;
        distanceTo = Integer.MAX_VALUE;
        shortestPath = null;
    }
}


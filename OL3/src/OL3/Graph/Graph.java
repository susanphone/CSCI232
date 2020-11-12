/*
Susan McCartney
CSCI 232 - Out Lab 3
November 6, 2020
 */

package OL3.Graph;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    Map<Integer, Node> nodeMap;


    public Graph() {
        this.nodeMap = new HashMap<>();
    }

    // add node connection and edge weights
    public void addNodeAndEdges(int startNodeId, int endNodeId, int weight) {
        Node startNode = (Node) nodeMap.get(startNodeId);
        Node endNode = (Node) nodeMap.get(endNodeId);

        if (startNode == null) {
            startNode = new Node(startNodeId);
            nodeMap.put(startNodeId, startNode);
        }

        if (endNode == null) {
            endNode = new Node(endNodeId);
            nodeMap.put(endNodeId, endNode);
        }

        startNode.addEdge(endNodeId, weight);
        endNode.addEdge(startNodeId, weight);
    }

    // print out shortest path recursively
    public void printPath(Node route, boolean finalNode) {
        if (route.getShortestPath() != null) {
            printPath(route.getShortestPath(), false);
        }

        System.out.print(route.getNodeId());

        if (!finalNode) {
            System.out.print(" -> ");
        } else {
            System.out.println("\n");
        }

    }

    // get node ID
    public Node getNode(int nodeId) {
        return nodeMap.get(nodeId);

    }

    // reset all the data for a new user input
    public void eraseNodes() {
        for (Node n : nodeMap.values()) {
            n.reset();
        }
    }

    // run Dijkstra's algorithm to find shortest path
    public void runDijkstra() {
        // run the algo
        Node curNode;
        do {
            curNode = getUnmarkedNode();
            algoDijkstra(curNode);
        } while (curNode != null);
    }



    //pick the node with the smallest relaxed weight
    // and pass through to Dijkstra's algorithm
    private Node getUnmarkedNode() {
        Node currentNode = null;

        for (Node nextNode : nodeMap.values()) {

            if (!nextNode.isMarked()) {
                // if current node is null then move
                // to the next node
                if (currentNode == null) {
                    currentNode = nextNode;
                }

                // or if the distance of the next node is less than
                // the distance to the current node,
                // then move to the next node
                else if (nextNode.getDistanceTo() < currentNode.getDistanceTo()) {
                    currentNode = nextNode;
                }
            }
        }
        return currentNode;
    }

    // Find to destination with the smallest weight total
    private void algoDijkstra(Node cNode) {
        // if all node have been marked, the algo is complete
        if (cNode == null) {
            return;
        }
        // otherwise, mark the current node as visited
        cNode.setMarked(true);

        // find which node connect and what edges are the shortest
        // for each node in the map
        for (Map.Entry<Integer, Integer> entry :  cNode.getEdges().entrySet()) {
            int nodeId = entry.getKey();
            Node nextNode = nodeMap.get(nodeId);
            int weight = entry.getValue();
            int relaxed = weight + cNode.getDistanceTo();

            //checks if the possible relaxed-weight is less than the current distance
            if (relaxed < nextNode.getDistanceTo()) {
                nextNode.setDistanceTo(relaxed);
                nextNode.setShortestPath(cNode);
            }
        }
    }

}

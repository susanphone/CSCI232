/*
Susan McCartney
CSCI 232 - Lab 3
October 23, 2020
 */

import java.util.*;

public class Graph {
    Map<Integer, Node> nodes;
    int V;
    int distance[];

    public Graph(Map<Integer, Node> nodes) {
        this.nodes = nodes;
        this.V = V;
        distance = new int[V];

    }


    // find the shortest path from source to destination
    public void algoDijkstra(int startNode, int endNode) {
        // make the smallest weight initially as large as possible
        PriorityQueue<Integer> shortestPath = new PriorityQueue<>();
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        shortestPath.add(startNode);
        shortestPath.add(endNode);
        for (int node = startNode; node < shortestPath.size(); node++) {
            shortestPath.add(node);
            return;
        }


        // TODO: calculate path with the smallest weight

        // if this weight is less than smallest weight, change directions
//        if (relaxedWeight < smallestWeight) {
//            relaxedWeight += smallestWeight;
//            smallestWeight = relaxedWeight;
//        }
        // return the path (SOURCE to DESTINATION)
    }


    // add edges to the nodes who have a connection with a weight
    public void addEdge(int startNodeId, int endNodeId, int weight) {
        Node startNode = nodes.get(startNodeId);
        // Check to make sure the start node was found in the map. if not, yell about it
        if (startNode == null) {
            System.out.println("This node does not exist!");
        }
        startNode.addNeighbor(endNodeId, weight);
    }
    
}
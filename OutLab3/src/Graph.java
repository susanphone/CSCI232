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
        // create a PQ called shortest path
        PriorityQueue<Integer> shortestPath = new PriorityQueue<>();
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        // turn start and end into nodes
        Node start= new Node(startNode);
        Node end = new Node(endNode);

        // add start node to shortest path and marking as true
        shortestPath.add(startNode);
        start.setMarked(true);

        // Create a map called neighbors
        Map<Integer, Integer> neighbors = start.getNeighbors();

        // Initialize weights
        int weight = 0;
        int smallestWeight = Integer.MAX_VALUE;


        // look at all the neighbors and test relaxed weight vs smallest weight
        do{
            // set keys
            // Grab the set of keys from neighbors
            if(neighbors.isEmpty()) {
                return;
            }
            Set<Integer> keys = neighbors.keySet();

            // Go through the keys with iterator
            Iterator<Integer> it = keys.iterator();

            // loop through neighbors
        for (int i = 0; i < neighbors.size(); i++) {
            int a = it.next();

            // set relaxed weight
            int relaxedWeight = weight + neighbors.get(a);
            if (relaxedWeight < distance[i]) {
            distance[i] = relaxedWeight;
            shortestPath.add(a);
            Node is = new Node(a);
            is.setMarked(true);
            weight += neighbors.get(a);

        }
        }} while (!end.isMarked());
        return;

        // if this weight is less than smallest weight, change directions
//
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
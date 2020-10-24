/*
Susan McCartney
CSCI 232 - Lab 3
October 23, 2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class GPSSystem {

    public static void main(String[] args) {

        // Create a new map for the nodes
        Map<Integer, Node> nodeMap = new HashMap<>();
        //TODO: Spencer needs to explain this when he is off work
        Graph myGraph = new Graph(nodeMap);


        // Scan content of file from command line argument
        Scanner input;
        try {
            input = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!");
            return;
        }

        // Process list of nodes and determine total number of number and edges and their weights.
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] nodeList = line.split(" ");
            System.out.println(nodeList[0]);


            // Find the total number of nodes and edges
            if (nodeList[0].startsWith("p")) {
                int vertexCount = Integer.parseInt(nodeList[2]);

                for (int nodeID = 1; nodeID < vertexCount + 1; nodeID++) {
                    nodeMap.put(nodeID, new Node(nodeID));
                }
                myGraph = new Graph(nodeMap);
                continue;
            }

            // Find all the different nodes and add the weights to the edges for the neighboring nodes.
            if (nodeList[0].startsWith("a")) {
                int startNode = Integer.parseInt(nodeList[1]);
                int endNode = Integer.parseInt(nodeList[2]);
                int weight = Integer.parseInt(nodeList[3]);
                myGraph.addEdge(startNode, endNode, weight);
            }

            // get user input for source
            Scanner inputSource = new Scanner(System.in);
            String source;
            System.out.println("Please enter source: ");
            source = inputSource.next();

            // scan user input for destination
            Scanner inputDestination = new Scanner(System.in);
            String destination;
            System.out.println("Please enter your destination: ");
            destination = inputDestination.next();

            System.out.println("Shortest path from " + source + " to " + destination + ":\n");

            // TODO: initiate Dijkstra's algorithm to find the shortest path
            myGraph.algoDijkstra(Integer.parseInt(source), Integer.parseInt(destination));


            // TODO: print out each node ID that was used to create the path
            for (int i = Integer.parseInt(source); i < nodeList.length ; i++) {
                System.out.print( i + " -> " );
             }
            System.out.print(destination);
            System.out.println("\n");
            // TODO: print the total distance and time to find the path in sec
            System.out.println("total distance: " + "weight");
            long time = 0;
            time = System.currentTimeMillis();
            System.out.println("time to find: " + time/1000);
            return;
        }

        // TODO: Plot the shortest path on a window.
        // NYC: Long [40.3; 41.3]
        //      Lat  [73.5; 74.5]

    }
}
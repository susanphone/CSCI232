/*
Susan McCartney
CSCI 232 - Out Lab 3
November 6, 2020
 */

package OL3;

import OL3.Graph.Graph;
import OL3.Graph.Node;
import java.io.File;
import java.io.*;
import java.util.Scanner;


public class GPSSystem {

    // read in nodes and edges
    private static int[] readFile(File file, Graph graph) throws IOException {
        int nodeNumber = 1;
        int smallest = Integer.MAX_VALUE;
        int biggest = -1;



        try {
            // read the file
            if (file.exists()) {
                FileReader reader = new FileReader(file);
                LineNumberReader lineReader = new LineNumberReader(reader);

                // get line count
                String nodeLine;
                while ((nodeLine = lineReader.readLine()) != null) {

                // get node connections and weight
                    if (nodeLine.trim().length() > 0) {
                        String[] nodeValue = nodeLine.split(" ");
                        if (nodeValue[0].equals("a")) {
                            int starting = Integer.parseInt(nodeValue[1]);
                            int ending = Integer.parseInt(nodeValue[2]);
                            int wt = Integer.parseInt(nodeValue[3]);

                            if (starting < smallest) {
                                smallest = starting;
                            }
                            if (ending < smallest) {
                                smallest = ending;
                            }
                            if (starting > biggest) {
                                biggest = starting;
                            }
                            if (ending > biggest) {
                                biggest = ending;
                            }

                            graph.addNodeAndEdges(starting, ending, wt);
                            nodeNumber++;
                        }
                    }
                }
            }

            // catch any error with input choices
        } catch (Exception except) {
            System.err.println("Error found in '" + file.getName() + "' on line " + nodeNumber);
            System.out.println(except);
            throw except;
        }
        // return the bounds of the map, smallest node and largest node
        return new int[]{smallest, biggest};
    }



    public static void main(String[] args) {
        // make a new graph that will take in info from
        // the command line
        Graph graph = new Graph();
        File file = new File(args[0]);

        // find the smallest and biggest node IDs
        int[] smallBig;
        try {
             smallBig = readFile(file, graph);
        } catch (IOException e) {
            System.err.println("Error reading file!");
            return;
        }

        int smallest = smallBig[0];
        int biggest = smallBig[1];

        System.out.printf("The current graph has vertices from %d to %d\n", smallest, biggest);

        // get user input for menu choice
        Scanner userInput = new Scanner(System.in);

        while (true) {
            System.out.print("Would you like to:\n1. Find a new route\n2. Exit\nChoice: ");
            int menuChoice = userInput.nextInt();


            // exit program
            if (menuChoice == 2) {
                return;
            }
            // user enters source and destination
            // then run them through the algo
            System.out.print("Enter Source: ");
            int source = userInput.nextInt();
            System.out.print("Enter Destination: ");
            int destination = userInput.nextInt();

            // check for valid range
            if (source < smallest || source > biggest || destination < smallest || destination > biggest) {
                System.out.println("Please enter a valid source and destination");
                continue;
            }


            Node startNode = graph.getNode(source);
            Node endNode = graph.getNode(destination);

            // start distance set to 0
            startNode.setDistanceTo(0);

            // start time, run algorithm, end time
            long startTime = System.currentTimeMillis();
            graph.runDijkstra();
            long endTime = System.currentTimeMillis();

            // print source to destination
            System.out.printf("The shortest path from %d to %d: \n", source, destination);

            // print the shortest path
            graph.printPath(endNode, true);

            // print distance of shortest path
            System.out.printf("Total Distance: %d\n", endNode.getDistanceTo());

            // calculate time in seconds
            double durationSec = (endTime - startTime) / 1000.0;

            // print out time in seconds
            System.out.printf("Total Time: %.1f sec\n\n", durationSec);

            // reset nodes for prompt from the user again
            graph.eraseNodes();
        }


    }
}

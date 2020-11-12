import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class Node {

    //field
    public int id;
    public Dictionary edge = new Hashtable();

    public int dist = Integer.MAX_VALUE; //relaxed value
    public Node shortestPath;
    public boolean isMarked = false;

    //constructor
    public Node(int n) {
        id = n;
    }

}

public class MyGPS {

    //this picks the node with the smallest relaxed value to pass through to Dijkstra's
    static Node thisNode(Dictionary nodes) {

        Node currentNode = null;

        for (Enumeration e = nodes.elements(); e.hasMoreElements();) {
            Node nextNode = (Node) e.nextElement();

            if (!nextNode.isMarked) {

                if (currentNode == null) {
                    currentNode = nextNode;
                } else if (nextNode.dist < currentNode.dist) {
                    currentNode = nextNode;
                }
            }
        }
        return currentNode;
    }

    //finds the shortest path
    static void dijkstra(Node thisNode, Dictionary totalNodes) {
        //Dijkstra's is complete if the all the nodes were visited (making it null)
        if (thisNode == null) {
            return;
        }

        //marks the candidate as visited and
        thisNode.isMarked = true;

        //loops through all the neighbors
        for (Enumeration e = thisNode.edge.keys(); e.hasMoreElements();) {
            int index = (int) e.nextElement();
            Node neighbor = (Node) totalNodes.get(index);
            int weight = (int) thisNode.edge.get(index);
            int potential_relax = weight + thisNode.dist;

            //checks if the potential relaxed is less than the current distance
            if (potential_relax < neighbor.dist) {
                neighbor.dist = potential_relax;    //puts the new shortest distance into the neighbors
                neighbor.shortestPath = thisNode;
            }
        }

    }

    //resets the algorithm
    static void reset(Dictionary allNodes) {

        //loops through all the elements
        for (Enumeration e = allNodes.elements(); e.hasMoreElements();) {
            Node x = (Node) e.nextElement();
            x.isMarked = false;                  //resets visited
            x.dist = Integer.MAX_VALUE;     //resets relaxed value
            x.shortestPath = null;                //resets path
        }

    }

    //prints the path from source to destination recursively
    static void printPath(Node dest, boolean last) {

        //checks if the path has ended
        if (dest.shortestPath != null) {
            printPath(dest.shortestPath, false);
        }

        //prints the destination
        System.out.print(dest.id);

        //prints the arrow for pretty format if not end
        if (!last) {
            System.out.print(" -> ");
        } else {
            System.out.println("\n");   //ends the print with a new line
        }

    }

    public static void main(String[] args) {

        Dictionary nodes = new Hashtable();

        //allNode hashtable
        //parameters (id, Node)
        //edges hashtable in the NODE
        //parameters (destination, weight)
        //reading the graph in
        File f = new File(args[0]); //f is the File from the command line arguments

        int linenumber = 0;
        int smallest = Integer.MAX_VALUE;
        int largest = -1;

        //tries and catches all exceptions
        try {
            //reads the file
            if (f.exists()) {
                FileReader fr = new FileReader(f);
                LineNumberReader lnr = new LineNumberReader(fr);

                //calculates number of lines in document
                String line;
                while ((line = lnr.readLine()) != null) {
                    linenumber++;


                    if (line.trim().length() > 0) {
                        String[] values = line.split(" "); //splits the line into a string array

                        //converts string into integers to be entered into the variables
                        if (values[0].equals("a")) {
                            int s = Integer.parseInt(values[1]);
                            int d = Integer.parseInt(values[2]);
                            int w = Integer.parseInt(values[3]);

                            if (s < smallest) {
                                smallest = s;
                            }
                            if (d < smallest) {
                                smallest = d;
                            }
                            if (s > largest) {
                                largest = s;
                            }
                            if (d > largest) {
                                largest = d;
                            }

                            Node source = (Node) nodes.get(s);
                            Node dest = (Node) nodes.get(d);

                            //inputs the source and destination into the allNodes Hashtable if not already there
                            if (source == null) {
                                source = new Node(s);
                                nodes.put(s, source);
                            }
                            if (dest == null) {
                                dest = new Node(d);
                                nodes.put(d, dest);
                            }

                            //connects source and destination as neighbors
                            source.edge.put(d, w);
                            dest.edge.put(s, w);
                        }
                    }
                }
            }
        } catch (Exception e) {
            //says the line and error if applicable
            System.err.println("ERROR in '" + f.getName() + "' on line " + linenumber);
            System.out.println(e);
            return;
        }

        //allows user input
        Scanner reader = new Scanner(System.in);

        System.out.print("The current graph has vertices from " + smallest + " to " + largest + ". \n");

        while (true) {

            System.out.print("Would you like to: \n1. Find a new route \n2. Exit\n> ");
            int choice = reader.nextInt();

            //if 2 exit
            if (choice == 2) {
                return;
            }

            //if 1 perform algorithm
            System.out.print("Enter Source: ");
            int source = reader.nextInt();              //user input
            System.out.print("Enter Destination: ");
            int dest = reader.nextInt();                //user input

            //checks range of value between smallest and largest
            if (source < smallest || source > largest || dest < smallest || dest > largest) {
                System.out.println("Please enter valid a source and destination: ");
                continue;
            }
            //chesks if the source and destination are the same
            if (source == dest) {
                System.out.println("The path to finding one's self complete. You are Enlightened.");
                continue;
            }

            Node s = (Node) nodes.get(source);
            Node d = (Node) nodes.get(dest);

            s.dist = 0;

            // Dijkstra's Algo
            Node n;
            long t0 = System.currentTimeMillis();   //start time
            do {
                n = thisNode(nodes);            //loop through all node finding smallest distance and not visited
                dijkstra(n, nodes);              //the algorithm will end with the graph mapped

            } while (n != null);                    //if not null perform dijkstra's
            long t1 = System.currentTimeMillis();   //end time

            //prints the answer with the path, distance, and time
            System.out.println("The shortest path from " + source + " to " + dest + ": ");
            printPath(d, true);
            System.out.println("Total Distance: " + d.dist);
            System.out.println("Total Time: " + ((double) (t1 - t0)) / 1000.0 + " sec \n");

            //resets before looping to prompt the user again
            reset(nodes);

        }
    }
}

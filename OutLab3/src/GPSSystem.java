import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GPSSystem {

    public static void main(String[] args) {
        //File map = new File("/OutLab3/src/rome99.gr");
        // File map2 = new File("/OutLab3/src/USA-road-d.NY.gr");

        //open in map and split by space

        Scanner input;
        try {
            input = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!");
            return;
        }

        List<Integer> jobList = new ArrayList<Integer>();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] lineArray = line.split("\n");
            int[] intLineArray = new int[lineArray.length];
            for (int i = 0; i < lineArray.length; i++) {
                //System.out.println(lineArray[i]);
                // new list for each line
                if (lineArray[i] == "p") {
                    System.out.println("This is a p line");
                }
                if (lineArray[i] == "a") {
                    System.out.println("This is a node set");
                }
                // return;
            }

            }

            //for (int i : itemList) {
            // String line[] = i.split(" ");
            // get total vertices and edges for graph
            //if (line[0] == "p") {
            //    int totalVertices = line[2];
            //    int totalEdges = line[3];
            //    Graph totalNodes = new Graph(totalVertices, totalEdges);
            // }
            // generate nodes from the information on each line
            //if (line[0] == "a") {
            //    Node node = new Node();
            //  }
            // }
            //}
            // get user input for source and destination
            //Scanner source = new Scanner("Please enter source:  ");
            //Scanner destination = new Scanner("Please enter your destination: ");

            // calculate the shortest path from source to destination and print the path.
            //Graph G = new Graph(int(source), int(destination));
            //System.out.println("Shortest path from " + source + " to " + destination + ":\n");
            //for (i = 0; i < path.length(); i++) {
            //    System.out.println( i + " -> ");
            // }
            // print the total distance and time to find the path
            //System.out.println("total distance: " + relaxedWeight);
            System.out.println("time to find: " + System.currentTimeMillis());
        }

    }
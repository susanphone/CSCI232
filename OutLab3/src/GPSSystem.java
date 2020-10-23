import java.util.Scanner;


public class GPSSystem {


    public static void main(String[] args) {
        File map = new File("/OutLab3/src/rome99.gr");
        File map2 = new File("/OutLab3/src/USA-road-d.NY.gr");

        //open in map and split by space
        for (int file: map) {
            String line[] = file.split(" ");
            if (map.hasNextLine()) {
                // get total vertices and edges for graph
                if (line[0] == "p") {
                    int totalVertices = line[2];
                    int totalEdges = line[3];
                    Graph totalNodes = new Graph(totalVertices, totalEdges);
                }
                // generate nodes from the information on each line
                if (line[0] == "a") {
                    Node node = new Node();
                }
            }
        }
        // get user input for source and destination
        Scanner source = new Scanner("Please enter source:  ");
        Scanner destination = new Scanner("Please enter your destination: ");

        // calculate the shortest path from source to destination and print the path.
        Graph G = new Graph(int(source), int(destination));
        System.out.println("Shortest path from " + source + " to " + destination + ":\n");
        for (i = 0; i < path.length(); i++) {
            System.out.println( i + " -> ");
        }
        // print the total distance and time to find the path
        System.out.println("total distance: " + relaxedWeight);
        System.out.println("time to find: " + System.currentTimeMillis());

    }

}

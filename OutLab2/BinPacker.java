package OutLab2.src;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

public class BinPacker {

    //java BinPacker 50 input.txt
    //should take bin<capacity> value and <filename> as command-line arguments

    //GOAL: find the fewest number of bins that hold all of the items.
    // each bin can hold no more than <capacity> values

    // if a bin exceeds capacity, print out a note saying that an item with greater weight than the bin capacity was encountered.

        private final int id;
        private final int capacityOfBin;
        private int size;
        private final List<Integer> items;

        public BinPacker(int id, int capacity) {
            this.capacityOfBin = capacity;
            this.id = id;
            this.items = new ArrayList<Integer>();
        }

        public boolean packItem(int item) {
            if (this.size + item > this.capacityOfBin) return false;

            if (this.items.get(0) > this.capacityOfBin) {
                System.out.println("An item with greater weight that the bin capacity was encountered.\n");

                this.items.add(item);
                this.size += item;

            }
            return true;
        }

        public String toString(int items, int id) {
            String binContents = "Items in bin no." + this.id + ": ";
            for(int item : this.items) {
                binContents += item + "\n";
            }
            return binContents;
        }

}

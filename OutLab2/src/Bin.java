package OutLab2.src.FinalBin;

public class Bin {

    private final int capacity;
    private final int count;
//    private final int item;

    public Bin(int binCapacity, int binCount) {
        this.capacity = binCapacity;
        this.count = binCount;
//        this.item = item;
    }

    public boolean addItem(int item) {
        if (this.count + item > this.capacity) return false;

        if (item > this.capacity) {
            System.out.println("An item with greater weight that the bin capacity was encountered.\n");
        }
        return true;
    }

    public String toString(int item) {
        String binContents = "Items in bin no." + item + ": ";
//        for(int items : this.item) {
            binContents += addItem(item) + "\n";
//        }
        return binContents;
    }

}

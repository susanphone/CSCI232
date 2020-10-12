// Susan McCartney
// CSCI 232 : OutLab2
// October 02, 2020

package OutLab2.src;

import java.util.ArrayList;
import java.util.List;

public class Bin implements Comparable<Bin> {
    private int capacity;
    private int binContents;
    private List<Integer> binnedItems;
    private int numBins;

    public Bin(int cap) {
        this.capacity = cap;
        this.binnedItems = new ArrayList<>();
        this.numBins = 1;
    }

    public int getNumBins() {
        return numBins;
    }
    public void setNumBins(int numBins) {
        this.numBins = numBins;
    }

    public int getBinContents() {
        return binContents;
    }

    // Add each item into the bin
    public boolean addItem(int item) {
        if (!canFit(item)) {
            return false;
        }
        binContents += item;
        binnedItems.add(item);
        return true;
    }
    public boolean canFit(int item) {
        return ((item + binContents) <= capacity);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i <numBins; i++) {
            str += String.format("Capacity:%d/%d\n", binContents, capacity);
        }
        return str;
    }

    @Override
    public int compareTo(Bin thatBin) {
        if (this.binContents > thatBin.binContents) {
            return 1;
        }
        if (this.binContents < thatBin.binContents) {
            return -1;
        }
        return 0;
    }
}

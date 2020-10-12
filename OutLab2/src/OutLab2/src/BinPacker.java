// Susan McCartney
// CSCI 232 : OutLab2
// October 02, 2020
package OutLab2.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BinPacker {
    private List<Integer> items;
    private int binSize;

    public BinPacker(int binSize, List<Integer> items) {
        this.items = items;
        this.binSize = binSize;
    }

    // First Fit
    // First bin that an item fits into goes into that bin
    public void firstFit() {
        List<Bin> bins = new ArrayList<>();
        int binsEstablished = 1;
        bins.add(new Bin(binSize));
        // looping over items
        for(int item : items) {
            if (tooBigForBin(item)) {
                return;
            }
            // find bin that fits item
            boolean addedItem = false;
            for(Bin bin : bins) {
                boolean fits = bin.addItem(item);
                if (fits) {
                    addedItem = true;
                    break;
                }
            }
            if (!addedItem) {
                binsEstablished++;
                Bin newBin = new Bin(binSize);
                newBin.addItem(item);
                bins.add(newBin);
            }
        }

        System.out.printf("Total bins: %d\n", binsEstablished);
        for(Bin bin: bins) {
            System.out.print(bin);
        }
    }

    // Best Fit Decreasing using Binary Search Tree
    // Find the least empty bin and put the item in the bin
    public void bestFit() {
        ArrayList<Integer> sortedItems = new ArrayList<>(items);
        Collections.sort(sortedItems, Collections.reverseOrder());


        Comparator<Bin> bestFitComp = (o1, o2) -> -o1.compareTo(o2);

        BST<Bin> binTree = new BST<>(bestFitComp);
        int binsEstablished = 1;
        binTree.insert(new Bin(binSize));

        for(int item : sortedItems) {
            if (tooBigForBin(item)) {
                return;
            }
            Bin binToUse = null;
            for(Bin bin : binTree) {
                boolean fits = bin.canFit(item);
                if (fits) {
                    binToUse = bin;
                    break;
                }
            }
            // If we find a bin that will fit the item
            if (binToUse != null) {
                // If the bin we chose has duplicates, decrease the number of dupes by one
                if (binToUse.getNumBins() > 1) {
                    binTree.delete(binToUse);
                    binToUse.setNumBins(binToUse.getNumBins()-1);
                    binTree.insert(binToUse);
                }

                // Re-use the old bin reference
                binToUse.setNumBins(1);
                binToUse.addItem(item);

                Bin duplicateBin = null;
                // Look through the bins for a duplicate of the new value
                for (Bin bin : binTree) {
                    if (bin.getBinContents() == binToUse.getBinContents()) {
                        duplicateBin = bin;
                    }
                }
            } else {
                binsEstablished++;
                Bin newBin = new Bin(binSize);
                newBin.addItem(item);
                binTree.insert(newBin);
            }
        }
        System.out.printf("Total bins: %d\n", binsEstablished);
        binTree.inOrderTraversal();
    }

    // Worst Fit Decreasing using Binary Search Tree
    // Find the least full bin and put the item in the bin
    public void worstFit() {
        Comparator<Bin> worstFitComp = (o1, o2) -> o1.compareTo(o2);

        ArrayList<Integer> sortedItems = new ArrayList<>(items);
        Collections.sort(sortedItems, Collections.reverseOrder());

        BST<Bin> binTree = new BST<>(worstFitComp);

        int binsEstablished = 1;
        binTree.insert(new Bin(binSize));

        for(int item : sortedItems) {
            if (tooBigForBin(item)) {
                return;
            }
            Bin binToUse = null;
            for(Bin bin : binTree) {
                boolean fits = bin.canFit(item);
                if (fits) {
                    binToUse = bin;
                    break;
                }
            }
            // If we find a bin that will fit the item
            if (binToUse != null) {
                // If the bin we chose has duplicates, decrease the number of dupes by one
                if (binToUse.getNumBins() > 1) {
                    binTree.delete(binToUse);
                    binToUse.setNumBins(binToUse.getNumBins()-1);
                    binTree.insert(binToUse);
                }

                // Re-use the old bin reference
                binToUse.setNumBins(1);
                binToUse.addItem(item);

                Bin duplicateBin = null;
                // Look through the bins for a duplicate of the new value
                for (Bin bin : binTree) {
                    if (bin.getBinContents() == binToUse.getBinContents()) {
                        duplicateBin = bin;
                    }
                }
            } else {
                binsEstablished++;
                Bin newBin = new Bin(binSize);
                newBin.addItem(item);
                binTree.insert(newBin);
            }
        }        System.out.printf("Total bins: %d\n", binsEstablished);
        binTree.inOrderTraversal();
    }

    private boolean tooBigForBin(int item) {
        if (item > binSize) {
            System.out.println("An item with greater weight than the bin capacity was encountered");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> itemList = new ArrayList<Integer>();
        try{
            File listFromFile = new File (args[1]);
            Scanner readFile = new Scanner(listFromFile);
            while (readFile.hasNextLine()) {
                String item = readFile.nextLine();
                itemList.add(Integer.parseInt(item));
            }
                // loop over the file and sore all the stuff
        }catch (FileNotFoundException na){
            System.out.println("File not found");
        }

        int firstArg = Integer.parseInt(args[0]);

        BinPacker binpacker = new BinPacker(firstArg, itemList);

        System.out.println("----------------- First Fit -------------------------");
        binpacker.firstFit();
        System.out.println("----------------- Best Fit -------------------------");
        binpacker.bestFit();
        System.out.println("----------------- Worst Fit -------------------------");
        binpacker.worstFit();

        }
}

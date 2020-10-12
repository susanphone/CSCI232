//package OutLab2;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class BinPacker {
//
//    //java BinPacker 50 input.txt
//    //should take bin<capacity> value and <filename> as command-line arguments
//
//    //GOAL: find the fewest number of bins that hold all of the items.
//    // each bin can hold no more than <capacity> values
//
//    // if a bin exceeds capacity, print out a note saying that an item with greater weight than the bin capacity was encountered.
//
//        private final int id;
//        private final int capacityOfBin;
//        private int size;
//        private final List<Integer> items;
//
//        public BinPacker(int id, int capacity) {
//            this.capacityOfBin = capacity;
//            this.id = id;
//            this.items = new ArrayList<Integer>();
//        }
//
//        public boolean packItem(int item) {
//            if (this.size + item > this.capacityOfBin) return false;
//
//            if (this.items.get(0) > this.capacityOfBin) {
//                System.out.println("An item with greater weight that the bin capacity was encountered.\n");
//
//                this.items.add(item);
//                this.size += item;
//
//            }
//            return true;
//        }
//
//        public String toString(int items, int id) {
//            String binContents = "Items in bin no." + this.id + ": ";
//            for(int item : this.items) {
//                binContents += item + "\n";
//            }
//            return binContents;
//        }
//
//    public static class BinFit {
//
//            private final List<BinPacker> bin;
//            private final List<Integer> items;
//            private final int capacityOfBin;
//            private int binCounter;
//
//
//            public(List<Integer> items, int capacityOfBin) {
//                this.items = items;
//                this.capacityOfBin = capacityOfBin;
//                this.bin = new ArrayList<>(this.items.size());
//                this.bin.add(new BinPacker(capacityOfBin, binCounter));
//            }
//
//            // least amount of space remaining
//            public void bestFit() {
//
//                for (Integer thisItem : items) {
//
//                    boolean isInBin = false;
//                    int thisBin = 0;
//
//                    while(!isInBin) {
//                        if (this.bin.get(thisBin).packItem(thisItem)) {
//                            isInBin = true;
//                        }
//                        else if (thisBin == this.bin.size()) {
//                            BinPacker addABin = new BinPacker(capacityOfBin, ++binCounter);
//                            addABin.packItem(thisItem);
//                            this.bin.add(addABin);
//                            isInBin = true;
//                        } else {
//                            thisBin++;              // try the next bin
//                        }
//                    }
//                }
//            }
//        // most amount of space remaining
//        public void worstFit() {
//
//            for (Integer thisItem : items) {
//
//                boolean isInBin = false;
//                int thisBin = 0;
//
//                while(!isInBin) {
//                    if (thisBin == this.bin.size()) {
//                        BinPacker addABin = new BinPacker(capacityOfBin, ++binCounter);
//                        addABin.packItem(thisItem);
//                        this.bin.add(addABin);
//                        isInBin = true;
//                    } else if (this.bin.get(thisBin).packItem(thisItem)) {
//                        isInBin = true;
//                    } else {
//                        thisBin++;              // try the next bin
//                    }
//                }
//            }
//        }
//            public void results() {
//                for (BinPacker bin : this.bin) {
//                    System.out.println(bin);
//                }
//            }
//        }
//
//    public static class Test {
//            public static void main(String[] args) {
//                List<Integer> items = Arrays.asList(5, 10, 5);
//                int capacityOfBin = 10;
//
//                BinFit algorithm = new BinFit(items, capacityOfBin);
//                System.out.println(algorithm)
//            }
//    }
//}

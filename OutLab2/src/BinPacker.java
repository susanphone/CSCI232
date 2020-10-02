package OutLab2.src.FinalBin;

import java.util.Arrays;
import java.util.List;

public class BinPacker {

    private final List<Integer> items;
    private final List<Bin> bins;


    public BinPacker(List items, List bins) {
        this.bins = bins;
        this.items = items;
    }

    public void bestFit(){
        for (int thisItem : items) {

            boolean isInBin = false;
            int thisBin = 0;

            while(!isInBin) {
                if (this.bins.get(thisBin).addItem(thisItem)) {
                    isInBin = true;
                }
                else if (thisBin == this.bins.size()) {
                    Bin newBin = new Bin();
                    newBin.addItem(thisItem);
                    this.bins.add(newBin);
                    isInBin = true;
                } else {
                    thisBin++;              // try the next bin
                }
            }
        }
    }


    public void worstFit() {
        for (int thisItem : items) {

            boolean isInBin = false;
            int thisBin = 0;

            while (!isInBin) {
                if (thisBin == this.bins.size()) {
                    Bin newBin = new Bin();
                    newBin.addItem(thisItem);
                    this.bins.add(newBin);
                    isInBin = true;
                } else if (this.bins.get(thisBin).addItem(thisItem)) {
                    isInBin = true;
                } else {
                    thisBin++;              // try the next bin
                }
            }
        }
    }

//    public void results() {
//        for (bin : this.bins) {
//            System.out.println(bins);
//        }
//    }

    public static void main(String[] args) {
        List<Integer> items = Arrays.asList(5, 10, 5);
        int binCapacity = 10;
    }
}

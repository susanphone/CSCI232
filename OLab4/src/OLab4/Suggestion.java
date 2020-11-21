/*
Susan McCartney
CSCI 232 - OLab4
November 20, 2020
 */

package OLab4;

// class that produces a word with its edit distance
public class Suggestion {
    private final String word;
    private final int distance;

    public Suggestion(String word, int distance)  {
        this.word = word;
        this.distance = distance;
    }

    public String getWord() {
        return word;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return word + "=" + distance;
    }
}

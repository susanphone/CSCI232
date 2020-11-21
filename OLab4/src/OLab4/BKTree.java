/*
Susan McCartney
CSCI 232 - OLab4
November 20, 2020
 */

package OLab4;

import java.util.*;

/*
This program uses a BK-Tree. A really neat data structure that
is used for spell checking. I built this using research I found
on the Geek for Geeks website. The BK-Tree uses Levenshtein's
Edit Distance to calculate that difference between two words.
I felt like this program ties that whole semester up into
a nice little program by using nodes and children, recursion,
and lots and lots of functions.
@link https://www.geeksforgeeks.org/bk-tree-introduction-implementation/
@author Susan McCartney
 */

public class BKTree {


    // A list of all the words in the tree, used to identify correctly
    // spelled words before searching for misspellings
    private List<String> flatWords;

    // The top level word node in the tree
    private Node rootNode;

    // The allowed range of edit distances to search for the correct word
    private int tolerance;

    // Constructs a BK-Tree, which is a metric tree which is good for
    // spell checking. Takes a tolerance, which is the allowed range of
    // edit distances to search for the correct word.
    public BKTree(int tolerance) {
        flatWords = new ArrayList<>();
        this.tolerance = tolerance;
    }

    // Adds the word recursively to the root node
    public void addWord(Node rootNode, String word) {
        // if the root is null, add a root to the tree
        if (this.rootNode == null) {
            this.rootNode = new Node(word);

            // Add the word to a flat list
            flatWords.add(word);
            return;
        }

        // Calculate the edit distance from the root to the new word
        int dist = levenshteinDist(rootNode.getWord(), word);

        // If the root has a child with the same distance, recursively
        // call addWord, with the child as the new root
        if(rootNode.hasChild(dist)) {
            addWord(rootNode.getChild(dist), word);

        } else {
            // If the root does not have a child with the same distance,
            // add it as a child of the root
            rootNode.addChild(new Node(word), dist);
            flatWords.add(word);
        }

    }

    // Returns the root node of the tree
    public Node getRootNode() {
        return rootNode;
    }

    // Returns true if the word is in the tree
    public boolean wordExists(String word) {
        return flatWords.contains(word);
    }

    // Returns a list of suggestions (words and their edit distance) for a given misspelled word
    public List<Suggestion> getSuggestedWords(Node root, String word, List<Suggestion> suggestions) {
        // If suggestions are null, make a new empty list
        if (suggestions == null) {
            suggestions = new ArrayList<>();
        }

        // Gets the distance between the root word and misspelled word
        int dist = levenshteinDist(root.getWord(), word);

        // If the distance is within the tolerance, this word is a suggestion
        if (dist <= tolerance) {
            suggestions.add(new Suggestion(root.getWord(), dist));
        }

        // Loop over the root's children within the tolerance,
        // and recursively get suggested words within the branch
        List<Node> childrenWithinTolerance = new ArrayList<>();
        for (int i = dist-tolerance; i <= dist+tolerance; i++) {
            // skip negative distances
            if (i < 0) {
                continue;
            }
            // Try to get a child at distance i
            Node child = root.getChild(i);
            if (child != null){
                getSuggestedWords(child, word, suggestions);
            }
        }

        // Sort suggestions by distance ascending
        Collections.sort(suggestions, Comparator.comparingInt(Suggestion::getDistance));

        return suggestions;
    }

//    public void printTree(Node rootNode, int weight, int depth) {
//        System.out.println(weight + ":" + rootNode);
//        depth++;
//        for (Map.Entry<Integer, Node> entry : rootNode.getChildren().entrySet()) {
//            for (int i=0; i<depth; i++) {
//                System.out.print("--");
//            }
//            printTree(entry.getValue(), entry.getKey(), depth);
//        }
//    }

    // Calculates the Levenshtein Distance between two words
    private int levenshteinDist(String first, String second) {
        // Turns the words into character arrays
        char[] firstWord = first.toCharArray();
        char[] secondWord = second.toCharArray();

        // Creates an array for the last line of the matrix
        // which is one longer than the second word
        int[] lastLine = new int[ secondWord.length + 1];

        // Precaches the first line of the matrix,
        // since it will always be the same
        for(int i = 0; i < secondWord.length + 1; i++) {
            lastLine[i] = i;
        }

        // iterate through all other rows of the matrix
        for(int i = 1; i < firstWord.length + 1; i++) {

            // Creates an array for the current line of the matrix
            // which is one longer than the second word
            int[] currentLine = new int[ secondWord.length + 1];

            // Precaches the first column of the matrix
            currentLine[0] = i;

            // Iterates through each column of the current row,
            // and calculates the edit distance between the current
            // characters.
            // All costs are 1.
            for(int j = 1; j < secondWord.length + 1; j++) {
                int ins = lastLine[j] + 1;
                int subst = currentLine[j - 1] + 1;
                int del = lastLine[j - 1];
                if (firstWord[i - 1] != secondWord[j - 1]) {
                    del += 1;
                }

                // Stores the edit distance
                currentLine[j] = Math.min( Math.min(ins, subst), del);
            }

            // Cache this line for use in calculating the next line
            lastLine = currentLine;
        }
        return lastLine[secondWord.length];
    }


}

/*
Susan McCartney
CSCI 232 - OLab4
November 20, 2020
 */

package OLab4;

import java.util.*;
import java.io.*;

public class SpellCheck {

    // Reads in a file as a list of lines
    private static List<String> readFile(String fileName) {
        long start = System.currentTimeMillis();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!");
            return new ArrayList<>();
        }
        int i = 0;
        List<String> dictWords = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            dictWords.add(line);
        }
        long end = System.currentTimeMillis();
        System.out.printf("Read in file in %dms\n",(end - start));
        return dictWords;
    }

    // Of the options, get optimal choice from user
    private static String getCorrectionFromUser(List<Suggestion> suggestions, String word) {
        Scanner scanner = new Scanner(System.in);
        String selected = null;
        while (selected == null) {

            // Choice menu
            System.out.printf("\n%s: did you mean:\n", word);
            for(int i = 0; i < 3; i++) {
                System.out.printf("%d. %s\n", i + 1, suggestions.get(i).getWord());
            }
            System.out.print("0. something else\n> ");


            // Read in the user input string
            String userInput = scanner.nextLine().trim();
            switch(userInput){
                case "1":
                    selected = suggestions.get(0).getWord();
                    break;
                case "2":
                    selected = suggestions.get(1).getWord();
                    break;
                case "3":
                    selected = suggestions.get(2).getWord();
                    break;
                case "0":
                    System.out.print ("Enter custom word: ");
                    selected = scanner.nextLine().trim();
                    break;
                default:
                    System.out.println("Not a valid input!");

            }
        }
        return selected;
    }




    public static void main(String[] args) {

        // Reads in dictionary words
        List<String> dictWords = readFile(args[0]);

        // Reads in and splits the document to spellcheck
        List<String> docLines = readFile(args[1]);

        if (dictWords.isEmpty() || docLines.isEmpty()) {
            System.out.println("Either the dictionary or the document are empty. Nothing to do!");
            return;
        }

        // To know that something is going on and to see
        // how quick this program runs, there are timers
        long start = System.currentTimeMillis();

        // Add the dictionary words to the tree
        BKTree tree = new BKTree(2);
        for (String word : dictWords) {
            tree.addWord(tree.getRootNode(), word);
        }
        long end = System.currentTimeMillis();

        // Time it takes to build tree
        System.out.printf("Built Tree in %dms\n",(end - start));

        // Changes file name to add "-checked" before .txt
        String newFileName = args[1];
        List<String> checkedName = Arrays.asList(newFileName.split(".txt"));
        if (checkedName.size() < 1) {
            newFileName += "-checked";
        } else {
            String old = checkedName.get(0);
            checkedName.set(0,  old + "-checked.txt");
            newFileName = String.join("", checkedName);
        }

        // Writes to new document that correct the old document and
        // replaces old words with correct words
        PrintWriter writer;
        try {
            writer = new PrintWriter(newFileName, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }


        // Set up new file for writing
        for (String line : docLines) {
            List<String> words = Arrays.asList(line.split(" "));
            for (String word : words) {
                String originalWord = word;
                // separates punctuation from word
                List<String> wordAndPunct = Arrays.asList(word.split("\\b+"));
                if (wordAndPunct.size() > 0) {
                    word = wordAndPunct.get(0);
                }

                // If the word is spelled correctly, move to the next one
                // ensure word is compared to lowercase word in tree
                if (tree.wordExists(word.toLowerCase())) {
                    writer.print(originalWord + " ");
                    continue;
                }

                List<Suggestion> suggestions = tree.getSuggestedWords(tree.getRootNode(), word.toLowerCase(), null);

                String selectedCorrection = getCorrectionFromUser(suggestions, word);

                // add punctuation back onto replaced word.
                String punct = "";
                if (wordAndPunct.size() > 0) {
                    punct = String.join("", wordAndPunct.subList(1, wordAndPunct.size()));
                }
                // Re-add capital letters to replaced words
                if(Character.isUpperCase(word.charAt(0))) {
                    String leading = selectedCorrection.substring(0,1).toUpperCase();
                    String trailing = selectedCorrection.substring(1);
                    selectedCorrection = leading + trailing;
                }

                // Add corrected word  and punctuation into document with
                // spaces in between each word
                writer.print(selectedCorrection + punct + " ");
            }
                writer.println();
        }
        // Close checked text file
        writer.close();
        System.out.println("\uD83C\uDF84 All done! \uD83C\uDF84");
    }
}


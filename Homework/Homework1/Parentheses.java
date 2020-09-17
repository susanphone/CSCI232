// CSCI 232 : Problem Set 1
// Susan McCartney
// Due August 28, 2020

/* 1.3.4 Write a stack client Parentheses that reads in a text stream from standard 
input and uses a stack to determine whether its parentheses are properly balanced.  
For example, your program should print true for [()]{}{[()()]()} and false for [(]).
Include source code and a few sample runs.
*/

// use Dijkstra's Two-Stack Algorithm for Expression Evaluation
package Homework.Homework1;

import java.util.Stack;

public class Parentheses {
    // All the characters that we care about for matching up; left and right
    private static char lParenthesis = '(';
    private static char rParenthesis = ')';
    private static char lBrace = '{';
    private static char rBrace = '}';
    private static char lBracket = '[';
    private static char rBracket = ']';

    // A boolean that takes and string and matches up the left and right special
    // characters
    public static boolean isMatched(String spec) {
        Stack<Character> specialCharacterStack = new Stack<Character>();

        for (int i = 0; i < spec.length(); i++) {
            final char currentChar = spec.charAt(i);

            // If current the current character passes through this if statement, it will
            // return
            // as a match; TRUE
            if (currentChar == lParenthesis || currentChar == lBrace || currentChar == lBracket) {
                specialCharacterStack.push(currentChar);
            }

            // If the current character passes through this if statement or the else if
            // statements below,
            // it will return as not a match; FALSE
            if (currentChar == rParenthesis) {
                if (specialCharacterStack.isEmpty() || specialCharacterStack.pop() != lParenthesis) {
                    return false;
                }
            }

            else if (currentChar == rBrace) {
                if (specialCharacterStack.isEmpty() || specialCharacterStack.pop() != lBrace) {
                    return false;
                }
            }

            else if (currentChar == rBracket) {
                if (specialCharacterStack.isEmpty() || specialCharacterStack.pop() != lBracket) {
                    return false;
                }
            }
        }
        return specialCharacterStack.isEmpty();
    }

    public static void main(String[] args) {
        // True string should return true, false string should return false.
        String trueString = "[()]{}{[()()]()}";
        String falseString = "[(])";
        // Call the isMatched function
        System.out.println("This string is " + isMatched(trueString)); // Returns true
        System.out.println("This string is " + isMatched(falseString)); // Returns false

    }
}
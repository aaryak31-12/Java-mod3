
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Exercise20_11 {
    public static void main(String[] args) throws FileNotFoundException {

        //chechiking if a file name was provided
        if (args.length != 1) {
            System.out.println("Usage: java Exercise20_11 filename");
            System.exit(1);
        }

        //creating a file object and check if the file exists
        File file = new File(args[0]);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.exit(2);
        }

        //creating a scanner to read the file
        Scanner input = new Scanner(file);

        // stacking stores opening grouping symbols
        Stack<Character> stack = new Stack<>();

        boolean correct = true;


        

        // reading the source file one line at a time
        while (input.hasNextLine() && correct) {
            String line = input.nextLine();

            // checking each character in the line
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);

                // pushing opening symbols onto the stack
                if (ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch);
                }

                // checking closing symbols
                else if (ch == ')' || ch == '}' || ch == ']') {

                    if (stack.isEmpty()) {
                        correct = false;
                        break;
                    }

                    // removing the last opening symbol from the stack
                    char opening = stack.pop();

                    // checking if opening and closing symbols match
                    if ((ch == ')' && opening != '(') ||
                        (ch == '}' && opening != '{') ||
                        (ch == ']' && opening != '[')) {

                        correct = false;
                        break;
                    }
                }
            }
        }

        // closing the scanner
        input.close();

        if (!stack.isEmpty()) {
            correct = false;
        }

        // displaying the result
        if (correct) {
            System.out.println("Correct grouping pairs");
        }
        else {
            System.out.println("Incorrect grouping pairs");
        }

    }






}

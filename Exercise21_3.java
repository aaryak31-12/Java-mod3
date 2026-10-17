
import java.io.*;
import java.util.*;

public class Exercise21_3 {
    public static void main(String[] args) throws Exception {

        // getting the filename from the command line argument
        if (args.length != 1) {
            System.out.println("Usage: java Exercise21_03 filename");
            System.exit(1);
        }

        File file = new File(args[0]);

        // checking if the file exists
        if (file.exists()) {
            System.out.println("The number of keywords in the program is "
                    + countKeywords(file));
        }
        else {
            System.out.println("File " + args[0] + " does not exist");
        }
    }

    public static int countKeywords(File file) throws Exception {

        // array
        String[] keywordString = {"abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto",
            "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile",
            "while", "true", "false", "null"};

        // creating a hashset to store the keywords
        Set<String> keywordSet =
            new HashSet<>(Arrays.asList(keywordString));

        int count = 0;

        // opening the Java source file
        Scanner input = new Scanner(file);

        // keeping track of comments and strings
        boolean blockComment = false;
        boolean inString = false;
        boolean inChar = false;



        // reading the file one line at a time
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String word = "";

            // checking each character in the line
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);

                // skipping characters inside block comments
                if (blockComment) {
                    if (ch == '*' && i + 1 < line.length()
                            && line.charAt(i + 1) == '/') {
                        blockComment = false;
                        i++;
                    }
                }

                // skipping characters inside strings
                else if (inString) {
                    if (ch == '\\') {
                        i++;
                    }
                    else if (ch == '"') {
                        inString = false;
                    }
                }

                
                // skipping characters inside character literals
                else if (inChar) {
                    if (ch == '\\') {
                        i++;
                    }
                    else if (ch == '\'') {
                        inChar = false;
                    }
                }


                // building a word using Java identifier characters
                else if (Character.isJavaIdentifierPart(ch)) {
                    word += ch;
                }

                else {
                    // checking if the completed word is a keyword
                    if (keywordSet.contains(word)) {
                        count++;
                    }
                    word = "";

                    // checking for a line comment
                    if (ch == '/' && i + 1 < line.length()
                            && line.charAt(i + 1) == '/') {
                        break;
                    }

                    // checking for a block comment
                    else if (ch == '/' && i + 1 < line.length()
                            && line.charAt(i + 1) == '*') {
                        blockComment = true;
                        i++;
                    }

                    // checking for the beginning of a string
                    else if (ch == '"') {
                        inString = true;
                    }

                    // checking for a character literal
                    else if (ch == '\'') {
                        inChar = true;
                    }
                }
            }

            // checking the last word in each line
            if (keywordSet.contains(word)) {
                count++;
            }
        }

        // closing
        input.close();
        return count;


    }



}

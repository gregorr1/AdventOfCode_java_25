import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Naloga3 {
    public static List<String> getInput() {
        String path = "src/input/input3.txt";
        List<String> input = Collections.emptyList();
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
        return input;
    }

    public static void naloga3_1() {
        /* Instructions available here: https://adventofcode.com/2025/day/3 */

        List<String> input = getInput();
        int sum = 0;

        for (String string : input) {
            String highest = findHighestTwo(string);
            try {
                sum += Integer.parseInt(highest);
            } catch (Exception e) {
                System.out.println("Error, invalid number!");
            }
        }

        System.out.println("The result is " + sum);
    }

    public static void naloga3_2() {
        List<String> input = getInput();
        long sum = 0;
        int joltageLength = 12;

        for (String string : input) {
            String highest = findHighest(string, joltageLength);
            try {
                sum += Long.parseLong(highest);
            } catch (Exception e) {
                System.out.println("Error, invalid number!");
            }
        }

        System.out.println("The result is " + sum);
    }

    // A method that finds the first instance of the highest digit in the string (with at least one digit left) and then the first instance of the highest digit in the remainder of the string
    public static String findHighestTwo(String input) {
        String highest = "";
        for (int i = 9; i > 0; i--) {
            for (int j = 0; j < input.length() - 1; j++) {
                if (input.charAt(j) - '0' == i) {
                    highest += input.charAt(j);
                    for (int k = 9; k > 0; k--) {
                        for (int l = j + 1; l < input.length(); l++) {
                            if (input.charAt(l) - '0' == k) {
                                highest += input.charAt(l);
                                return highest;
                            }
                        }
                    }
                }
            }
        }
        return "Error, no number found!";
    }

    // An improved version of the method above that adds the possibility to include the desired length of the result. The method then loops until the highest possible number of a given length is found in the string.
    public static String findHighest(String input, int length) {
        int remLength = length;
        int startIndex = 0;
        String highest = "";
        
        for (int i = 0; i < length; i++) {
            boolean found = false;
            for (int j = 9; j > 0; j--) {
                if (!found) {
                    for (int k = startIndex; k < (input.length() - remLength + 1); k++) {
                        if (input.charAt(k) - '0' == j) {
                            highest += input.charAt(k);
                            startIndex = k + 1;
                            remLength--;
                            found = true;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        
        return highest;
    }
}

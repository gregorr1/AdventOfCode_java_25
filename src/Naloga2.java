import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Naloga2 {
    public static List<String> getInput() {
        String path = "src/input/input2.txt";
        List<String> input = Collections.emptyList();
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
        return input;
    }

    public static void naloga2_1() {
        /* Instructions available here: https://adventofcode.com/2025/day/2 */

        List<String> lstInput = getInput();
        String input = lstInput.get(0);
        long sum = 0;
        String[] substrings = input.split(",");

        // Parse each string into a range
        for (String string : substrings) {
            String[] range = string.split("-");
            long start = 0;
            long end = 0;
             try {
                start = Long.parseLong(range[0]);
                end = Long.parseLong(range[1]);
            } catch (Exception e) {
                System.out.println("Error, invalid number!");
                continue;
            }

            // Split all numbers with an even number of digits in half and compare if both halves are equal
            for (long i = start; i <= end; i++) {
                String strNumber = String.valueOf(i);
                if (strNumber.length() %2 == 0) {
                    int splitIndex = strNumber.length() / 2;
                    if (strNumber.substring(0, splitIndex).equals(strNumber.substring(splitIndex))) {
                        sum += i;
                    }
                }
            }

        }

        System.out.println("The result is " + sum);
    }

    public static void naloga2_2() {
        List<String> lstInput = getInput();
        String input = lstInput.get(0);
        long sum = 0;
        String[] substrings = input.split(",");

        for (String string : substrings) {
            String[] range = string.split("-");
            long start = 0;
            long end = 0;
             try {
                start = Long.parseLong(range[0]);
                end = Long.parseLong(range[1]);
            } catch (Exception e) {
                System.out.println("Error, invalid number!");
                continue;
            }

            // Simplified approach with regex that finds any string that consists in its entirety of repeating digits or groups of digits
            for (long i = start; i <= end; i++) {
                String strNumber = String.valueOf(i);
                if (Pattern.matches("^(\\d+)\\1+$", strNumber)) {
                    sum += i;
                }
            }
        }

        System.out.println("The result is " + sum);
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Naloga1 {
    public static List<String> getInput() {
        String path = "src/input/input1.txt";
        List<String> input = Collections.emptyList();
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
        return input;
    }

    public static void naloga1_1() {
        /* Instructions available here: https://adventofcode.com/2024/day/1 */

        List<String> input = getInput();
        int current = 50;
        int sum = 0;

        // The first character of each string should be (L)eft or (R)ight. The rest should be parsed into the number of steps.
        for (String string : input) {
            char direction = string.charAt(0);
            int steps = 0;
            try {
                steps = Integer.parseInt(string.substring(1));
            } catch (Exception e) {
                System.out.println("Error, invalid number of steps!");
            }

            // If we go left, we decrease the current number at the dial; if we go right, we increase it
            if (direction == 'L') {
                current -= steps;
            } else if (direction == 'R') {
                current += steps;
            } else {
                System.out.println("Error, invalid direction!");
            }

            // If the current number is divisible by 100, it means it's at 0 on the dial
            if (current%100 == 0) {
                sum++;
            }
        }

        System.out.println("The result is " + sum);
    }

    public static void naloga1_2() {
        List<String> input = getInput();
        int current = 50;
        int sum = 0;

        for (String string : input) {
            char direction = string.charAt(0);
            int steps = 0;
            try {
                steps = Integer.parseInt(string.substring(1));
            } catch (Exception e) {
                System.out.println("Error, invalid number of steps!");
            }

            if (steps == 0) {
                continue;
            }

            if (direction == 'L') {
                // If we go left from '0', the earliest we can reach '0' again is after 100 steps, so we must increase the current number, otherwise it becomes negative after the smallest possible move.
                if (current == 0) {
                    current += 100;
                }
                current -= steps;
            } else if (direction == 'R') {
                current += steps;
            } else {
                System.out.println("Error, invalid direction!");
            }

            if (current < 0) {
                while (current < 0) {
                    sum++;
                    current += 100;
                }
                // If the current number reaches '0' at the end of adding hundreds and counting steps, this new '0' must be recorded in the sum
                if (current == 0) {
                    sum++;
                }
            } else if (current > 99) {
                // The extra step from above does not have to be recorded here, because 100 is not a valid current number to end the loop
                while (current > 99) {
                    sum++;
                    current -= 100;
                }
            } else if (current == 0) {
                sum++;
            }
        }

        System.out.println("The result is " + sum);
    }
}

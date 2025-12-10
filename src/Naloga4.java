import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Naloga4 {
    public static List<String> getInput() {
        String path = "src/input/input4.txt";
        List<String> input = Collections.emptyList();
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
        return input;
    }

    public static void naloga4_1() {
        /* Instructions available here: https://adventofcode.com/2025/day/4 */

        List<String> input = getInput();
        int sum = 0;
        int maxAdjacents = 3;

        // Iterate over input and run the checkSurroundings() method for each roll
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == '@') {
                    int adjacents = checkSurroundings(input, i, j);
                    // The roll in question is included in the number of rolls, returned by the checkSurroundings() method, so we must increase the threshold by one
                    if (adjacents <= maxAdjacents + 1) {
                        sum++;
                    }
                }
            }
        }

        System.out.println("The result is " + sum);
    }

    public static void naloga4_2() {
        List<String> input = getInput();
        int sum = 0;
        int maxAdjacents = 3;

        // Loop by comparing the previous and the current sum and break the loop when these numbers are the same, e.g. no new rolls are found and uncovered. During each iteration of the loop, a new input is created with the removed rolls marked.
        while(true) {
            List<String> newInput = new ArrayList<>();
            int prevSum = sum;
            for (int i = 0; i < input.size(); i++) {
                String line = "";
                for (int j = 0; j < input.get(i).length(); j++) {
                    if (input.get(i).charAt(j) == '@') {
                        int adjacents = checkSurroundings(input, i, j);
                        if (adjacents <= maxAdjacents + 1) {
                            sum++;
                            line += ".";
                        } else {
                            line += input.get(i).charAt(j);
                        }
                    } else {
                        line += input.get(i).charAt(j);
                    }
                }
                newInput.add(line);
            }
            input = new ArrayList<>(newInput);

            if (prevSum == sum) {
                break;
            }
        }
        
        System.out.println("The result is " + sum);
    }

    // Determine the position in the input and check the surrounding fields that are inside bounds. Returns the number of adjacent elements, defined by the symbol in the input.
    public static int checkSurroundings(List<String> input, int row, int col) {
        int startRow = Math.max(0, row - 1);
        int endRow = Math.min(input.size() - 1, row + 1);
        int startCol = Math.max(0, col - 1);
        int endCol = Math.min(input.get(row).length() - 1, col + 1);
        int adjacents = 0;
        
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (input.get(i).charAt(j) == '@') {
                    adjacents++;
                }
            }
        }
        return adjacents;
    }
}

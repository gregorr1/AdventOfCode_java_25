import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Naloga7 {
    public static List<String> getInput() {
        String path = "src/input/input7.txt";
        List<String> input = Collections.emptyList();
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
        return input;
    }

    public static void naloga7_1() {
        /* Instructions available here: https://adventofcode.com/2025/day/7 */

        List<String> input = getInput();
        int sum = 0;
        boolean[][] matrix = new boolean[input.size()][input.get(0).length()];

        // Mark all fields with beams as 'true' in the matrix and add up all splits
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (i > 0) {
                    if (input.get(i - 1).charAt(j) == 'S') {
                        matrix[i][j] = true;
                    }
                    if (matrix[i - 1][j] == true) {
                        if (input.get(i).charAt(j) != '^') {
                            matrix[i][j] = true;
                        } else {
                            if (j > 0) {
                                matrix[i][j - 1] = true;
                            }
                            if (j < input.get(i).length() - 1) {
                                matrix[i][j + 1] = true;
                            }
                            sum++;
                        }
                    }
                }
            }
        }

        System.out.println("The result is " + sum);
    }

    public static void naloga7_2() {
        List<String> input = getInput();
        long sum = 0;
        long[][] matrix = new long[input.size()][input.get(0).length()];

        // Add up all the possible ways a beam could reach any specific field using a variation of Pascal's triangle
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (i > 0) {
                    if (input.get(i - 1).charAt(j) == 'S') {
                        matrix[i][j] += matrix[i - 1][j] + 1;
                    }
                    if (matrix[i - 1][j] > 0) {
                        if (input.get(i).charAt(j) != '^') {
                            matrix[i][j] += matrix[i - 1][j];
                        } else {
                            if (j > 0) {
                                matrix[i][j - 1] += matrix[i - 1][j];
                            }
                            if (j < input.get(i).length() - 1) {
                                matrix[i][j + 1] += matrix[i - 1][j];
                            }
                        }
                    }
                }
            }
        }

        // Add up all possible timelines in the last row
        for (long timelines : matrix[matrix.length - 1]) {
            sum += timelines;
        }

        System.out.println("The result is " + sum);
    }
}

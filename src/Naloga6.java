import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Naloga6 {
    public static List<String> getInput() {
        String path = "src/input/input6.txt";
        List<String> input = Collections.emptyList();
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
        return input;
    }

    public static void naloga6_1() {
        /* Instructions available here: https://adventofcode.com/2025/day/6 */

        List<String> input = getInput();
        List<List<String>> allLists = new ArrayList<>();
        long sum = 0;

        // Loop through the input and add all non-blank strings to lists so that same indices of different lists will be connected
        for (String string : input) {
            String[] subStrings = string.split(" ");
            List<String> tempList = new ArrayList<>();
            for (String s : subStrings) {
                if (!s.isBlank()) {
                    tempList.add(s);
                }
            }
            allLists.add(tempList);
        }

        // Make sure that all strings of all lists have the same number of elements
        int size = allLists.get(0).size();
        if (!checkInput(allLists)) {
            System.out.println("Input not valid!");
            return;
        }
        
        for (int i = 0; i < size; i++) {
            List<String> currentList = new ArrayList<>();
            for (List<String> list : allLists) {
                currentList.add(list.get(i));
            }
            sum += evaluateString(currentList);
        }

        System.out.println("The result is " + sum);
    } 
    
    public static void naloga6_2() {
        List<String> input = getInput();
        long sum = 0;
        List<String> reversedList = new ArrayList<>();

        modifyInput(input);

        // Create an up-down list by interating through all lists for each index
        for (int i = 0; i < input.get(0).length(); i++) {
            String st = "";
            for (int j = 0; j < input.size(); j++) {
                st += input.get(j).charAt(i);
            }
            reversedList.add(st);
        }

        // Create a list of reversed lists and save each section of numbers as a separate list. Sections are marked by a vertical line of whitespaces. When the end of a section is reached, add the currently created list (if not empty) to the list of all lists.
        List<List<String>> reversedAllLists = new ArrayList<>();
        boolean delimiter = false;
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < reversedList.size(); i++) {
            if (delimiter) {
                if (!tempList.isEmpty()) {
                    reversedAllLists.add(tempList);
                }
                tempList = new ArrayList<>();
            }
            delimiter = true;
            if (!reversedList.get(i).isBlank()) {
                tempList.add(reversedList.get(i));
                delimiter = false;
            }
        }
        if (!tempList.isEmpty()) {
            reversedAllLists.add(tempList);
        }

        for (List<String> revList : reversedAllLists) {
            sum += evaluateVertical(revList);
        }

        System.out.println("The result is " + sum);
    }  

    // Check if all lists are the same size
    public static boolean checkInput(List<List<String>> input) {
        for (int i = 0; i < input.size() - 1; i++) {
            if (input.get(i).size() != input.get(i + 1).size()) {
                return false;
            }
        }
        return true;
    }

    // Modify input so that all Strings are the same length. This is achieved by adding whitespaces to the end of the Strings that are shorter than max
    public static void modifyInput(List<String> input) {
        if (checkStringLengths(input)) {
            return;
        } else {
            int max = 0;
            for (int i = 0; i < input.size(); i++) {
                if (input.get(i).length() > max) {
                    max = input.get(i).length();
                }
            }
            for (int j = 0; j < input.size(); j++) {
                if (input.get(j).length() < max) {
                    String st = input.get(j);
                    while (st.length() < max) {
                        st += " ";
                    }
                    input.set(j, st);
                }
            }
        }
    }

    // Check if all Strings are the same length
    public static boolean checkStringLengths(List<String> input) {
        for (int i = 0; i < input.size() - 1; i++) {
            if (input.get(i).length() != input.get(i + 1).length()) {
                return false;
            }
        }
        return true;
    }

    // Take a list of strings, determine the operator from it and either add up or multiply the numbers in the list
    public static long evaluateString(List<String> input) {
        long sum = 1;
        String sign = input.get(input.size() - 1);
        for (int i = 0; i < input.size() - 1; i++) {
            int num = 0;
            try {
                num = Integer.parseInt(input.get(i));
            } catch (Exception e) {
                System.out.println("Error, invalid number!");
            }
            if (sign.equals("+")) {
                sum += num;
            } else if (sign.equals("*")) {
                sum *= num;
            } else {
                System.out.println("Error, invalid sign!");
            }
        }
        // Because of multiplying, the starting number must be 1 which must be subtracted if we were adding numbers
        if (sign.equals("+")) {
            sum--;
        }
        return sum;
    }

    // Get the operator from the end of the first String, the add up or multiply the numbers in the list
    public static long evaluateVertical(List<String> input) {
        long sum = 1;
        char sign = input.get(0).charAt(input.get(0).length() - 1);
        input.set(0, input.get(0).substring(0, input.get(0).length() - 1));
        
        for (int i = 0; i < input.size(); i++) {
            int num = 0;
            try {
                num = Integer.parseInt(input.get(i).strip());
            } catch (Exception e) {
                System.out.println("Error, invalid number!");
            }
            if (sign == '+') {
                sum += num;
            } else if (sign == '*') {
                sum *= num;
            } else {
                System.out.println("Error, invalid sign!");
            }
        }
        // Because of multiplying, the starting number must be 1 which must be subtracted if we were adding numbers
        if (sign == '+') {
            sum--;
        }
        return sum;        
    }
}

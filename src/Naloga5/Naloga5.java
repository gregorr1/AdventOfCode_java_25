package Naloga5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Naloga5 {
    public static List<String> getInput() {
        String path = "src/input/input5.txt";
        List<String> input = Collections.emptyList();
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
        return input;
    }

    public static void naloga5_1() {
        /* Instructions available here: https://adventofcode.com/2025/day/5 */

        List<String> input = getInput();
        List<Long> listIds = new ArrayList<>();
        List<Range> listRanges = new ArrayList<>();
        int sum = 0;
        boolean isRange = true;

        // Before the first blank line, the strings are Ranges, then they are sole Longs
        for (String string : input) {
            if (string.isBlank()) {
                isRange = false;
                continue;
            }
            if (isRange) {
                String[] strRange = string.split("-");
                try {
                    long start = Long.parseLong(strRange[0]);
                    long end = Long.parseLong(strRange[1]);
                    listRanges.add(new Range(start, end));
                } catch (Exception e) {
                    System.out.println("Cannot parse this string!");
                }
            } else {
                try {
                    long id = Long.parseLong(string);
                    listIds.add(id);
                } catch (Exception e) {
                    System.out.println("Cannot parse this string!");
                }
            }            
        }

        // Iterate through the list of IDs and check for each whether it's within any Range
        for (Long id : listIds) {
            for (Range range : listRanges) {
                if (id >= range.getRangeStart() && id <= range.getRangeEnd()) {
                    sum++;
                    break;
                }
            }    
        }
        
        System.out.println("The result is " + sum);
    }

    public static void naloga5_2() {
        List<String> input = getInput();
        List<Range> listRanges = new ArrayList<>();
        long sum = 0;

        // In this task, only create the list of Ranges and sort it first by range start, then by range end
        for (String string : input) {
            if (string.isBlank()) {
                break;
            }

            String[] strRange = string.split("-");
            try {
                long start = Long.parseLong(strRange[0]);
                long end = Long.parseLong(strRange[1]);
                listRanges.add(new Range(start, end));
            } catch (Exception e) {
                System.out.println("Cannot parse this string!");
            }            
        }
        listRanges.sort(Range.BY_START_THEN_END);

        // Add the first Range to the sum, then compare each next one with the current one
        sum += (listRanges.get(0).getRangeEnd() - listRanges.get(0).getRangeStart() + 1);
        for (int i = 0; i < listRanges.size() - 1; i++) {
            if (listRanges.get(i + 1).getRangeStart() > listRanges.get(i).getRangeEnd()) {
                // If there is no overlap, e.g. the next Range starts after the current one ends, add the entire next range to the sum
                sum += (listRanges.get(i + 1).getRangeEnd() - listRanges.get(i + 1).getRangeStart() + 1);
            } else if (listRanges.get(i + 1).getRangeEnd() > listRanges.get(i).getRangeEnd()) {
                // If there is overlap, but the next Range ends after the current one, only add the difference in Range ends
                sum += (listRanges.get(i + 1).getRangeEnd() - listRanges.get(i).getRangeEnd());
            } else {
                // If the next Range is entirely covered by the current Range, remove the next Range and continue iterating with the Range from the list
                listRanges.remove(i + 1);
                i--;
            }
        }

        System.out.println("The result is " + sum);
    }
}
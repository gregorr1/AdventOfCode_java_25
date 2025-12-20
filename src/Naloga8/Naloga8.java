package Naloga8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Naloga8 {
    public static List<String> getInput() {
        String path = "src/input/input8.txt";
        List<String> input = Collections.emptyList();
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
        return input;
    }

    public static void naloga8_1() {
        /* Instructions available here: https://adventofcode.com/2025/day/8 */

        List<String> input = getInput();

        // Fill the list of all boxes
        List<Box> listBoxes = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String[] subStrings = input.get(i).split(",");
            int boxCoords = 3;
            int[] coords = new int[boxCoords];
            try {
                for (int j = 0; j < boxCoords; j++) {
                    coords[j] = Integer.parseInt(subStrings[j]);
                }
                listBoxes.add(new Box(i + 1, coords[0], coords[1], coords[2]));
            } catch (Exception e) {
                System.out.println("Cannot parse this number");
            }
        }

        // Fill the list of all pairs and sort by distance
        List<Pair> listPairs = new ArrayList<>();
        for (int i = 0; i < listBoxes.size() - 1; i++) {
            for (int j = i + 1; j < listBoxes.size(); j++) {
                listPairs.add(new Pair(listBoxes.get(i), listBoxes.get(j)));
            }
        }
        listPairs.sort(Pair.BY_DISTANCE);

        // Take the first 1000 pairs and arrange them into networks, then sort the list of networks by the size of networks
        List<Pair> shortestPairs = listPairs.subList(0, 1000);
        List<List<Box>> listNetworks = new ArrayList<>();
        for (Pair pr : shortestPairs) {
            int box1Network = -1;
            int box2Network = -1;

            // Get the ids of networks containing given boxes
            for (int i = 0; i < listNetworks.size(); i++) {
                if (listNetworks.get(i).contains(pr.getBox1())) {
                    box1Network = i;
                }
                if (listNetworks.get(i).contains(pr.getBox2())) {
                    box2Network = i;
                }
            }
            
            // If no box is in a network, create a new network of these two boxes
            if (box1Network < 0 && box2Network < 0) {
                List<Box> tempList = new ArrayList<>();
                tempList.add(listBoxes.get(pr.getBox1().getId() - 1));
                tempList.add(listBoxes.get(pr.getBox2().getId() - 1));
                listNetworks.add(tempList);
            }

            // If only one box is in a network, add the other box to that network
            if (box1Network >= 0 && box2Network < 0) {
                listNetworks.get(box1Network).add(listBoxes.get(pr.getBox2().getId() - 1));
            }
            if (box1Network < 0 && box2Network >= 0) {
                listNetworks.get(box2Network).add(listBoxes.get(pr.getBox1().getId() - 1));
            }

            // If both boxes are in different networks, merge the two networks
            if (box1Network >= 0 && box2Network >= 0) {
                if (box1Network != box2Network) {
                    listNetworks.get(box1Network).addAll(listNetworks.get(box2Network));
                    listNetworks.remove(box2Network);
                }
            }
        }
        listNetworks.sort(Comparator.comparingInt((List<Box> l) -> l.size()).reversed());

        int sum = (listNetworks.get(0).size() * listNetworks.get(1).size() * listNetworks.get(2).size());
        System.out.println("The result is " + sum);
    }   
    
    public static void naloga8_2() {
        List<String> input = getInput();

        // Fill the list of all boxes
        List<Box> listBoxes = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String[] subStrings = input.get(i).split(",");
            int boxCoords = 3;
            int[] coords = new int[boxCoords];
            try {
                for (int j = 0; j < boxCoords; j++) {
                    coords[j] = Integer.parseInt(subStrings[j]);
                }
                listBoxes.add(new Box(i + 1, coords[0], coords[1], coords[2]));
            } catch (Exception e) {
                System.out.println("Cannot parse this number");
            }
        }

        // Fill the list of all pairs and sort by distance
        List<Pair> listPairs = new ArrayList<>();
        for (int i = 0; i < listBoxes.size() - 1; i++) {
            for (int j = i + 1; j < listBoxes.size(); j++) {
                listPairs.add(new Pair(listBoxes.get(i), listBoxes.get(j)));
            }
        }
        listPairs.sort(Pair.BY_DISTANCE);

        // Sort the pairs into networks until one network contains every box in the list
        List<List<Box>> listNetworks = new ArrayList<>();
        int lastBox1X = 0;
        int lastBox2X = 0;
        for (Pair pr : listPairs) {
            int box1Network = -1;
            int box2Network = -1;

            // Get the ids of networks containing given boxes
            for (int i = 0; i < listNetworks.size(); i++) {
                if (listNetworks.get(i).contains(pr.getBox1())) {
                    box1Network = i;
                }
                if (listNetworks.get(i).contains(pr.getBox2())) {
                    box2Network = i;
                }
            }
            
            // If no box is in a network, create a new network of these two boxes
            if (box1Network < 0 && box2Network < 0) {
                List<Box> tempList = new ArrayList<>();
                tempList.add(listBoxes.get(pr.getBox1().getId() - 1));
                tempList.add(listBoxes.get(pr.getBox2().getId() - 1));
                listNetworks.add(tempList);
            }

            // If only one box is in a network, add the other box to that network
            if (box1Network >= 0 && box2Network < 0) {
                listNetworks.get(box1Network).add(listBoxes.get(pr.getBox2().getId() - 1));
            }
            if (box1Network < 0 && box2Network >= 0) {
                listNetworks.get(box2Network).add(listBoxes.get(pr.getBox1().getId() - 1));
            }

            // If both boxes are in different networks, merge the two networks
            if (box1Network >= 0 && box2Network >= 0) {
                if (box1Network != box2Network) {
                    listNetworks.get(box1Network).addAll(listNetworks.get(box2Network));
                    listNetworks.remove(box2Network);
                }
            }

            // Once one network contains all boxes, store the X coordinates of the last two boxes and stop processing further
            if (listNetworks.get(0).size() == listBoxes.size()) {
                lastBox1X = pr.getBox1().getX();
                lastBox2X = pr.getBox2().getX();
                break;
            }
        }

        int sum = lastBox1X * lastBox2X;
        System.out.println("The result is " + sum);
    }
}

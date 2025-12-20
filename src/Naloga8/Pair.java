package Naloga8;

import java.util.Comparator;

public class Pair {
    private Box box1;
    private Box box2;
    private double distance;
    
    public Pair(Box box1, Box box2) {
        this.box1 = box1;
        this.box2 = box2;
        this.distance = calculateDistance();
    }

    public Box getBox1() {
        return box1;
    }
    public Box getBox2() {
        return box2;
    }
    public double getDistance() {
        return distance;
    }
    
    private double calculateDistance() {
        return Math.sqrt(
            Math.pow(box1.getX() - box2.getX(), 2)
            + Math.pow(box1.getY() - box2.getY(), 2)
            + Math.pow(box1.getZ() - box2.getZ(), 2));
    }

    public static final Comparator<Pair> BY_DISTANCE = Comparator.comparingDouble(Pair::getDistance);
}

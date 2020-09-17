// CSCI 232 : Problem Set 1
// Susan McCartney
// Due August 28, 2020

/* 1.2.1 Write a Point2D client that takes an integer value n from the command line, 
generates n random points in the unit square, and computes the distance separating
the closest pair of points.
 */
package Homework.Homework1;

import java.util.Random;

public class Point2D {
    // fields
    private double x;
    private double y;

    // constructor
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Distance cannot be negative, so by using absolute values we can keep the
    // distance positive
    public double distanceTo(Point2D that) {
        double dToX = Math.abs(this.x - that.x);
        double dToY = Math.abs(this.y - that.y);
        // distance formula, pythagorean theorem
        return Math.sqrt((dToX * dToX) + (dToY * dToY));
    }

    // Keep the program from returning memory addresses
    @Override
    public String toString() {
        return "X: " + x + " Y: " + y;
    }

    public static void main(String args[]) {
        // generate n random points
        Random rand = new Random();
        final int n = 4;
        Point2D[] points = new Point2D[n];

        // Loop through n times and get the four different x and y coordinates
        for (int i = 0; i < n; i++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            points[i] = new Point2D(x, y);
        }

        // A temporary location for the shortest distance between two points
        double shortestDistance = Double.MAX_VALUE;
        int pointOneIndex = -1;
        int pointTwoIndex = -1;

        // Loop through all the points and retain the shortest distance between two
        // points
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = points[j].distanceTo(points[i]);
                if (shortestDistance > distance) {
                    shortestDistance = distance;
                    pointOneIndex = i;
                    pointTwoIndex = j;
                }
            }
        }

        // Print out the first and second points and the distance between them
        System.out.println("--------------------------------");
        System.out.println(points[pointOneIndex]);
        System.out.println(points[pointTwoIndex]);
        System.out.println(shortestDistance);

    }
}

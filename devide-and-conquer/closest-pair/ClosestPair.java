import java.util.*;

class Point {
    int x, y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class ClosestPair {
    private static final int INF = Integer.MAX_VALUE;

    // Function to calculate distance between two points
    private static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    // Brute force method to find minimum distance
    private static double bruteForce(Point[] P, int n) {
        double min = INF;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                min = Math.min(min, distance(P[i], P[j]));
            }
        }
        return min;
    }

    // Function to find the distance between the closest points of strip of a given size
    private static double stripClosest(Point[] strip, int size, double d) {
        double min = d;
        Arrays.sort(strip, 0, size, (a, b) -> Integer.compare(a.y, b.y));

        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                min = Math.min(min, distance(strip[i], strip[j]));
            }
        }
        return min;
    }

    // A recursive function to find the smallest distance
    private static double closestUtil(Point[] P, int n) {
        if (n <= 3) {
            return bruteForce(P, n);
        }

        int mid = n / 2;
        Point midPoint = P[mid];

        double dl = closestUtil(P, mid);
        double dr = closestUtil(Arrays.copyOfRange(P, mid, n), n - mid);

        double d = Math.min(dl, dr);

        Point[] strip = new Point[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(P[i].x - midPoint.x) < d) {
                strip[j] = P[i];
                j++;
            }
        }

        return Math.min(d, stripClosest(strip, j, d));
    }

    // The main function that finds the smallest distance
    public static double closest(Point[] P) {
        Arrays.sort(P, (a, b) -> Integer.compare(a.x, b.x));
        return closestUtil(P, P.length);
    }

    public static void main(String[] args) {
        Point[] P = new Point[]{
            new Point(2, 3),
            new Point(12, 30),
            new Point(40, 50),
            new Point(5, 1),
            new Point(12, 10),
            new Point(3, 4)
        };

        double minDistance = closest(P);
        System.out.println("The smallest distance is " + minDistance);

        // Find and print the closest pair
        Point p1 = null, p2 = null;
        for (int i = 0; i < P.length; i++) {
            for (int j = i + 1; j < P.length; j++) {
                if (distance(P[i], P[j]) == minDistance) {
                    p1 = P[i];
                    p2 = P[j];
                    break;
                }
            }
            if (p1 != null) break;
        }

        System.out.println("The closest pair of points are:");
        System.out.println("(" + p1.x + ", " + p1.y + ") and (" + p2.x + ", " + p2.y + ")");
    }
}
import java.util.*;

public class QuickHull {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Set<Point> quickHull(List<Point> points) {
        Set<Point> convexHull = new HashSet<>();
        if (points.size() < 3) {
            convexHull.addAll(points);
            return convexHull;
        }

        int minPoint = 0, maxPoint = 0;
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).x < points.get(minPoint).x)
                minPoint = i;
            if (points.get(i).x > points.get(maxPoint).x)
                maxPoint = i;
        }
        Point A = points.get(minPoint);
        Point B = points.get(maxPoint);

        convexHull.add(A);
        convexHull.add(B);

        List<Point> leftSet = new ArrayList<>();
        List<Point> rightSet = new ArrayList<>();

        for (Point p : points) {
            if (findSide(A, B, p) == 1)
                leftSet.add(p);
            else if (findSide(A, B, p) == -1)
                rightSet.add(p);
        }
        hullSet(A, B, rightSet, convexHull);
        hullSet(B, A, leftSet, convexHull);

        return convexHull;
    }

    public static void hullSet(Point A, Point B, List<Point> set, Set<Point> hull) {
        if (set.isEmpty())
            return;
        if (set.size() == 1) {
            hull.add(set.get(0));
            return;
        }
        int dist = Integer.MIN_VALUE;
        Point furthest = null;
        for (Point p : set) {
            int distance = distance(A, B, p);
            if (distance > dist) {
                dist = distance;
                furthest = p;
            }
        }
        hull.add(furthest);
        List<Point> leftSetAP = new ArrayList<>();
        List<Point> leftSetPB = new ArrayList<>();
        for (Point p : set) {
            if (findSide(A, furthest, p) == 1)
                leftSetAP.add(p);
            if (findSide(furthest, B, p) == 1)
                leftSetPB.add(p);
        }
        hullSet(A, furthest, leftSetAP, hull);
        hullSet(furthest, B, leftSetPB, hull);
    }

    public static int findSide(Point A, Point B, Point P) {
        int val = (P.y - A.y) * (B.x - A.x) - (B.y - A.y) * (P.x - A.x);
        if (val > 0)
            return 1;
        if (val < 0)
            return -1;
        return 0;
    }

    public static int distance(Point A, Point B, Point C) {
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        return Math.abs(num);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Point> points = new ArrayList<>();

        System.out.println("Enter the number of points:");
        int n = scanner.nextInt();

        System.out.println("Enter the points (x y):");
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add(new Point(x, y));
        }

        Set<Point> hull = quickHull(points);
        
        System.out.println("Convex Hull points:");
        for (Point p : hull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }

        scanner.close();
    }
}
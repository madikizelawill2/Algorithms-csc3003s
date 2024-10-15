import java.util.*;

public class ShortestPath {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1, 1, 0, 0}; // Up, Down, Left, Right
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter grid dimensions (rows columns):");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] grid = new int[rows][cols];

        System.out.println("Enter the grid (0 for open cell, 1 for obstacle):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter start point (row column):");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();

        System.out.println("Enter end point (row column):");
        int endX = scanner.nextInt();
        int endY = scanner.nextInt();

        int shortestPath = bfs(grid, startX, startY, endX, endY);
        
        if (shortestPath == -1) {
            System.out.println("No path found");
        } else {
            System.out.println("Shortest path length: " + shortestPath);
        }

        scanner.close();
    }

    public static int bfs(int[][] grid, int startX, int startY, int endX, int endY) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY));
        visited[startX][startY] = true;

        int pathLength = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();

                if (current.x == endX && current.y == endY) {
                    return pathLength;
                }

                for (int j = 0; j < 4; j++) {
                    int newX = current.x + dx[j];
                    int newY = current.y + dy[j];

                    if (isValid(newX, newY, rows, cols) && grid[newX][newY] == 0 && !visited[newX][newY]) {
                        queue.offer(new Point(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            pathLength++;
        }

        return -1; // No path found
    }

    public static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
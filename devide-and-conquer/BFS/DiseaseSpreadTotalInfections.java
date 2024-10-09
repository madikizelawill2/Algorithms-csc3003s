import java.util.*;

public class DiseaseSpreadTotalInfections {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static int totalInfections(char[][] grid, int startX, int startY) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<Point> queue = new LinkedList<>();
        
        queue.offer(new Point(startX, startY));
        visited[startX][startY] = true;
        int totalInfected = 1;  // Start with 1 for the initial infection
        
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];
                
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols 
                    && !visited[newX][newY] && grid[newX][newY] == 'P') {
                    
                    queue.offer(new Point(newX, newY));
                    visited[newX][newY] = true;
                    totalInfected++;
                }
            }
        }
        
        return totalInfected;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int startX = scanner.nextInt() - 1;  // Adjust for 0-based indexing
        int startY = scanner.nextInt() - 1;  // Adjust for 0-based indexing
        
        char[][] grid = new char[rows][cols];
        scanner.nextLine();  // Consume the newline
        for (int i = 0; i < rows; i++) {
            grid[i] = scanner.nextLine().toCharArray();
        }
        
        int result = totalInfections(grid, startX, startY);
        System.out.println(result);
        
        scanner.close();
    }
}
import java.util.*;

public class DiseaseSpreadBFS {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static class Point {
        int x, y, day;
        Point(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
    
    public static int maxNewInfections(char[][] grid, int startX, int startY) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<Point> queue = new LinkedList<>();
        Map<Integer, Integer> infectionsPerDay = new HashMap<>();
        
        queue.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;
        infectionsPerDay.put(0, 1);  // Day 0 (initial infection)
        
        int maxNewInfections = 1;
        
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];
                int newDay = current.day + 1;
                
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols 
                    && !visited[newX][newY] && grid[newX][newY] == 'P') {
                    
                    queue.offer(new Point(newX, newY, newDay));
                    visited[newX][newY] = true;
                    
                    infectionsPerDay.put(newDay, infectionsPerDay.getOrDefault(newDay, 0) + 1);
                    maxNewInfections = Math.max(maxNewInfections, infectionsPerDay.get(newDay));
                }
            }
        }
        
        return maxNewInfections;
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
        
        int result = maxNewInfections(grid, startX, startY);
        System.out.println("The greatest number of newly infected populations in any one day: " + result);
        
        scanner.close();
    }
}


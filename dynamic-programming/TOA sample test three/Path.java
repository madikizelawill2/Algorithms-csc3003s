import java.util.Scanner;

public class Path {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read grid size
        int N = scanner.nextInt();
        
        // Read number of obstacles
        int K = scanner.nextInt();
        
        // Initialize grid
        long[][] grid = new long[N][N];
        
        // Place obstacles
        for (int i = 0; i < K; i++) {
            int x = scanner.nextInt() - 1; // Convert to 0-based index
            int y = scanner.nextInt() - 1;
            grid[y][x] = -1; // Mark obstacle
        }
        
        // Calculate paths
        long paths = calculatePaths(grid);
        
        // Output result
        System.out.println(paths);
        
        scanner.close();
    }
    
    public static long calculatePaths(long[][] grid) {
        int N = grid.length;
        
        // Initialize first row and column
        for (int i = 0; i < N; i++) {
            if (grid[0][i] == -1) break;
            grid[0][i] = 1;
        }
        for (int i = 0; i < N; i++) {
            if (grid[i][0] == -1) break;
            grid[i][0] = 1;
        }
        
        // Fill the grid
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (grid[i][j] == -1) continue; // Skip obstacles
                
                long fromLeft = (grid[i][j-1] == -1) ? 0 : grid[i][j-1];
                long fromTop = (grid[i-1][j] == -1) ? 0 : grid[i-1][j];
                
                grid[i][j] = fromLeft + fromTop;
            }
        }
        
        return (grid[N-1][N-1] == -1) ? 0 : grid[N-1][N-1];
    }
}

import java.util.Scanner;

public class Farming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the size of the grid
        int N = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        
        // Read the grid
        char[][] grid = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        
        // Find the largest square patch
        long result = largestSquarePatch(grid);
        
        // Output the result
        System.out.println(result);
        
        scanner.close();
    }
    
    public static long largestSquarePatch(char[][] grid) {
        int N = grid.length;
        long[][] dp = new long[N][N];
        long maxSize = 0;
        
        // Initialize the first row and column of dp
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
            dp[0][i] = 1;
            maxSize = 1;
        }
        
        // Fill the dp table
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (grid[i][j] == grid[i-1][j] && grid[i][j] == grid[i][j-1] && grid[i][j] == grid[i-1][j-1]) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    maxSize = Math.max(maxSize, dp[i][j]);
                } else {
                    dp[i][j] = 1;
                }
            }
        }
        
        return maxSize;
    }
}

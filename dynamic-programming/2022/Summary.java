import java.util.Scanner;

public class Summary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter m: ");
        int m = scanner.nextInt();
        
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        
        long result = summify(m, n);
        System.out.println("Summify(" + m + ", " + n + ") = " + result);
        
        scanner.close();
    }
    
    public static long summify(int m, int n) {
        // Create a 2D array to store intermediate results
        long[][] dp = new long[m + 1][n + 1];
        
        // Base case: Summify(0, n) = n
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = 0;
                for (int k = 1; k <= j; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }
        
        return dp[m][n];
    }
}

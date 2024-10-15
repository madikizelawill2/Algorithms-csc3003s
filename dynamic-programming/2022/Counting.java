import java.util.Scanner;

public class Counting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of N: ");
        int N = scanner.nextInt();
        
        int[] result = countZerosAndOnes(N);
        
        System.out.println("Number of zeroes (p): " + result[0]);
        System.out.println("Number of ones (q): " + result[1]);
        
        scanner.close();
    }

    public static int[] countZerosAndOnes(int N) {
        // Initialize dp array to store [zeroes, ones] for each n
        int[][] dp = new int[Math.max(N+1, 4)][2];
        
        // Base cases
        dp[0] = new int[]{1, 0}; // prints 0
        dp[1] = new int[]{0, 1}; // prints 1
        dp[2] = new int[]{1, 1}; // unknown(1) * unknown(-1)
        dp[3] = new int[]{2, 1}; // unknown(2) * unknown(0)
        
        // Fill dp array
        for (int n = 4; n <= N; n++) {
            int[] prev1 = dp[n-1];
            int[] prev3 = dp[n-3];
            dp[n][0] = prev1[0] + prev3[0];
            dp[n][1] = prev1[1] + prev3[1];
        }
        
        return dp[N];
    }
}
import java.util.Scanner;

public class FibonacciBottomUp {
    public static int fibBottomUp(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        
        int[] bottomUp = new int[n + 1];
        bottomUp[1] = 1;
        bottomUp[2] = 1;
        
        for (int i = 3; i <= n; i++) {
            bottomUp[i] = bottomUp[i-1] + bottomUp[i-2];
        }
        
        return bottomUp[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the value of n to calculate the nth Fibonacci number: ");
        int n = scanner.nextInt();
        
        long result = fibBottomUp(n);
        System.out.printf("The %dth Fibonacci number is: %d%n", n, result);
        
        scanner.close();
    }
}
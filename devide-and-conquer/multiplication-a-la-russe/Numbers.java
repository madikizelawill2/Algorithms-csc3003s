import java.util.Scanner;

public class Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read two integers
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        
        // Perform Russian Peasant Multiplication
        long result = russianPeasantMultiply(a, b);
        System.out.println(result);
        
        scanner.close();
    }

    public static long russianPeasantMultiply(int a, int b) {
        // Base cases
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a == 1) {
            return b;
        }
        if (b == 1) {
            return a;
        }

        // Recursive divide-and-conquer step
        if (a % 2 == 0) {
            // If a is even, divide a by 2 and double b
            return russianPeasantMultiply(a / 2, b * 2);
        } else {
            // If a is odd, subtract 1 from a and add b to the result
            return b + russianPeasantMultiply(a - 1, b);
        }
    }
}
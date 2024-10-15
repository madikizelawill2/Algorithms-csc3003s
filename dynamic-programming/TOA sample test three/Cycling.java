import java.util.Scanner;

public class Cycling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the number of signs
        int N = scanner.nextInt();
        
        // Read the values of the signs
        int[] signs = new int[N];
        for (int i = 0; i < N; i++) {
            signs[i] = scanner.nextInt();
        }
        
        // Calculate the maximum score
        int maxScore = calculateMaxScore(signs);
        
        // Output the result
        System.out.println(maxScore);
        
        scanner.close();
    }
    
    public static int calculateMaxScore(int[] signs) {
        int maxSoFar = 0;
        int maxEndingHere = 0;
        
        for (int sign : signs) {
            maxEndingHere = Math.max(0, maxEndingHere + sign);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
}
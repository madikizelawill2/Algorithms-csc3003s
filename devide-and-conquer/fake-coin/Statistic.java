import java.util.Scanner;

public class Statistic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read all input into an array
        double[] coins = readInputArray(scanner);
        
        // Find the sum using divide and conquer
        double totalSum = divideAndConquerSum(coins, 0, coins.length - 1);
        
        // Find the fake coin
        int fakeIndex = findFakeCoin(coins, totalSum);
        System.out.println(fakeIndex);
        
        scanner.close();
    }

    private static double[] readInputArray(Scanner scanner) {
        String line = scanner.nextLine().trim();
        String[] tokens = line.split("\\s+");
        double[] coins = new double[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            coins[i] = Double.parseDouble(tokens[i]);
        }
        return coins;
    }

    public static double divideAndConquerSum(double[] arr, int left, int right) {
        // Base case: if there's only one element, return it
        if (left == right) {
            return arr[left];
        }
        
        // Divide the array into two halves and recursively sum them
        int mid = left + (right - left) / 2;
        double leftSum = divideAndConquerSum(arr, left, mid);
        double rightSum = divideAndConquerSum(arr, mid + 1, right);
        
        // Combine the results
        return leftSum + rightSum;
    }

    public static int findFakeCoin(double[] coins, double totalSum) {
        int n = coins.length;
        double expectedSum = coins[0] * n; // Assuming the first coin is real
        
        if (Math.abs(totalSum - expectedSum) < 1e-10) {
            // All coins are the same weight
            return -1;
        }
        
        // Use divide and conquer to find the fake coin
        return findFakeCoinHelper(coins, 0, n - 1, coins[0]);
    }

    private static int findFakeCoinHelper(double[] coins, int left, int right, double realWeight) {
        // Base case: if there's only one coin, it's the fake one
        if (left == right) {
            return left;
        }
        
        // Divide the coins into two groups
        int mid = left + (right - left) / 2;
        
        // Calculate the sum of the left group
        double leftSum = divideAndConquerSum(coins, left, mid);
        double expectedLeftSum = realWeight * (mid - left + 1);
        
        // Decide which group to recurse on
        if (Math.abs(leftSum - expectedLeftSum) > 1e-10) {
            // The fake coin is in the left group
            return findFakeCoinHelper(coins, left, mid, realWeight);
        } else {
            // The fake coin is in the right group
            return findFakeCoinHelper(coins, mid + 1, right, realWeight);
        }
    }
}
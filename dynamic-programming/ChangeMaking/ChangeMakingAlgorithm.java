import java.util.Arrays;
import java.util.Scanner;

public class ChangeMakingAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of coin denominations: ");
        int n = scanner.nextInt();

        int[] coins = new int[n];
        System.out.println("Enter the coin denominations (can be in any order):");
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }

        System.out.print("Enter the target amount: ");
        int amount = scanner.nextInt();

        int[] result = minCoins(coins, amount);

        if (result[0] == Integer.MAX_VALUE) {
            System.out.println("It's not possible to make the target amount with the given coins.");
        } else {
            System.out.println("Minimum number of coins needed: " + result[0]);
            System.out.println("Coins used:");
            printCoinsUsed(coins, amount, result[1]);
        }

        scanner.close();
    }

    public static int[] minCoins(int[] coins, int amount) {
        Arrays.sort(coins); // Sort the coin denominations

        int[] dp = new int[amount + 1];
        int[] coinUsed = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    int subResult = dp[i - coin];
                    if (subResult != Integer.MAX_VALUE && subResult + 1 < dp[i]) {
                        dp[i] = subResult + 1;
                        coinUsed[i] = coin;
                    }
                } else {
                    // Since coins are sorted, we can break the loop
                    // when the coin value becomes greater than the current amount
                    break;
                }
            }
        }

        return new int[]{dp[amount], coinUsed[amount]};
    }

    public static void printCoinsUsed(int[] coins, int amount, int lastCoin) {
        if (amount == 0) return;
        System.out.println(lastCoin);
        printCoinsUsed(coins, amount - lastCoin, minCoins(coins, amount - lastCoin)[1]);
    }
}
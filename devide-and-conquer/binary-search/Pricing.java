import java.util.Scanner;

public class Pricing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long N = scanner.nextLong();
        scanner.close();

        long result = binarySearch(N);
        System.out.println(result);
    }

    private static long binarySearch(long N) {
        long left = 1;
        long right = 1_000_000; // Maximum possible K

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long cost = calculateCost(mid);

            if (cost <= N) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right; // right will be the largest K for which f(K) <= N
    }

    private static long calculateCost(long K) {
        long cost = 0;
        for (long j = 1; j < K; j++) {
            cost += j * (K / j);
            if (cost < 0) { // Check for overflow
                return Long.MAX_VALUE;
            }
        }
        return cost;
    }
}
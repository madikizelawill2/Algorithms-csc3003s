import java.util.Scanner;

public class WarshallAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        boolean[][] graph = new boolean[V][V];

        System.out.println("Enter the adjacency matrix (1 for edge, 0 for no edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt() == 1;
            }
        }

        boolean[][] reachability = warshall(graph);

        System.out.println("The transitive closure matrix is:");
        printSolution(reachability);

        scanner.close();
    }

    public static boolean[][] warshall(boolean[][] graph) {
        int V = graph.length;
        boolean[][] reachability = new boolean[V][V];

        // Initialize reachability matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                reachability[i][j] = graph[i][j];
            }
        }

        // Apply Warshall's algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    reachability[i][j] = reachability[i][j] || (reachability[i][k] && reachability[k][j]);
                }
            }
        }

        return reachability;
    }

    public static void printSolution(boolean[][] reachability) {
        int V = reachability.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print((reachability[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }
}
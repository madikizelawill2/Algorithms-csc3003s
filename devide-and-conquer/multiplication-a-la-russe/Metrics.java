import java.util.Scanner;

public class Metrics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read matrix dimensions
        int n = scanner.nextInt(); // Assuming square matrices for simplicity

        System.out.println("Enter matrix dimensions 1");
        
        // Read first matrix
        int[][] A = readMatrix(scanner, n);
        
        System.out.println("Enter matrix dimensions 2");
        // Read second matrix
        int[][] B = readMatrix(scanner, n);
        
        // Perform matrix multiplication
        int[][] result = strassenMultiply(A, B);
        
        // Print result
        printMatrix(result);
        
        scanner.close();
    }

    private static int[][] readMatrix(Scanner scanner, int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        
        // Base case: 1x1 matrix
        if (n == 1) {
            return new int[][]{{A[0][0] * B[0][0]}};
        }
        
        // Pad matrices if necessary
        int newSize = nextPowerOfTwo(n);
        if (newSize != n) {
            A = padMatrix(A, newSize);
            B = padMatrix(B, newSize);
            n = newSize;
        }
        
        // Divide matrices into quadrants
        int[][] A11 = subMatrix(A, 0, 0, n/2);
        int[][] A12 = subMatrix(A, 0, n/2, n/2);
        int[][] A21 = subMatrix(A, n/2, 0, n/2);
        int[][] A22 = subMatrix(A, n/2, n/2, n/2);
        int[][] B11 = subMatrix(B, 0, 0, n/2);
        int[][] B12 = subMatrix(B, 0, n/2, n/2);
        int[][] B21 = subMatrix(B, n/2, 0, n/2);
        int[][] B22 = subMatrix(B, n/2, n/2, n/2);
        
        // Compute 7 products recursively
        int[][] P1 = strassenMultiply(add(A11, A22), add(B11, B22));
        int[][] P2 = strassenMultiply(add(A21, A22), B11);
        int[][] P3 = strassenMultiply(A11, subtract(B12, B22));
        int[][] P4 = strassenMultiply(A22, subtract(B21, B11));
        int[][] P5 = strassenMultiply(add(A11, A12), B22);
        int[][] P6 = strassenMultiply(subtract(A21, A11), add(B11, B12));
        int[][] P7 = strassenMultiply(subtract(A12, A22), add(B21, B22));
        
        // Compute quadrants of result
        int[][] C11 = add(subtract(add(P1, P4), P5), P7);
        int[][] C12 = add(P3, P5);
        int[][] C21 = add(P2, P4);
        int[][] C22 = add(subtract(add(P1, P3), P2), P6);
        
        // Combine quadrants into result
        return combineMatrix(C11, C12, C21, C22);
    }

    private static int[][] subMatrix(int[][] matrix, int row, int col, int size) {
        int[][] subMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                subMatrix[i][j] = matrix[row + i][col + j];
            }
        }
        return subMatrix;
    }

    private static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    private static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    private static int[][] combineMatrix(int[][] C11, int[][] C12, int[][] C21, int[][] C22) {
        int n = C11.length * 2;
        int[][] result = new int[n][n];
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2; j++) {
                result[i][j] = C11[i][j];
                result[i][j + n/2] = C12[i][j];
                result[i + n/2][j] = C21[i][j];
                result[i + n/2][j + n/2] = C22[i][j];
            }
        }
        return result;
    }

    private static int[][] padMatrix(int[][] matrix, int newSize) {
        int[][] newMatrix = new int[newSize][newSize];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
        }
        return newMatrix;
    }

    private static int nextPowerOfTwo(int n) {
        return (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
    }
}

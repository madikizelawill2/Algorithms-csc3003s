def transpose_matrix(matrix, N, M):
    # Create a new matrix with swapped dimensions
    transposed = [[0 for _ in range(N)] for _ in range(M)]
    
    # Fill the transposed matrix
    for i in range(N):
        for j in range(M):
            transposed[j][i] = matrix[i][j]
    
    return transposed

# Read input
N, M = map(int, input().strip().split())

# Read the matrix
matrix = []
for _ in range(N):
    row = list(map(int, input().strip().split()))
    matrix.append(row)

# Transpose the matrix
transposed_matrix = transpose_matrix(matrix, N, M)

# Print the transposed matrix
for row in transposed_matrix:
    print(*row)
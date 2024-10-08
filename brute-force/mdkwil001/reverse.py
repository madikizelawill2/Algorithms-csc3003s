def reversed(matrix, N, M):
    # Create a new matrix with dimensions
    reversed_matrix = [[0 for _ in range(M)] for _ in range(N)]

    for i in range(N):
        for j in range(M):
            reversed_matrix[i][j] = matrix[N-1-i][M-1-j]
    
    return reversed_matrix

# Read input
N = int(input())
M = int(input())


# Read the matrix
matrix = []
for _ in range(N):
    row = list(map(int, input().strip().split()))
    matrix.append(row)

# reverse the matrix
reversed = reversed(matrix, N, M)

for row in reversed:
    print(*row)
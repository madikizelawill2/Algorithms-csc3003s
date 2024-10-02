def rotate_matrix(matrix):
    # Transpose the matrix
    transposed = list(zip(*matrix))
    
    # Reverse each row
    rotated = [list(row)[::-1] for row in transposed]
    
    return rotated

# Example usage
n, m = map(int, input("Enter the dimensions of the matrix (n m): ").split())
print("Enter the matrix elements row by row:")
matrix = [list(map(int, input().split())) for _ in range(n)]

rotated_matrix = rotate_matrix(matrix)

print("\nRotated Matrix:")
for row in rotated_matrix:
    print(*row)
def count_modulo_pairs(N, Z):
    count = 0
    for X in range(1, N):
        for Y in range(1, N):
            if (X * Y) % N == Z:
                count += 1
    return count

# Read input
N = int(input().strip())
Z = int(input().strip())

# Calculate and print the result
result = count_modulo_pairs(N, Z)
print(result)
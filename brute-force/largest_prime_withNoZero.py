def is_prime(n):
    if n < 2:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

def all_substrings_prime(n):
    str_n = str(n)
    for i in range(len(str_n)):
        if not is_prime(int(str_n[i:])):
            return False
    return True

def contains_zero(n):
    return '0' in str(n)

def find_largest_special_prime(N):
    for num in range(N-1, 1, -1):
        if not contains_zero(num) and is_prime(num) and all_substrings_prime(num):
            return num
    return -1

# Read input
N = int(input().strip())

# Find and print the result
result = find_largest_special_prime(N)
print(result)
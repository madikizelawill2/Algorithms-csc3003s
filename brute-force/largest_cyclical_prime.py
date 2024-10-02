def is_prime(n):
    if n < 2:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

def rotate_number(n):
    return int(str(n)[1:] + str(n)[0])

def is_cyclical_prime(n):
    if not is_prime(n):
        return False
    rotated = rotate_number(n)
    while rotated != n:
        if not is_prime(rotated):
            return False
        rotated = rotate_number(rotated)
    return True

def largest_cyclical_prime(N):
    for num in range(N-1, 1, -1):
        if is_cyclical_prime(num):
            return num
    return None

# Example usage
N = int(input("Enter a number N: "))
result = largest_cyclical_prime(N)

if result:
    print(f"The largest cyclical prime smaller than {N} is: {result}")
else:
    print(f"No cyclical prime found smaller than {N}")
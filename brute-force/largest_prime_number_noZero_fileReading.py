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

def find_largest_prime_substring(N):
    for num in range(N-1, 1, -1):
        if '0' not in str(num) and is_prime(num) and all_substrings_prime(num):
            return num
    return -1

def process_file(filename):
    results = []
    try:
        with open(filename, 'r') as file:
            for line in file:
                try:
                    N = int(line.strip())
                    result = find_largest_prime_substring(N)
                    results.append((N, result))
                except ValueError:
                    print(f"Skipping invalid input: {line.strip()}")
    except FileNotFoundError:
        print(f"Error: File '{filename}' not found.")
    except IOError:
        print(f"Error: Could not read file '{filename}'.")
    return results
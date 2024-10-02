
def simulate_load_shedding(N, K):
    # Initialize all stations to OFF (False)
    stations = [False] * N
    
    for _ in range(K):
        power = True  # Power from the main socket
        for i in range(N):
            if power:
                # If the station is receiving power, toggle its state
                stations[i] = not stations[i]
            power = power and stations[i]  # Power passes to next station if this one is ON
    
    # Check if the last station (N-1) is ON and receiving power
    return all(stations)

def process_test_cases(filename):
    results = []
    try:
        with open(filename, 'r') as file:
            T = int(file.readline().strip())  # Number of test cases
            
            for _ in range(T):
                N, K = map(int, file.readline().split())
                result = "ON" if simulate_load_shedding(N, K) else "OFF"
                results.append(result)
    
    except FileNotFoundError:
        print(f"Error: File '{filename}' not found.")
    except Exception as e:
        print(f"An error occurred while reading the file: {e}")
    
    return results

# Process the input file
filename = "ts1_input.txt"
results = process_test_cases(filename)

# Print the results
for i, result in enumerate(results, 1):
    print(f"Case #{i}: {result}")
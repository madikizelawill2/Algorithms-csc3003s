def max_score(numbers, target):
    # Initialize the dp array with -1 (unvisited)
    dp = [-1] * target
    dp[1] = 1  # Start with a score of 1

    def dfs(index, current_score):
        if index == len(numbers):
            return current_score
        
        if current_score >= target:
            return -1
        
        if dp[current_score] >= index:
            return -1
        
        dp[current_score] = index
        
        # Try adding the current number
        add_result = dfs(index + 1, current_score + numbers[index])
        
        # Try multiplying by the current number
        multiply_result = dfs(index + 1, current_score * numbers[index])
        
        return max(add_result, multiply_result)

    return dfs(0, 1) - 1  # Subtract 1 to get the score less than T

# Read input
N = int(input().strip())
numbers = [int(input().strip()) for _ in range(N)]
T = int(input().strip())

# Calculate and print the result
result = max_score(numbers, T)
print(result)
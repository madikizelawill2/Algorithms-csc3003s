
#TODO: toggle print statements to view diff in computation of the i'th value vs. its computation
def solve_coin_row(coins, i, table):
	if table[i] == -1: #if value is unknown, compute and cache
		#print('computing V[{0}]'.format(i))
		table[i] = max(
			solve_coin_row(coins, i-2, table) + coins[i-1], #if we are picking the current item
			solve_coin_row(coins, i-1, table) #if we are ignoring the current coin
		)
	#print('Using V[{0}]'.format(i))
	return table[i]

if __name__=='__main__':
	coins = [5, 1, 2, 10, 6, 2]

	#this doesn't need to be able a dict. it just might help ease of understanding
	table = {}
	table[0] = 0 #base condition 1
	table[1] = coins[0] #base condition 2
	for idx in range(2, len(coins)+1):
		table[idx] = -1 #we don't know the 'values' of the rest of the positions so set them to null
	
	print(solve_coin_row(coins, len(coins), table))

def naive_gcd(n, m):
	while True:
		print('naive_gcd({0}, {1})'.format(n,m))
		if n > m:
			n = n - m
		elif m > n:
			m = m - n
		else:
			break

def euclid_gcd(n, m):
	#TODO: implement this via a loop instead of recursive function
	return -1

if __name__=='__main__':
	naive_gcd(53, 3)
	euclid_gcd(53, 3)

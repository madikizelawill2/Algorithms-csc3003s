import matplotlib.pyplot as plt
from math import *
import random

    
#See https://en.wikipedia.org/wiki/Cross_product#Computational_geometry
def is_on_left(a, b, c):
    P = ((b[0] - a[0]) * (c[1] - a[1])) - ((c[0] - a[0]) * (b[1] - a[1]))
    
    if P > 0:
        return True
    else:
        return False

def get_random_points():
	NUM_POINTS = 10

	points = []
	for _ in range(NUM_POINTS):
	    x, y = random.randint(100,300), random.randint(100, 400)
	    points.append([x,y])
	return points

def get_convex_hull(points):
	convex_hull = []
	for p1 in points:
		for p2 in points:
			if p1 != p2:
				#TODO: clean up the following so that you don't have to maintain the directions array
				directions = []
				for p3 in points:
					directions.append(is_on_left(p1, p2, p3))
				if len(set(directions)) == 1:
					convex_hull.append(p1)
					convex_hull.append(p2)
	return convex_hull

def plot_points(points):
	hull_points = get_convex_hull(points)
	non_hull_points = [point for point in points if not point in hull_points]

	x1 = []
	y1 = []
	for point in non_hull_points:
		x1.append(point[0])
		y1.append(point[1])
		
	x2 = []
	y2 = []
	for point in hull_points:
		x2.append(point[0])
		y2.append(point[1])
		
	plt.scatter(x1,y1, c='b')
	plt.scatter(x2, y2, c='r')
	plt.show()

if __name__=='__main__':
	points = get_random_points()
	plot_points(points)


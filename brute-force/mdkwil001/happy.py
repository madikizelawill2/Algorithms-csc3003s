

def get_sum(n):
    sum = 0
    n = str(n)
    for i in range(len(n)):
        sum = sum + int(n[i])**2
    return sum

def happy(n):
    sum_arr = []
    temp_sum = 0
    sum_arr.append(get_sum(n))
    
    for i in sum_arr:
        temp_sum = get_sum(i)
        if temp_sum not in sum_arr:
            if temp_sum != 1:
                sum_arr.append(temp_sum)
    return temp_sum

def left_most(happy_value):
    happy_value = str(happy_value)
    new_happy_value = happy_value[1:]
    return happy(new_happy_value)

def happy_find(n):
    output = 0
    if happy(n):
        output = left_most(n)
    return output


if __name__ == '__main__':
    N = int(input())
    print(N)
    print(happy_find(N))
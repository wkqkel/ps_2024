import math
num = input()

arr = [0] * 10
for i in str(num): arr[int(i)] += 1
arr[6] += arr[9]
arr[6] = math.ceil(arr[6] / 2)
print(max(arr[0:9]))
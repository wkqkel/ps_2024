from collections import Counter
mul = int(input()) * int(input()) * int(input())
counter = Counter(str(mul))
for i in range(0,10): print(counter[str(i)])
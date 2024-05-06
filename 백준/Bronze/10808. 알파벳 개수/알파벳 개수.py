str = input()

dic = dict()

for c in 'abcdefghijklmnopqrstuvwxyz':
     dic[c] = 0


for c in str:
    dic[c] += 1;

print(*list(dic.values()))

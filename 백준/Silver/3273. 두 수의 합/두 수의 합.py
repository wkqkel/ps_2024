n = int(input())
arr = list(map(int, input().split()))
x = int(input())

dic = {}
for i in arr: 
    dic[i] = True
    

cnt = 0
for i in arr: 
    # print(x-i)
    if x-i in dic: cnt+=1

print(cnt // 2)
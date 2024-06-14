def hanoi(n, a, b, ans):
    if n == 0: return
    hanoi(n - 1, a, 6 - a - b, ans)
    ans.append([a,b])
    hanoi(n - 1, 6 - a - b, b, ans)
    

def solution(n):
    answer = []
    hanoi(n, 1, 3, answer)
    return answer
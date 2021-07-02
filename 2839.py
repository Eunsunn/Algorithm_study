# https://www.acmicpc.net/problem/2839

def solution():
    n = int(input())
    # 3 <= n <= 5000
    for i in range(n // 5, -1, -1):
        remain = n - 5 * i
        if remain % 3 == 0:
            return i + remain // 3
    return -1

print(solution())
# https://www.acmicpc.net/problem/1463

def solution():
    n = int(input())
    cnt = [None] * (n + 1)

    cnt[1] = 0
    for i in range(1, n + 1):
        next1 = i + 1
        if next1 < n + 1:
            if cnt[next1] is None:
                cnt[next1] = cnt[i] + 1
            else:
                cnt[next1] = min(cnt[next1], cnt[i] + 1)
        next2 = i * 2
        if next2 < n + 1:
            if cnt[next2] is None:
                cnt[next2] = cnt[i] + 1
            else:
                cnt[next2] = min(cnt[next2], cnt[i] + 1)
        next3 = i * 3
        if next3 < n + 1:
            if cnt[next3] is None:
                cnt[next3] = cnt[i] + 1
            else:
                cnt[next3] = min(cnt[next3], cnt[i] + 1)
    
    return cnt[-1]

print(solution())
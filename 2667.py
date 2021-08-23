# https://www.acmicpc.net/problem/2667

from collections import deque

def solution():
    # 단지의 크기 : n x n
    n = int(input())
    town = [[0] * n for _ in range(n)]
    homes = set()
    for i in range(n):
        line = input()
        for j in range(n):
            cur = int(line[j])
            if cur == 1:
                town[i][j] = 1
                homes.add((i, j))

    # BFS로 단지개수, 집 개수 찾기
    q = deque()
    x_dir = [1, -1, 0, 0] # 동, 서, 남, 북
    y_dir = [0, 0, 1, -1]
    direction = 4
    num_towm = 0
    num_homes = []
    # 단지 찾기
    while homes:
        num_towm += 1
        # 한 단지에서 집 개수 찾기
        q.append(homes.pop())
        tmp_homes = 1
        while q:
            cur_r, cur_c = q.popleft()
            for i in range(direction):
                tmp_r, tmp_c = cur_r + y_dir[i], cur_c + x_dir[i]
                if (0 <= tmp_r < n) and (0 <= tmp_c < n) and (tmp_r, tmp_c) in homes:
                    tmp_homes += 1
                    homes.remove((tmp_r, tmp_c))
                    q.append((tmp_r, tmp_c))
        num_homes.append(tmp_homes)
    
    # 정답 출력
    num_homes.sort()
    print(num_towm)
    for num in num_homes:
        print(num)
    return

        
solution()
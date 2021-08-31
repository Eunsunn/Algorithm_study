# https://www.acmicpc.net/problem/1012

import sys
from collections import deque

def solution():
    input = sys.stdin.readline
    
    num_test = int(input())
    dy = [0, 0, 1, -1] # 동, 서, 남, 북
    dx = [1, -1, 0, 0]

    def min_earth_worm():
        # 가로, 세로 길이, 배추개수 입력
        n, m, k = map(int, input().split())
        
        # 배추밭에서 배추 위치를 1로 입력
        cabbage = set() # 방문하지 않은 배추의 (행, 열) 위치
        for _ in range(k):
            x, y = map(int, input().split())
            cabbage.add((y, x))

        # 방문하지 않은 배추가 남아있으면 탐색
        num_group = 0 # 배추 그룹개수 = 필요한 최소 지렁이 수
        while len(cabbage) > 0:
            num_group += 1
            # 시작점과 인접한 배추를 탐색
            queue = deque([cabbage.pop()])
            while queue:
                r, c = queue.popleft()
                for i in range(len(dx)):
                    tmp_r, tmp_c = r + dy[i], c + dx[i]
                    if 0 <= tmp_r < m and 0 <= tmp_c < n and (tmp_r, tmp_c) in cabbage:
                        queue.append((tmp_r, tmp_c)) # 탐색할 후보에 추가하고
                        cabbage.remove((tmp_r, tmp_c)) # 방문하지 않은 set에서 제거
    
        return num_group


    # Test case 개수만큼 정답을 찾는다
    for _ in range(num_test):
        print(min_earth_worm())


solution()


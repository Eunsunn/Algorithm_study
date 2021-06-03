# https://www.acmicpc.net/problem/2206

import sys
from collections import deque

def solution():
    input = sys.stdin.readline
    dx = [1, -1, 0, 0] # 동, 서, 남, 북
    dy = [0, 0, 1, -1]

    # (N x M) 크기의 맵
    n, m = map(int, input().split())
    max_dist = n * m + 1
    graph = []
    for _ in range(n):
        graph.append([int(i) for i in input().rstrip()])

    def BFS():
        q = deque()
        visited = [[False] * m for _ in range(n)] # 부수지 않고 방문한 경우
        break_visited = [[False] * m for _ in range(n)] # 부수고 방문한 경우

        # (r, c)을 큐에 넣기, dist: 이전까지 거리
        def push_to_q(r, c, dist, breaked):
            checked = None
            # 벽이 아닌 경우 -> breaked에 따라 그대로 간다
            if graph[r][c] == 0:
                if breaked == False:
                    checked = visited
                else:
                    checked = break_visited
            # 벽인 경우 -> 벽을 부수고 break_visited를 체크한다
            else:
                if breaked == False:
                    checked = break_visited
                    breaked = True
                else:
                    return
            # 기존에 방문하지 않았던 경우만 방문
            if checked[r][c] == False:
                checked[r][c] = True
                q.append((r, c, dist + 1, breaked))
        
        result = max_dist
        push_to_q(0, 0, 0, False)
        while q:
            r, c, dist, breaked = q.popleft()
            if r == n - 1 and c == m - 1:
                result = min(result, dist)
                continue
            for i in range(len(dx)):
                tmp_r, tmp_c = r + dy[i], c + dx[i]
                if 0 <= tmp_r < n and 0 <= tmp_c < m:
                    push_to_q(tmp_r, tmp_c, dist, breaked)

        if result == max_dist:
            return -1
        else:
            return result

    return BFS()

if __name__ == "__main__":
    print(solution())
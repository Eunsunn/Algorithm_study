# https://www.acmicpc.net/problem/2606

def solution():
    n = int(input())
    edge = int(input())
    connected = [[False] * n for _ in range(n)]
    for _ in range(edge):
        i, j = map(int, input().split())
        connected[i - 1][j - 1] = connected[j - 1][i - 1] = True
    
    # 현재 stack에 들어있는 노드 이후로 바이러스에 걸리는 컴퓨터 수 탐색
    # return: 컴퓨터 수 (0 이상)
    def DFS(stack, visited):
        cnt = 0
        if not stack:
            return cnt
        # 현재 노드
        cur_com = stack[-1]
        # 다음 방문할 노드
        for next_com in range(0, n):
            if next_com == cur_com: 
                continue
            if connected[cur_com][next_com] and not visited[next_com]:
                visited[next_com] = True
                stack.append(next_com)
                cnt += 1 # next_com 노드 방문
                cnt += DFS(stack, visited) # next_com 이후 방문 노드
                stack.pop()
        return cnt

    # DFS 탐색
    start = 0
    stack = [start]
    visited = [False] * n
    visited[start] = True
    num_infected = DFS(stack, visited)
    return num_infected

ret = solution()
print(ret)


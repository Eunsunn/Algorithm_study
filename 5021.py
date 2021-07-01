# https://www.acmicpc.net/problem/5021

import sys
from collections import deque

def solution():
    input = sys.stdin.readline
    n, m = map(int, input().split())
    king = input().rstrip()

    # 트리 생성하기
    children, parents = {}, {} # 각 노드의 자식노드, 부모노드 저장
    for _ in range(n):
        child, p1, p2 = input().split()
        parents[child] = [p1, p2]
        for p in (p1, p2):
            if p in children:
                children[p].append(child)
            else:
                children[p] = [child]

    # BFS로 혈통 계산하기
    blood = {}
    blood[king] = 1
    q = deque()
    q.append(king)
    while q:
        cur = q.popleft()
        if cur in children:
            # 현재 노드의 자식 노드들 혈통 계산
            for child in children[cur]:
                value = 0
                # 부모의 혈통합 / 2
                for parent in parents[child]:
                    if parent in blood:
                        value += blood[parent]
                blood[child] = value / 2.0
                q.append(child)
    
    # 왕위 계승자 찾기
    max_val, next_king = 0, None
    for _ in range(m):
        name = input().rstrip()
        if name in blood:
            if blood[name] > max_val:
                max_val = blood[name]
                next_king = name
    return next_king

print(solution())

# https://www.acmicpc.net/problem/1620

import sys

def solution():
    input = sys.stdin.readline
    n, m = map(int, input().split())
    name2idx, idx2name = {}, [None] * (n + 1)
    for i in range(1, n + 1):
        name = input().strip()
        name2idx[name] = i
        idx2name[i] = name
        
    for i in range(m):
        line = input().strip()
        if line.isdigit():
            print(idx2name[int(line)])
        else:
            print(name2idx[line])
            
solution()
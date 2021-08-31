# https://www.acmicpc.net/problem/1003

def solution():
    num_test = int(input())
    max_n = 40
    # fibo[i] = i를 호출할 때 [0 출력 횟수, 1 출력 횟수]
    fibo = [[0, 0] for _ in range(max_n + 1)]
    fibo[0][0], fibo[1][1] = 1, 1 # 초기화
    for i in range(2, max_n + 1):
        fibo[i][0] = fibo[i - 1][0] + fibo[i - 2][0]
        fibo[i][1] = fibo[i - 1][1] + fibo[i - 2][1]

    for _ in range(num_test):
        n = int(input())
        print(fibo[n][0], end=' ')
        print(fibo[n][1])

solution()
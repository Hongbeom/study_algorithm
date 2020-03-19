## 백준 5546 - [파스타](https://www.acmicpc.net/problem/5546)

### 풀이법

1. memo[][] 2차원 DP
2. 그날의 그 파스타가 정해졌다면 그 파스타에 대한 자리만 업데이트.
3. 특정한 날 N에 특정 파스타를 먹을 경우, N-1까지의 모든 경우의수를 구한 뒤, 만약 그 특정 파스타에 대해 3일 연속에 대한 경우가 있다면 N-3일에 대해 특정 파스타를 먹지 않는 경우를 빼주면 된다.
  - 만약 n 이 3일 경우에는 1을 빼준다.

```JAVA
if (PLAN[1] == 0) {
    memo[1][1] = 1;
    memo[2][1] = 1;
    memo[3][1] = 1;
} else {
    memo[PLAN[1]][1] = 1;
}

for (int j = 2; j <= n; j++) {
    for (int i = 1; i <= 3; i++) {
        if (PLAN[j] != 0 && PLAN[j] != i) {
            continue;
        }

        int sum = (memo[1][j - 1] + memo[2][j - 1] + memo[3][j - 1]) % 10000;
        if (j != 2 && memo[i][j - 1] != 0 && memo[i][j - 2] != 0) {
            // 빼주는 경우.
            if (j - 3 == 0) {
                sum -= 1;
            } else {
                int b1 = i % 3 + 1;
                int b2 = b1 % 3 + 1;
                long sb = memo[b1][j - 3] + memo[b2][j - 3];
                sum -= sb;
            }
        }

        while (sum < 0) {
            sum += 10000;
        }

        memo[i][j] = sum;
    }
}
```
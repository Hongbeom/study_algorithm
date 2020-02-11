## 백준 1535 - [안녕](https://www.acmicpc.net/problem/1535)

0/1 knapsack problem


### 풀이법

1. dp[i][j] 의 배열을 이용한 dp
- i = 사람수
- j = (감당할수 있는) 체력
- 답은 dp[n][99]

2. dp[i][j] = Max(dp[i-1][j], dp[i-1][j - hp[i]])
- i번째 사람에게 인사했을때와 안했을 경우의 최댓값 

```JAVA
int[][] dp = new int[n + 1][100];

for (int i = 1; i <= n; i++) {
    for (int j = 1; j < 100; j++) {
        if (j - hp[i - 1] >= 0) {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - hp[i - 1]] + pleasure[i - 1]);
        } else {
            dp[i][j] = dp[i - 1][j];
        }
    }
}
```
